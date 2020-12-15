package org.waman.washiki;

@FunctionalInterface
public interface RandomGenerator{

    /**
     * Return a random number between 0 and 1.
     */
    double nextDouble();

    /**
     * Return a random number between 0 and <code>width</code>.
     */
    default double nextDouble(double width){
        return width * nextDouble();
    }

    /**
     * Return a random number between <code>min</code> and <code>min+width</code>.
     */
    default double nextDouble(double min, double width){
        return min + width * nextDouble();
    }

    /**
     * Return a random number between <code>min</code> and <code>max</code>.
     * This method is equivalent to <code>nextDouble(min, max - min)</code>.
     */
    default double nextDoubleIn(double min, double max){
        return nextDouble(min, max-min);
    }
}
