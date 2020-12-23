package org.waman.washiki;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Theories.class)
public class SphereRandomGeneratorTest {

    @DataPoints
    public static int[] dims = {1, 2, 3, 4, 5};

    @Theory
    public void getDimension_method_should_return_the_proper_value(int dim){
        // SetUp
        SphereRandomPointGenerator sut0 = SphereRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getDimension(), is(dim));

        //***** With Radius *****
        // SetUp
        SphereRandomPointGenerator sut1 = SphereRandom.newGenerator(dim, 2.0);
        // Verify
        assertThat(sut1.getDimension(), is(dim));
    }

    @Theory
    public void getRadius_method_should_return_the_proper_value(int dim){
        // SetUp
        SphereRandomPointGenerator sut0 = SphereRandom.newGenerator(dim);
        // Verify
        assertThat(sut0.getRadius(), is(1.0));

        //***** With Radius *****
        // SetUp
        SphereRandomPointGenerator sut1 = SphereRandom.newGenerator(dim, 2.0);
        // Verify
        assertThat(sut1.getRadius(), is(2.0));
    }

    @Theory
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void setRandomPoint_method_should_throw_an_ArrayIndexOutBoundsException_when_an_array_whose_length_is_less_than_dim_is_passed(int dim){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(dim);
        double[] x = new double[dim-1];
        // Exercise
        sut.setRandomPoint(x);
    }

    @Theory
    public void setRandomPoint_method_should_not_affect_the_array_elements_after_dim_th(int dim){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(dim);
        double[] x = new double[dim+2];
        Arrays.fill(x, 10.0);
        // Exercise
        sut.setRandomPoint(x);
        // Verify
        assertThat(x[dim], is(not(10.0)));
        assertThat(x[dim+1], is(10.0));
    }

    @Theory
    public void setRandomPoint_method_should_not_affect_the_array_elements_after_dim_th_for_non_unit_radius(int dim){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(dim, 2.0);
        double[] x = new double[dim+2];
        Arrays.fill(x, 10.0);
        // Exercise
        sut.setRandomPoint(x);
        // Verify
        assertThat(x[dim], is(not(10.0)));
        assertThat(x[dim+1], is(10.0));
    }

    @Theory
    public void newRandomPoint_method_should_create_an_array_whose_length_is_dim_plus_1(int dim){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(dim);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim+1));
    }

    @Theory
    public void newRandomPoint_method_should_create_array_whose_length_is_dim_plus_1_for_non_unit_radius(int dim){
        // SetUp
        SphereRandomPointGenerator sut = SphereRandom.newGenerator(dim, 2.0);
        // Exercise
        double[] x = sut.newRandomPoint();
        // Verify
        assertThat(x.length, is(dim+1));
    }
}
