package org.waman.washiki;

import java.util.Random;

import static java.lang.Math.*;
import static org.waman.washiki.WashikiUtil.*;

public class SphereRandom {

    public static SphereRandomPointGenerator newGenerator(int dim){
        return newGenerator(Math::random, dim);
    }

    public static SphereRandomPointGenerator newGenerator(long seed, int dim){
        return newGenerator(new Random(seed)::nextDouble, dim);
    }

    public static UnitSphereRandomPointGenerator newGenerator(RandomGenerator rand, int dim){
        if (dim <= 0) {
            throw new IllegalArgumentException("The dimension argument must be positive: " + dim);
        }
        switch(dim){
            case 1:
                return new UnitCircumferenceRandomPointGenerator(rand);
            case 2:
                return new UnitSphere2DRandomPointGenerator(rand);
            default:
                return new UnitHypersphereRandomPointGenerator(rand, dim);
        }
    }

    public static SphereRandomPointGenerator newGenerator(int dim, double radius){
        return newGenerator(Math::random, dim, radius);
    }

    public static SphereRandomPointGenerator newGenerator(long seed, int dim, double radius){
        return newGenerator(new Random(seed)::nextDouble, dim, radius);
    }

    public static SphereRandomPointGenerator newGenerator(RandomGenerator rand, int dim, double radius){
        return new ScaleSphereRandomPointGenerator(newGenerator(rand, dim), radius);
    }
}