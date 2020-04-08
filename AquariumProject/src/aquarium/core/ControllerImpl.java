package aquarium.core;

import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;
import aquarium.repositories.Repository;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private Repository decorations;
    private Collection<Aquarium>aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium;
        if (aquariumType.equals("FreshwaterAquarium")){
            aquarium = new FreshwaterAquarium(aquariumName);
        }else if (aquariumType.equals("SaltwaterAquarium")){
            aquarium = new SaltwaterAquarium(aquariumName);
        }else {
            throw new IllegalArgumentException("Invalid aquarium type.");
        }
        this.aquariums.add(aquarium);
        return String.format("Successfully added %s.", aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        if (type.equals("Plant")){
            decoration = new Plant();
        }else  if (type.equals("Ornament")){
            decoration = new Ornament();
        }else {
            throw  new IllegalArgumentException("Invalid decoration type.");
        }
        this.decorations.add(decoration);
        return String.format("Successfully added %s.", type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Aquarium aquarium = findByName(aquariumName);
        if (aquarium==null){
            throw new IllegalArgumentException(String.format("Aquarium %s doesn't exist", aquariumName));
        }
        Decoration decorationsByType = this.decorations.findByType(decorationType);
        if (decorationsByType==null){
            throw  new IllegalArgumentException(String.format("There isn't a decoration of type %s.", decorationType));
        }
        aquarium.addDecoration(decorationsByType);
        this.decorations.remove(decorationsByType);
        return String.format("Successfully added %s to %s.", decorationType, aquariumName);
    }

    private Aquarium findByName(String aquariumName) {
        for (Aquarium aquarium : this.aquariums) {
            if (aquarium.getName().equals(aquariumName)){
                return aquarium;
            }
        }
        return null;
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Aquarium aquarium = findByName(aquariumName);
        Fish fish;
        if (fishType.equals("FreshwaterFish")){
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        }else if (fishType.equals("SaltwaterFish")){
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        }else {
            throw new IllegalArgumentException("Invalid fish type.");
        }
        StringBuilder sb = new StringBuilder();
        String type = fishType.substring(0, fishType.length()-4);
        if (!aquarium.getClass().getSimpleName().contains(type)){
            sb.append("Water not suitable.");
        }else {
            try {
                aquarium.addFish(fish);
                sb.append(String.format("Successfully added %s to %s.", fishType, aquariumName));
            }catch (IllegalStateException e){
                sb.append(e.getMessage());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String feedFish(String aquariumName) {
        Aquarium aquarium = findByName(aquariumName);
        aquarium.feed();
        return String.format("Fish fed: %d", aquarium.getFish().size());
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium = findByName(aquariumName);
        double value = 0;
        value+=aquarium.getFish().stream().mapToDouble(Fish::getPrice).sum();
        value+= aquarium.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();
        return String.format("The value of Aquarium %s is %.2f.", aquariumName, value);
    }

    @Override
    public String report() {
        StringBuilder stringBuilder = new StringBuilder();
        this.aquariums.forEach(a->{
            stringBuilder.append(a.getInfo());
            stringBuilder.append(System.lineSeparator());
        });
        return stringBuilder.toString().trim();
    }
}
