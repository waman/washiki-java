package org.waman.washiki;

public interface UnitRandomPointGenerator extends RandomPointGenerator {

    default double getRadius() {
        return 1.0;
    }
}
