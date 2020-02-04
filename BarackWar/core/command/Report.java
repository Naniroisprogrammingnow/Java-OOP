package core.command;

import interfaces.Repository;
import interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Report extends Command {
    public Report(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String output = this.getRepository().getStatistics();
        return output;
    }
}
