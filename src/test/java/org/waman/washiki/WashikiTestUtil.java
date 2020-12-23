package org.waman.washiki;

import java.util.Arrays;

final class WashikiTestUtil {

    private WashikiTestUtil(){}

    public static double norm(double[] x){
        return Arrays.stream(x).map(a -> a*a).sum();
    }
}
