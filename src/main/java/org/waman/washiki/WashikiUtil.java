package org.waman.washiki;

final class WashikiUtil {

    private WashikiUtil(){}

    static final double TWO_PI = 2.0 * Math.PI;

    static void scale(double[] x, double scale, int length){
        for(int i = 0; i < length; i++){
            x[i] *= scale;
        }
    }
}