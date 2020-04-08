package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class RiderRepository implements Repository<Rider> {

    private Collection<Rider> riders;

    public RiderRepository() {
        this.riders = new ArrayList<>();
    }
    @Override
    public Rider getByName(String name) {
        for (Rider rider : riders) {
            if (rider.getName().equals(name)){
                return rider;
            }
        }

        return null;
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(riders);
    }

    @Override
    public void add(Rider model) {
        this.riders.add(model);
    }

    @Override
    public boolean remove(Rider model) {
        return this.riders.remove(model);
    }
}
