package aquarium.models.decorations;

public class Ornament extends BaseDecoration {
    private static final int DEFAULT_COMFORT = 1;
    private static final double DEFAULT_PRICE = 5.0;

    public Ornament() {
        super(DEFAULT_COMFORT, DEFAULT_PRICE);
    }
}
