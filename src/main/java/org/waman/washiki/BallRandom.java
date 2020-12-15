package org.waman.washiki;

import java.util.Random;

import static java.lang.Math.pow;
import static org.waman.washiki.WashikiUtil.*;

public class BallRandom {

    public static RandomPointGenerator newGenerator(int dim){
        return newGenerator(Math::random, dim);
    }

    public static RandomPointGenerator newGenerator(long seed, int dim){
        return newGenerator(new Random(seed)::nextDouble, dim);
    }

    public static RandomPointGenerator newGenerator(RandomGenerator rand, int dim){
        return new UnitBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim), dim);

    }
    public static RandomPointGenerator newGenerator(int dim, double radius){
        return newGenerator(Math::random, dim, radius);
    }

    public static RandomPointGenerator newGenerator(long seed, int dim, double radius){
        return newGenerator(new Random(seed)::nextDouble, dim, radius);
    }

    public static RandomPointGenerator newGenerator(RandomGenerator rand, int dim, double radius){
        return new ScaleBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim), radius);
    }
}

class UnitBallRandomPointGenerator implements UnitRandomPointGenerator{

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
    public RandomGenerator getRandomGenerator() {
        return this.rand.getRandomGenerator();
    }

    @Override
    /**
     * @param x array to which coordinate of a random point in n-ball (n >= 2)
     */
    public void setRandomPoint(double[] x){
        int n = x.length;
        this.rand.setRandomPoint(x);
        scale(x, pow(getRandomGenerator().nextDouble(), 1.0/n));
    }
}

class ScaleBallRandomPointGenerator implements RandomPointGenerator {

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
    public void setRandomPoint(double[] x){
        int n = x.length;
        this.rand.setRandomPoint(x);
        double s = pow(getRandomGenerator().nextDouble(), 1.0/n) * this.radius;
        scale(x, s);
    }
}