package org.aelion.communities.invitations;

import org.aelion.communities.invitations.dto.requests.InvitationCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/communities/invitations", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvitationController {
    @Autowired
    private InvitationService service;


    @DeleteMapping("/{id}")
    public boolean deleteInvitation(@PathVariable Long id) {
        return service.deleteInvitation(id);
    }

    @GetMapping("/community/{communityId}")
    public List<InvitationEntity> getAllByCommunityId(@PathVariable Integer communityId) {
        return service.getAllByCommunityId(communityId);
    }

    @GetMapping("/{code}/user/{email}")
    public InvitationEntity getByCode(@PathVariable String code, @PathVariable String email) {
        return service.getByCodeAndEmail(code, email);
    }

    @PostMapping
    public InvitationEntity createInvitation(@RequestBody InvitationCreationRequest invitation) {
        return service.createInvitation(invitation);
    }

    @PostMapping("/{code}")
    public boolean validateInvitation(@PathVariable String code) {
        return service.validateInvitation(code);
    }
}
