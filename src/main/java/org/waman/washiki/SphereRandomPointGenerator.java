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

    protected final void setRandomPointOnCircumference(double[] x, int start){
        double phi = nextPhi();
        x[start] = sin(phi);
        x[start+1] = cos(phi);
    }

    protected final void setRandomPointOnSphere(double[] x, int start){
        double phi = nextPhi();
        double sinTheta = this.getRandomGenerator().nextDouble(-1.0, 2.0);  // uniform distribution in [-1, 1] (min:-1.0, width:2.0)
        double cosTheta = sqrt(1 - sinTheta * sinTheta);

        x[start] = sinTheta;
        x[start+1] = cosTheta * sin(phi);
        x[start+2] = cosTheta * cos(phi);
    }
}

class UnitCircumferenceRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitCircumferenceRandomPointGenerator(RandomGenerator rand){
        super(rand, 1);
    }

    @Override
    public void setRandomPoint(double[] x, int start){
        setRandomPointOnCircumference(x, start);
    }
}

class UnitSphere2DRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitSphere2DRandomPointGenerator(RandomGenerator rand){
        super(rand, 2);
    }

    @Override
    public void setRandomPoint(double[] x, int start){
        setRandomPointOnSphere(x, start);
    }
}

class UnitHypersphereRandomPointGenerator extends UnitSphereRandomPointGenerator {

    UnitHypersphereRandomPointGenerator(RandomGenerator rand, int dim){
        super(rand, dim);
    }

    @Override
    public void setRandomPoint(double[] x, int start){
        setRandomPoint(x, start, this.getDimension());
    }

    private void setRandomPoint(double[] x, int start, int dim){
        switch(dim){
            case 1:
                throw new IllegalArgumentException("Argument array must have at least 2 length.");
            case 2:
                setRandomPointOnCircumference(x, start);break;
            case 3:
                setRandomPointOnSphere(x, start);break;
            default:
                setRandomPointOnHypersphere(x, start, dim);
        }
    }

    private void setRandomPointOnHypersphere(double[] x, int start, int dim){
        setRandomPoint(x, start, dim-2);  // generate the uniform distribution on (n-3)-sphere

        double phi = nextPhi();
        double theta = this.getRandomGenerator().nextDouble();
        double sinTheta = pow(theta, 1.0/(dim-2.0));
        double cosTheta = sqrt(1 - sinTheta * sinTheta);

        int end = start+dim;

        for(int i = start, n =end-2; i < n; i++) {
            x[i] *= sinTheta;
        }

        x[end-2] = cosTheta * sin(phi);
        x[end-1] = cosTheta * cos(phi);
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
    public void setRandomPoint(double[] x, int start) {
        this.rand.setRandomPoint(x, start);
        scale(x, this.radius, start, this.getDimension());
    }
}
