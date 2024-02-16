package org.aelion.pubs.pubs.strategy;

public class PubStrategy implements Strategy {

    Strategy pubStrategy;

    @Override
    public void SetStrategy(Strategy strategy) {
        pubStrategy = strategy;
    }

    @Override
    public void getPub() {

    }
}
