package org.waman.washiki;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public class BallRandomGeneratorTest {

    @DataPoints
    public static int[] dims = {1, 2, 3, 4, 5};

    @Theory
    public void getDimensionMethod_ReturnsTheProperValue(int dim){
        // SetUp
        BallRandomPointGenerator sut0 = BallRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getDimension(), is(dim));

        //***** With Radius *****
        // SetUp
        BallRandomPointGenerator sut1 = BallRandom.newGenerator(dim, 2.0);
        // Verify
        assertThat(sut1.getDimension(), is(dim));
    }

    @Theory
    public void getRadiusMethod_ReturnsTheProperValue(int dim){
        // SetUp
        BallRandomPointGenerator sut0 = BallRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getRadius(), is(1.0));

        //***** With Radius *****
        // SetUp
        BallRandomPointGenerator sut1 = BallRandom.newGenerator(dim, 2.0);
        // Verify
        assertThat(sut1.getRadius(), is(2.0));
    }

    @Theory
    public void newRandomPointMethod_CreatesArrayWhoseLengthIsDim(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim));
    }

    @Theory
    public void newRandomPointMethodWithRadius_CreatesArrayWhoseLengthIsDim(int dim){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(dim, 2.0);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim));
    }
}
