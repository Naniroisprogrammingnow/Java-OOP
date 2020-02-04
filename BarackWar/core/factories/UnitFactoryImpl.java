package core.factories;

import interfaces.Unit;
import interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "models.";

    @Override
    public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
        Unit unit = null;
        try {
            Class<?> clazz = Class.forName(UNITS_PACKAGE_NAME+unitType);
            Constructor ctor = clazz.getDeclaredConstructor();
            unit = (Unit) ctor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return unit;
    }

}
