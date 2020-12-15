package org.waman.washiki;

import java.util.function.Supplier;
import static org.waman.washiki.WashikiUtil.*;

public interface RandomPointGenerator extends Supplier<double[]>{

    int getDimension();
    double getRadius();
    RandomGenerator getRandomGenerator();

    /**
     * @param x array to which coordinate of a random point on (n-1)-sphere (n >= 2) is set.
     */
    void setRandomPoint(double[] x);

    default double[] newRandomPoint(){
        double[] x = new double[getDimension()];
        setRandomPoint(x);
        return x;
    }

    @Override
    default double[] get() {
        return newRandomPoint();
    }
}