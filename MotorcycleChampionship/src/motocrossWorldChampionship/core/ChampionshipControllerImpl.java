package motocrossWorldChampionship.core;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.common.OutputMessages;
import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;

import java.util.*;
import java.util.stream.Collectors;

public class ChampionshipControllerImpl implements ChampionshipController {

    private RiderRepository riderRepository;
    private MotorcycleRepository motorcycleRepository;
    private RaceRepository raceRepository;

    public ChampionshipControllerImpl(RiderRepository riderRepository, MotorcycleRepository motorcycleRepository, RaceRepository raceRepository) {
        this.riderRepository = riderRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.raceRepository = raceRepository;
    }

    @Override
    public String createRider(String riderName) {
        Rider rider = new RiderImpl(riderName);
        if (this.riderRepository.getByName(riderName)!=null){
            throw new IllegalArgumentException(String.format("Rider %s is already created.",riderName));
        }
        this.riderRepository.add(rider);
        return String.format("Rider %s is created.", riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle;
        switch (type){
            case "Speed":
                motorcycle = new SpeedMotorcycle(model, horsePower);
                break;
            case "Power":
                motorcycle = new PowerMotorcycle(model, horsePower);
                break;
            default:
                throw  new IllegalArgumentException("Invalid motorcycle type");
        }
        if (this.motorcycleRepository.getByName(model)!=null){
            throw new IllegalArgumentException(String.format("Motorcycle %s is already created.", model));
        }
        this.motorcycleRepository.add(motorcycle);
        return String.format("%sMotorcycle %s is created.", type, model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider==null){
            throw new NullPointerException(String.format("Rider %s could not be found.", riderName));
        }
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);
        if (motorcycle==null){
            throw new NullPointerException(String.format("Motorcycle %s could not be found.", motorcycleModel));
        }
        rider.addMotorcycle(motorcycle);

        return String.format("Rider %s received motorcycle %s.", riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race==null){
            throw new NullPointerException(String.format("Race %s could not be found.", raceName));
        }
        Rider rider = this.riderRepository.getByName(riderName);
        if (rider==null){
            throw new NullPointerException(String.format("Rider %s could not be found.", riderName));
        }
        race.addRider(rider);

        return String.format("Rider %s added in %s race.",riderName,raceName);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        if (race==null){
            throw new NullPointerException(String.format("Race %s could not be found.", raceName));
        }
        Collection<Rider> riders = race.getRiders();
        if (riders.size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID, raceName, 3));
        }
        int laps = race.getLaps();
        List<Rider> result =  riders.stream().sorted((a, b) -> {
            return Double.compare(b.getMotorcycle().calculateRacePoints(laps),
                    a.getMotorcycle().calculateRacePoints(laps));
        }).limit(3).collect(Collectors.toUnmodifiableList());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(OutputMessages.RIDER_FIRST_POSITION, result.get(0).getName(), raceName))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_SECOND_POSITION, result.get(1).getName(), raceName))
                .append(System.lineSeparator());
        sb.append(String.format(OutputMessages.RIDER_THIRD_POSITION, result.get(2).getName(), raceName))
                .append(System.lineSeparator());

        this.raceRepository.remove(race);
        return sb.toString().trim();
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        if (this.raceRepository.getByName(name)!=null){
            throw new IllegalArgumentException(String.format("Race %s is already created.", name));
        }
        this.raceRepository.add(race);
        return String.format("Race %s is created.", name);
    }
}
