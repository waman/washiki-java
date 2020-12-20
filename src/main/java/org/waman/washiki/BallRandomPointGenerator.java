package org.waman.washiki;

import static java.lang.Math.pow;
import static org.waman.washiki.WashikiUtil.scale;

public interface BallRandomPointGenerator extends RandomPointGenerator{

    default double[] newRandomPoint(){
        double[] x = new double[getDimension()];
        setRandomPoint(x);
        return x;
    }
}

class UnitBallRandomPointGenerator implements BallRandomPointGenerator {

    private final UnitSphereRandomPointGenerator rand;
    private final int dim;

    UnitBallRandomPointGenerator(UnitSphereRandomPointGenerator rand, int dim) {
        this.rand = rand;
        this.dim = dim;
    }

    @Override
    public int getDimension() {
        return this.dim;
    }

    @Override
    public double getRadius() {
        return 1.0;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand.getRandomGenerator();
    }

    @Override
    /**
     * @param x array to which coordinate of a random point in n-ball (n >= 2)
     */
    public void setRandomPoint(double[] x, int start) {
        this.rand.setRandomPoint(x, start);
        scale(x, pow(getRandomGenerator().nextDouble(), 1.0 / getDimension()));
    }
}

class ScaleBallRandomPointGenerator implements BallRandomPointGenerator {

    private final UnitSphereRandomPointGenerator rand;
    private final double radius;

    ScaleBallRandomPointGenerator(UnitSphereRandomPointGenerator rand, double radius) {
        this.rand = rand;
        this.radius = radius;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand.getRandomGenerator();
    }

    @Override
    public int getDimension() {
        return this.rand.getDimension();
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    /**
     * @param x array to which coordinate of a random point in n-ball (n >= 2)
     */
    public void setRandomPoint(double[] x, int start){
        this.rand.setRandomPoint(x, start);
        double s = pow(getRandomGenerator().nextDouble(), 1.0/getDimension()) * this.radius;
        scale(x, s);
    }
}
