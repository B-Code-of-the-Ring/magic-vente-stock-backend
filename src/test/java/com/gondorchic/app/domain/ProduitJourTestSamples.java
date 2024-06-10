package com.gondorchic.app.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class ProduitJourTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static ProduitJour getProduitJourSample1() {
        return new ProduitJour().id(1L);
    }

    public static ProduitJour getProduitJourSample2() {
        return new ProduitJour().id(2L);
    }

    public static ProduitJour getProduitJourRandomSampleGenerator() {
        return new ProduitJour().id(longCount.incrementAndGet());
    }
}
