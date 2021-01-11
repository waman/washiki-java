package org.waman.washiki;

@FunctionalInterface
public interface RandomGenerator{

    /**
     * Return a random number between 0 and 1.
     * @return a random number between 0 and 1
     */
    double nextDouble();

    /**
     * Return a random number between 0 and <code>width</code>1.
     * @param width -
     * @return a random number between 0 and <code>width</code>
     */
    default double nextDouble(double width){
        return width * nextDouble();
    }

    /**
     * Return a random number between <code>min</code> and <code>min+width</code>.
     * @param min -
     * @param width -
     * @return a random number between <code>min</code> and <code>min+width</code>
     */
    default double nextDouble(double min, double width){
        return min + width * nextDouble();
    }
}
