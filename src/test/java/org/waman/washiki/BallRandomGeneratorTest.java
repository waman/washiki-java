package org.waman.washiki;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static java.lang.Math.sqrt;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(Theories.class)
public class BallRandomGeneratorTest {

    @DataPoints
    public static final int[] dims = {1, 2, 3, 4, 5, 6};

    @DataPoints
    public static final double[] radii = {0.5, 1.0, 3.0, 10.0};

    @Theory
    public void getDimension_method_should_return_the_proper_value(int dim, double r){
        // SetUp
        BallRandomPointGenerator sut0 = BallRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getDimension(), is(dim));

        //***** With Radius *****
        // SetUp
        BallRandomPointGenerator sut1 = BallRandom.newGenerator(dim, r);
        // Verify
        assertThat(sut1.getDimension(), is(dim));
    }

    @Theory
    public void getRadius_method_should_return_the_proper_value(int dim, double r){
        // SetUp
        BallRandomPointGenerator sut0 = BallRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getRadius(), is(1.0));

        //***** With Radius *****
        // SetUp
        BallRandomPointGenerator sut1 = BallRandom.newGenerator(dim, r);
        // Verify
        assertThat(sut1.getRadius(), is(r));
    }

    @Theory
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setRandomPoint_method_should_throw_an_ArrayIndexOutBoundsException_when_an_array_whose_length_is_less_than_dim_is_passed(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim);
        double[] x = new double[dim-1];
        // Exercise
        sut.setRandomPoint(x);
    }

    @Theory
    public void setRandomPoint_method_should_not_affect_the_array_elements_after_dim_th_minus_1(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim);
        double[] x = new double[dim+1];
        double initValue = 7.0;
        Arrays.fill(x, initValue);
        // Exercise
        sut.setRandomPoint(x);
        // Verify
        assertThat(x[dim-1], is(not(initValue)));
        assertThat(x[dim], is(initValue));
    }

    @Theory
    public void setRandomPoint_method_should_not_affect_the_array_elements_after_dim_th_minus_1_for_non_unit_radius(int dim, double r){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim, r);
        double[] x = new double[dim+1];
        double initValue = 7.0;
        Arrays.fill(x, initValue);
        // Exercise
        sut.setRandomPoint(x);
        // Verify
        assertThat(x[dim-1], is(not(initValue)));
        assertThat(x[dim], is(initValue));
    }

    @Theory
    public void newRandomPoint_method_should_create_an_array_whose_length_is_dim(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim));
    }

    @Theory
    public void newRandomPoint_method_should_create_an_array_whose_length_is_dim_for_non_unit_radius(int dim, double r){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim, r);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim));
    }

    public static final int N = 100000;
    public static final double DELTA = 2.0/sqrt(N);

    @Theory
    public void generated_points_should_have_the_proper_moments(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim);
        PointMoments pm = new PointMoments(dim, 4);
        // Exercise
        for (int i = 0; i < N; i++) {
            pm.addPoint(sut.newRandomPoint());
        }
        // Verify
        Arrays.stream(pm.moments(1)).forEach(m -> assertEquals(0.0, m, DELTA));
        Arrays.stream(pm.moments(2)).forEach(m -> assertEquals(1.0/(dim+2), m, DELTA));
        Arrays.stream(pm.moments(3)).forEach(m -> assertEquals(0.0, m, DELTA));
        Arrays.stream(pm.moments(4)).forEach(m -> assertEquals(3.0/((dim+4)*(dim+2)), m, DELTA));
    }

    @Theory
    public void generated_points_should_have_the_proper_moments_for_non_unit_radius(int dim, double r){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim, r);
        PointMoments pm = new PointMoments(dim, 4);
        double r2 = r*r;
        // Exercise
        for (int i = 0; i < N; i++) {
            pm.addPoint(sut.newRandomPoint());
        }
        // Verify
        Arrays.stream(pm.moments(1)).forEach(m -> assertEquals(0.0, m, DELTA*r));
        Arrays.stream(pm.moments(2)).forEach(m -> assertEquals(r2/(dim+2), m, DELTA*r2));
        Arrays.stream(pm.moments(3)).forEach(m -> assertEquals(0.0, m, DELTA*r*r2));
        Arrays.stream(pm.moments(4)).forEach(m -> assertEquals(3.0*r2*r2/((dim+4)*(dim+2)), m, DELTA*r2*r2));
    }
}
