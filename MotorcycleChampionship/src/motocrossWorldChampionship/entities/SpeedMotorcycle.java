package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.common.ExceptionMessages;
import motocrossWorldChampionship.entities.MotorcycleImpl;

public class SpeedMotorcycle extends MotorcycleImpl {
    private static final double DEFAULT_CUBIC_CM =  125.0;
    private static final int DEFAULT_MIN_HP =  50;
    private static final int DEFAULT_MAX_HP =  69;


    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, DEFAULT_CUBIC_CM);
    }

    @Override
    protected void setHorsePower(int horsePower) {
        if (horsePower<DEFAULT_MIN_HP || horsePower>DEFAULT_MAX_HP){
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_HORSE_POWER, horsePower));
        }
        super.setHorsePower(horsePower);
    }
}
