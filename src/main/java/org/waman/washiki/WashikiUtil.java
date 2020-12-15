package org.waman.washiki;

final class WashikiUtil {

    private WashikiUtil(){}

    static final double TWO_PI = 2.0 * Math.PI;

    static void scale(double[] x, double scale){
        for(int i = 0, n = x.length; i < n; i++){
            x[i] *= scale;
        }
    }
}