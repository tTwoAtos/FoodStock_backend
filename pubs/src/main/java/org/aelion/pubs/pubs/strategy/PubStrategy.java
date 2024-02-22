package org.aelion.pubs.pubs.strategy;

public class PubStrategy implements Strategy {

    PubStrategy pubStrategy;

    @Override
    public void setStrategy(PubStrategy strategy) {
        pubStrategy = strategy;
    }

    @Override
    public void getPubProduct() {
        pubStrategy.getPubProduct();
    }
}
