package aquarium.models.aquariums;

import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class BaseAquarium implements Aquarium {
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity){
        setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }


    public void setName(String name) {
        if (name==null || name.trim().equals("")){
            throw new NullPointerException("Aquarium name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size()<this.capacity){
            this.fish.add(fish);
        }else {
            throw new IllegalStateException("Not enough capacity.");
        }
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name + " (%s):" + System.lineSeparator());
        sb.append("Fish: ");
        if (fish.isEmpty()){
            sb.append("none");
        }else{
            StringBuilder s = new StringBuilder();
            this.fish.forEach(f-> s.append(f.getName() + " "));
            sb.append(s.toString().trim());
        }
        sb.append(System.lineSeparator());
        sb.append("Decorations: " + this.decorations.size() + System.lineSeparator());
        sb.append("Comfort: " + this.calculateComfort());
        return sb.toString().trim();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
