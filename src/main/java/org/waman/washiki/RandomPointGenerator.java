package org.waman.washiki;

import java.util.function.Supplier;

public interface RandomPointGenerator extends Supplier<double[]>{

    int getDimension();
    double getRadius();
    RandomGenerator getRandomGenerator();

    void setRandomPoint(double[] x);

    double[] newRandomPoint();

    @Override
    default double[] get() {
        return newRandomPoint();
    }
}