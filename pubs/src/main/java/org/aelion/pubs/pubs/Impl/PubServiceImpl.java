package org.aelion.pubs.pubs.Impl;

import org.aelion.pubs.pubs.PubService;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.aelion.pubs.pubs.pubStrategies.PubGenerator;
import org.aelion.pubs.pubs.pubStrategies.RandomRelatedPubStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PubServiceImpl implements PubService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public ProductDto getPubProduct(String communityId) throws Exception {
        PubGenerator pubGenerator = new PubGenerator(new RandomRelatedPubStrategy(restTemplate));
        return pubGenerator.getPub(communityId);
    }
}

