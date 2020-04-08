package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;

import java.util.Scanner;

public class EngineImpl implements Engine {
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ChampionshipController controller = new ChampionshipControllerImpl(
                new RiderRepository()
                , new MotorcycleRepository()
                , new RaceRepository());

        while (!input.equals("End")) {

            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            try {
                switch (command) {
                    case "CreateRider":
                        System.out.println(controller.createRider(tokens[1]));
                        break;
                    case "CreateMotorcycle":
                        System.out.println(controller.createMotorcycle(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                        break;
                    case "AddMotorcycleToRider":
                        System.out.println(controller.addMotorcycleToRider(tokens[1], tokens[2]));
                        break;
                    case "AddRiderToRace":
                        System.out.println(controller.addRiderToRace(tokens[1], tokens[2]));
                        break;
                    case "CreateRace":
                        System.out.println(controller.createRace(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    case "StartRace":
                        System.out.println(controller.startRace(tokens[1]));
                        break;
                }
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

            input = scanner.nextLine();
        }
    }
}
