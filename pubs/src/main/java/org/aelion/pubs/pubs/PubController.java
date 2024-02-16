package org.aelion.pubs.pubs;

import org.aelion.pubs.pubs.dto.CategoryDto;
import org.aelion.pubs.pubs.dto.CommunityDto;
import org.aelion.pubs.pubs.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/pubs")
public class PubController {
    @Autowired
    private PubService service;


    /**
     * Get a suggestion of a product depending on community alimentary habits
     * @param communityId
     * @return
     */
    @GetMapping("/{communityId}")
    public void getPub(@PathVariable String communityId) {
        service.getPubProduct(communityId);
    }

}
