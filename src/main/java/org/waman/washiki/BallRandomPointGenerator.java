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

class UnitBall1DRandomPointGenerator implements BallRandomPointGenerator{

    private final RandomGenerator rand;

    UnitBall1DRandomPointGenerator(RandomGenerator rand){
        this.rand = rand;
    }

    @Override
    public int getDimension() {
        return 1;
    }

    @Override
    public double getRadius() {
        return 1.0;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand;
    }

    @Override
    public void setRandomPoint(double[] x) {
        x[0] = this.rand.nextDouble(-1.0, 2.0);
    }
}

class ScaleBall1DRandomPointGenerator implements BallRandomPointGenerator{

    private final RandomGenerator rand;
    private final double radius;

    ScaleBall1DRandomPointGenerator(RandomGenerator rand, double radius){
        this.rand = rand;
        this.radius = radius;
    }

    @Override
    public int getDimension() {
        return 1;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand;
    }

    @Override
    public void setRandomPoint(double[] x) {
        x[0] = this.rand.nextDouble(-1.0, 2.0) * this.radius;
    }
}

class UnitBallRandomPointGenerator implements BallRandomPointGenerator {

    private final UnitSphereRandomPointGenerator rand;
    private final int dim;

    UnitBallRandomPointGenerator(UnitSphereRandomPointGenerator rand) {
        this.rand = rand;
        this.dim = rand.getDimension()+1;
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
    public void setRandomPoint(double[] x) {
        this.rand.setRandomPoint(x);
        scale(x, pow(getRandomGenerator().nextDouble(), 1.0/this.dim), this.dim);
    }
}

class ScaleBallRandomPointGenerator implements BallRandomPointGenerator {

    private final UnitSphereRandomPointGenerator rand;
    private final int dim;
    private final double radius;

    ScaleBallRandomPointGenerator(UnitSphereRandomPointGenerator rand, double radius) {
        this.rand = rand;
        this.dim = rand.getDimension()+1;
        this.radius = radius;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand.getRandomGenerator();
    }

    @Override
    public int getDimension() {
        return this.dim;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    /**
     * @param x array to which coordinate of a random point in n-ball (n >= 2)
     */
    public void setRandomPoint(double[] x){
        this.rand.setRandomPoint(x);
        double s = pow(getRandomGenerator().nextDouble(), 1.0/this.dim) * this.radius;
        scale(x, s, this.dim);
    }
}
