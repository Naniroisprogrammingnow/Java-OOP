package aquarium.repositories;

import aquarium.models.decorations.Decoration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DecorationRepository implements Repository<Decoration> {
    private Collection<Decoration> decorations;

    public DecorationRepository() {
        this.decorations = new ArrayList<>();
    }

    public Collection<Decoration> getDecorations() {
        return Collections.unmodifiableCollection(decorations);
    }

    private void setDecorations(Collection<Decoration> decorations) {
        this.decorations = decorations;
    }

    @Override
    public void add(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public boolean remove(Decoration decoration) {
        return this.decorations.remove(decoration);
    }

    @Override
    public Decoration findByType(String type) {
        for (Decoration decoration : this.decorations) {
            if (decoration.getClass().getSimpleName().equals(type)){
                return decoration;
            }
        }
        return null;
    }
}
