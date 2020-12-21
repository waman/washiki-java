package org.waman.washiki;

import org.junit.Test;

public class SphereRandomTest {

    @Test(expected = IllegalArgumentException.class)
    public void newGeneratorMethodThrowIllegalExceptionForNegativeDim(){
        SphereRandom.newGenerator(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newGeneratorMethodThrowIllegalExceptionFor0Dim(){
        SphereRandom.newGenerator(0);
    }
}
