package core.command;

import interfaces.Executable;
import interfaces.Repository;
import interfaces.UnitFactory;

public abstract class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    public Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected Repository getRepository(){
        return this.repository;
    }
    protected UnitFactory getUnitFactory(){
        return this.unitFactory;
    }
    protected String [] getData(){
        return this.data;
    }
}
