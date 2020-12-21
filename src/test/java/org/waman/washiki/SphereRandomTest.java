package org.waman.washiki;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class SphereRandomTest {

    @DataPoints
    public static int[] dims = {-1, 0};

    @Theory
    @Test(expected = IllegalArgumentException.class)
    public void newGeneratorMethod_ThrowIllegalExceptionForNonPositiveDimension(int dim) {
        SphereRandom.newGenerator(dim);
    }
}
