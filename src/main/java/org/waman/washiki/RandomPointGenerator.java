package org.waman.washiki;

import java.util.function.Supplier;

public interface RandomPointGenerator extends Supplier<double[]>{

    int getDimension();
    double getRadius();
    RandomGenerator getRandomGenerator();

    /**
     * @param x array to which coordinate of a random point on (n-1)-sphere (n >= 2) is set.
     * @param start
     */
    void setRandomPoint(double[] x, int start);

    default void setRandomPoint(double[] x){
        setRandomPoint(x, 0);
    }

    double[] newRandomPoint();

    @Override
    default double[] get() {
        return newRandomPoint();
    }
}