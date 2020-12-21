package org.waman.washiki;

import org.junit.Test;

public class BallRandomTest {

    @Test(expected = IllegalArgumentException.class)
    public void newGeneratorMethodThrowIllegalExceptionForNegativeDim(){
        BallRandom.newGenerator(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newGeneratorMethodThrowIllegalExceptionFor0Dim(){
        BallRandom.newGenerator(0);
    }
}
