package org.waman.washiki;

import java.util.Random;

import static java.lang.Math.*;
import static org.waman.washiki.WashikiUtil.*;

public class SphereRandom {

    public static RandomPointGenerator newGenerator(int dim){
        return newGenerator(Math::random, dim);
    }

    public static RandomPointGenerator newGenerator(long seed, int dim){
        return newGenerator(new Random(seed)::nextDouble, dim);
    }

    public static UnitSphereRandomPointGenerator newGenerator(RandomGenerator rand, int dim){
        return new UnitSphereRandomPointGenerator(rand, dim);

    }
    public static RandomPointGenerator newGenerator(int dim, double radius){
        return newGenerator(Math::random, dim, radius);
    }

    public static RandomPointGenerator newGenerator(long seed, int dim, double radius){
        return newGenerator(new Random(seed)::nextDouble, dim, radius);
    }

    public static RandomPointGenerator newGenerator(RandomGenerator rand, int dim, double radius){
        return new ScaleSphereRandomPointGenerator(newGenerator(rand, dim), radius);
    }
}

class UnitSphereRandomPointGenerator implements UnitRandomPointGenerator{

    private final RandomGenerator rand;
    private final int dim;

    UnitSphereRandomPointGenerator(RandomGenerator rand, int dim){
        this.rand = rand;
        this.dim = dim;
    }

    @Override
    public int getDimension(){
        return this.dim;
    }

    @Override
    public RandomGenerator getRandomGenerator(){
        return this.rand;
    }

    private double nextPhi(){
        return this.getRandomGenerator().nextDouble(TWO_PI);
    }

    @Override
    public void setRandomPoint(double[] x){
        setPoint(x, x.length);
    }

    private void setPoint(double[] x, int n){
        switch(n){
            case 1:
                throw new IllegalArgumentException("Argument array must have at least 2 length.");
            case 2:
                setRandomPointOnCircumference(x);break;
            case 3:
                setRandomPointOnSphere2D(x);break;
            default:
                setRandomPointOnSphereND(x, n);
        }
    }

    private void setRandomPointOnCircumference(double[] x){
//        assert x.length == 2;
     
        double phi = nextPhi();
        x[0] = sin(phi);
        x[1] = cos(phi);
    }

    private void setRandomPointOnSphere2D(double[] x){
//        assert x.length == 3;
     
        double phi = nextPhi();
        double sinTheta = this.getRandomGenerator().nextDouble(-1.0, 2.0);  // uniform distribution in [-1, 1] (min:-1.0, width:2.0)
        double cosTheta = sqrt(1 - sinTheta * sinTheta);
     
        x[0] = sinTheta;
        x[1] = cosTheta * sin(phi);
        x[2] = cosTheta * cos(phi);
    }

    private void setRandomPointOnSphereND(double[] x, int n){
//        assert n >= 4;
     
        setPoint(x, n - 2);  // generate the uniform distribution on (n-3)-sphere

        double phi = nextPhi();
        double theta = this.getRandomGenerator().nextDouble();
        double sinTheta = pow(theta, 1.0/(n-2.0));
        double cosTheta = sqrt(1 - sinTheta * sinTheta);
     
        for(int i = 0; i < n-2; i++) {
            x[i] *= sinTheta;
        }
     
        x[n-2] = cosTheta * sin(phi);
        x[n-1] = cosTheta * cos(phi);
    }
}

class ScaleSphereRandomPointGenerator implements RandomPointGenerator{

    private final UnitRandomPointGenerator rand;
    private final double radius;

    protected ScaleSphereRandomPointGenerator(UnitRandomPointGenerator rand, double radius){
        this.rand = rand;
        this.radius = radius;
    }

    @Override
    public int getDimension() {
        return this.rand.getDimension();
    }

    @Override
    public double getRadius(){
        return this.radius;
    }

    @Override
    public RandomGenerator getRandomGenerator() {
        return this.rand.getRandomGenerator();
    }

    @Override
    public void setRandomPoint(double[] x) {
        this.rand.setRandomPoint(x);
        scale(x, this.radius);
    }
}
