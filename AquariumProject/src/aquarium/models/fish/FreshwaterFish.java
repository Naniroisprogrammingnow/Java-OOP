package aquarium.models.fish;

public class FreshwaterFish extends BaseFish {
    private static final int SIZE_DEFAULT = 3;
    public FreshwaterFish(String name, String species, double price) {
        super(name, species, price);
        super.setSize(SIZE_DEFAULT);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize()+3);
    }
}
