package org.waman.washiki;

final class WashikiUtil {

    private WashikiUtil(){}

    static final double TWO_PI = 2.0 * Math.PI;

    static void scale(double[] x, double scale, int start, int length){
        for(int i = start, n = start+length; i < n; i++){
            x[i] *= scale;
        }
    }
}