package org.aelion.pubs.pubs;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PubService {
    List<Pub> getAll();

    ResponseEntity<?> getById(String code);

    ResponseEntity<?> addedToCommunity(String code);
}
