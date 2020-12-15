package org.waman.washiki;

import org.junit.Test;
import org.waman.washiki.SphereRandom;

import java.util.stream.Stream;

public class SphereRandomGeneratorTest {

    @Test
    public void testInstantiation(){
        RandomPointGenerator rand = SphereRandom.newGenerator(4);
        Stream.generate(rand).limit(10).forEach(System.out::println);
    }
}
