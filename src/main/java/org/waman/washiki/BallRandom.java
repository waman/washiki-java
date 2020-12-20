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
        return new UnitBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim-1), dim);

    }
    public static RandomPointGenerator newGenerator(int dim, double radius){
        return newGenerator(Math::random, dim, radius);
    }

    public static RandomPointGenerator newGenerator(long seed, int dim, double radius){
        return newGenerator(new Random(seed)::nextDouble, dim, radius);
    }

    public static RandomPointGenerator newGenerator(RandomGenerator rand, int dim, double radius){
        return new ScaleBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim-1), radius);
    }
}