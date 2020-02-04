package interfaces;

import jdk.jshell.spi.ExecutionControl;

public interface UnitFactory {

   interfaces.Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException;
}