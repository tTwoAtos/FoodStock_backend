package org.aelion.pubs.pubs;

import org.aelion.pubs.pubs.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/pubs", produces = MediaType.APPLICATION_JSON_VALUE)
public class PubController {
    @Autowired
    private PubService service;


    /**
     * Get a suggestion of a product depending on community alimentary habits
     *
     * @param communityId
     * @return
     */
    @GetMapping("/{communityId}")
    public ResponseEntity<?> getPub(@PathVariable String communityId) throws Exception {
        ProductDto pub = service.getPubProduct(communityId);

        return new ResponseEntity<>(pub, HttpStatus.OK);
    }
}
