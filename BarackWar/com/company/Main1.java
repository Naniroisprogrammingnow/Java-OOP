package com.company;

import core.Engine;
import core.factories.UnitFactoryImpl;
import data.UnitRepository;
import interfaces.Repository;
import interfaces.Unit;
import interfaces.UnitFactory;

public class Main1 {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
