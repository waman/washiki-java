package org.waman.washiki;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.waman.washiki.WashikiTestUtil.norm;

@RunWith(Enclosed.class)
public class BallRandomTest {

    @RunWith(Theories.class)
    public static class RandomGeneratorArgumentTest{

        @DataPoints
        public static final int[] dims = {1, 2, 3, 4, 5};

        @Theory
        public void newGenerator_method_with_seed_should_return_the_proper_instance(int dim){
            // SetUp
            BallRandomPointGenerator gen = BallRandom.newGenerator(19L, dim);
            for(int i = 0; i < 20; i++){
                // Exercise
                double[] x = gen.newRandomPoint();
                // Verify
                assertTrue(norm(x) <= 1.0);
            }
        }

        @Theory
        public void newGenerator_method_with_seed_should_return_the_proper_instance_for_non_unit_radius(int dim){
            // SetUp
            BallRandomPointGenerator gen = BallRandom.newGenerator(19L, dim, 2.0);
            for(int i = 0; i < 20; i++){
                // Exercise
                double[] x = gen.newRandomPoint();
                // Verify
                assertTrue(norm(x) <= 4.0);
            }
        }
    }

    @RunWith(Theories.class)
    public static class DimensionArgumentTest{

        @DataPoints
        public static final int[] dims = {-1, 0};

        @Theory
        @Test(expected = IllegalArgumentException.class)
        public void newGenerator_method_should_throw_an_IllegalException_for_non_positive_dimension(int dim) {
            // Exercise
            BallRandom.newGenerator(dim);
        }
    }
}
