package org.waman.washiki;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class SphereRandomGeneratorTest {

    @Test
    public void testDim1GeneratorCreatesLength2Array(){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(1);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(2));
    }

    @Test
    public void testDim2GeneratorCreatesLength3Array(){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(2);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(3));
    }

    @Test
    public void testDim3GeneratorCreatesLength4Array(){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(3);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(4));
    }
}
