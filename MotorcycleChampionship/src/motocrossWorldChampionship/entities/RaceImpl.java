package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RaceImpl implements Race {
    private String name;
    private int laps;
    private Collection<Rider> riders;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.riders = new ArrayList<>();
    }

    private void setName(String name){
        if (name==null|| name.trim().equals("") || name.length()<5){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps<1){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS, 1));
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    @Override
    public Collection<Rider> getRiders() {
        return this.riders;
    }

    @Override
    public void addRider(Rider rider) {
        if (rider==null){
            throw new NullPointerException(ExceptionMessages.RIDER_INVALID);
        }
        if (!rider.getCanParticipate()){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_NOT_PARTICIPATE, rider.getName()));
        }
        if (alreadyParticipates(rider.getName())){
            throw new IllegalArgumentException(String.format(ExceptionMessages.RIDER_ALREADY_ADDED, rider.getName(), this.name));
        }
        this.riders.add(rider);
    }

    private boolean alreadyParticipates(String name) {
        for (Rider rider : this.riders) {
            if (rider.getName().equals(name)){
                return true;
            }
        }
        return false;
    }
}
