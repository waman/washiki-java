package org.waman.washiki;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BallRandomGeneratorTest {

    @Test
    public void testDim1GeneratorCreatesLength1Array(){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(1);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(1));
    }

    @Test
    public void testDim2GeneratorCreatesLength2Array(){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(2);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(2));
    }

    @Test
    public void testDim3GeneratorCreatesLength3Array(){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(3);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(3));
    }

    @Test
    public void testDim4GeneratorCreatesLength4Array(){
        // SetUp
        BallRandomPointGenerator sut = BallRandom.newGenerator(4);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(4));
    }
}
