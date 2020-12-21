package org.waman.washiki;

import java.util.Random;

import static java.lang.Math.pow;
import static org.waman.washiki.WashikiUtil.*;

public class BallRandom {

    public static BallRandomPointGenerator newGenerator(int dim){
        return newGenerator(Math::random, dim);
    }

    public static BallRandomPointGenerator newGenerator(long seed, int dim){
        return newGenerator(new Random(seed)::nextDouble, dim);
    }

    public static BallRandomPointGenerator newGenerator(RandomGenerator rand, int dim){
        if (dim <= 0) {
            throw new IllegalArgumentException("The dimension argument must be positive: " + dim);
        } else if (dim == 1) {
            return new UnitBall1DRandomPointGenerator(rand);
        } else {
            return new UnitBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim - 1));
        }
    }

    public static BallRandomPointGenerator newGenerator(int dim, double radius){
        return newGenerator(Math::random, dim, radius);
    }

    public static BallRandomPointGenerator newGenerator(long seed, int dim, double radius){
        return newGenerator(new Random(seed)::nextDouble, dim, radius);
    }

    public static BallRandomPointGenerator newGenerator(RandomGenerator rand, int dim, double radius){
        if (dim <= 0) {
            throw new IllegalArgumentException("The dimension argument must be positive: " + dim);
        } else if (dim == 1) {
            return new ScaleBall1DRandomPointGenerator(rand, radius);
        } else {
            return new ScaleBallRandomPointGenerator(SphereRandom.newGenerator(rand, dim - 1), radius);
        }
    }
}