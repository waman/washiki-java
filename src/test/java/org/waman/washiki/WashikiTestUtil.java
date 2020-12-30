package org.waman.washiki;

import java.util.Arrays;

final class WashikiTestUtil {

    private WashikiTestUtil(){}

    public static double norm(double[] x){
        return Arrays.stream(x).map(a -> a*a).sum();
    }
}

class PointMoments {

    private int count = 0;
    private final PowerSums[] powerSums;

    private static class PowerSums {

        // i-th power sum is the (i-1)-th element
        private final double[] powerSums;

        PowerSums(int power){
            assert power > 0;
            this.powerSums = new double[power];
        }

        public void addData(double v){
            this.powerSums[0] += v;
            double w = v;
            for (int i = 1, n = this.powerSums.length; i < n; i++) {
                w *= v;
                this.powerSums[i] += w;
            }
        }

        public double getPowerSum(int order){
            return this.powerSums[order-1];
        }
    }

    PointMoments(int dim, int order){
        this.powerSums = new PowerSums[dim];
        for (int i = 0; i < dim; i ++) {
            this.powerSums[i] = new PowerSums(order);
        }
    }

    // the argument is coordinate of a point
    public void addPoint(double[] p){
        this.count++;
        for (int i = 0; i < this.powerSums.length; i++) {
            this.powerSums[i].addData(p[i]);
        }
    }

    public double moment(int i, int order){
        return this.powerSums[i].getPowerSum(order) / this.count;
    }

    public double[] moments(int order){
        double[] ds = new double[this.powerSums.length];
        Arrays.setAll(ds, i -> this.moment(i, order));
        return ds;
    }
}