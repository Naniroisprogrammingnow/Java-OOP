package aquarium.repositories;

import aquarium.models.decorations.Decoration;

public interface Repository<D> {
    void add(Decoration decoration);

    boolean remove(Decoration decoration);

    Decoration findByType(String type);
}
