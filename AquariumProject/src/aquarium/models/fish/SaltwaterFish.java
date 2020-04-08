package aquarium.models.fish;

public class SaltwaterFish  extends BaseFish{
    private static final int SIZE_DEFAULT = 5;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        this.setSize(SIZE_DEFAULT);
    }

    @Override
    public void eat() {
        this.setSize(this.getSize()+2);
    }
}
