package com.gondorchic.app.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ProduitTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Produit getProduitSample1() {
        return new Produit().id(1L).libelleProduit("libelleProduit1").quantiteProduit(1);
    }

    public static Produit getProduitSample2() {
        return new Produit().id(2L).libelleProduit("libelleProduit2").quantiteProduit(2);
    }

    public static Produit getProduitRandomSampleGenerator() {
        return new Produit()
            .id(longCount.incrementAndGet())
            .libelleProduit(UUID.randomUUID().toString())
            .quantiteProduit(intCount.incrementAndGet());
    }
}
