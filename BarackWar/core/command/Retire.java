package core.command;

import interfaces.Repository;
import interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {

    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String result;
        try {
            this.getRepository().removeUnit(this.getData()[1]);
            result = this.getData()[1] + " retired!";
        }catch (IllegalArgumentException e){
            result = e.getMessage();
        }
        return result;
    }
}
