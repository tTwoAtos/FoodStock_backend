package org.aelion.pubs.pubs.pubStrategies;

import org.aelion.pubs.pubs.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PubGenerator implements PubStrategy {
    private PubStrategy pubStrategy;

    public PubGenerator() {
    }

    public PubGenerator(PubStrategy strategy) {
        this.pubStrategy = strategy;
    }

    public void setStrategy(PubStrategy pubStrategy) {
        this.pubStrategy = pubStrategy;
    }

    public ProductDto getPub(String communityId) throws Exception {
        if (this.pubStrategy == null)
            throw new Exception("Strategy not set");

        return this.pubStrategy.getPub(communityId);
    }

    public List<ProductDto> getPub(String communityId, int quantity) throws Exception {
        if (this.pubStrategy == null)
            throw new Exception("Strategy not set");

        List<ProductDto> res = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            res.add(this.pubStrategy.getPub(communityId));
        }

        return res;
    }
}
