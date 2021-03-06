package org.waman.washiki;

import static java.lang.Math.*;
import static org.waman.washiki.WashikiUtil.TWO_PI;
import static org.waman.washiki.WashikiUtil.scale;

public interface SphereRandomPointGenerator extends RandomPointGenerator{

    default double[] newRandomPoint(){
        double[] x = new double[getDimension()+1];
        setRandomPoint(x);
        return x;
    }
}

abstract class UnitSphereRandomPointGenerator implements SphereRandomPointGenerator {

    private final RandomGenerator rand;
    private final int dim;

    UnitSphereRandomPointGenerator(RandomGenerator rand, int dim) {
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
        return this.rand;
    }

    protected final double nextPhi(){
        return this.rand.nextDouble(TWO_PI);
    }

    protected final void setRandomPointOnCircumference(double[] x){
        double phi = nextPhi();
        x[0] = sin(phi);
        x[1] = cos(phi);
    }

    protected final void setRandomPointOnSphere(double[] x){
        double phi = nextPhi();
        double sinTheta = this.getRandomGenerator().nextDouble(-1.0, 2.0);  // uniform distribution in [-1, 1] (min:-1.0, width:2.0)
        double cosTheta = sqrt(1 - sinTheta * sinTheta);

        x[0] = sinTheta;
        x[1] = cosTheta * sin(phi);
        x[2] = cosTheta * cos(phi);
    }
}

class UnitCircumferenceRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitCircumferenceRandomPointGenerator(RandomGenerator rand){
        super(rand, 1);
    }

    @Override
    public void setRandomPoint(double[] x){
        setRandomPointOnCircumference(x);
    }
}

class UnitSphere2DRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitSphere2DRandomPointGenerator(RandomGenerator rand){
        super(rand, 2);
    }

    @Override
    public void setRandomPoint(double[] x){
        setRandomPointOnSphere(x);
    }
}

class UnitHypersphereRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitHypersphereRandomPointGenerator(RandomGenerator rand, int dim){
        super(rand, dim);
    }

    @Override
    public void setRandomPoint(double[] x){
        setRandomPoint(x, this.getDimension());
    }

    private void setRandomPoint(double[] x, int dim){
        switch(dim){
            case 1:
                setRandomPointOnCircumference(x);break;
            case 2:
                setRandomPointOnSphere(x);break;
            default:
                setRandomPointOnHypersphere(x, dim);
        }
    }

    private void setRandomPointOnHypersphere(double[] x, int dim){
        setRandomPoint(x, dim-2);

        double phi = nextPhi();
        double theta = this.getRandomGenerator().nextDouble();
        double sinTheta = pow(theta, 1.0/(dim-1));
        double cosTheta = sqrt(1 - sinTheta * sinTheta);

        for(int i = 0, n = dim-1; i < n; i++) {
            x[i] *= sinTheta;
        }

        x[dim-1] = cosTheta * sin(phi);
        x[dim] = cosTheta * cos(phi);
    }
}

class ScaleSphereRandomPointGenerator implements SphereRandomPointGenerator{

    private final UnitSphereRandomPointGenerator rand;
    private final double radius;

    protected ScaleSphereRandomPointGenerator(UnitSphereRandomPointGenerator rand, double radius){
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
        scale(x, this.radius, this.getDimension()+1);
    }
}
