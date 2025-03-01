package org.aelion.communities.invitations;

import org.aelion.communities.community.Community;
import org.aelion.communities.community.CommunityRepository;
import org.aelion.communities.invitations.dto.UserEntityDto;
import org.aelion.communities.invitations.dto.UserToCommunityDto;
import org.aelion.communities.invitations.dto.requests.InvitationCreationRequest;
import org.aelion.exception.BadRequestException;
import org.aelion.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvitationService {
    private String USER_API = "http://USER-SERVICE/api/v1/users/";

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public List<InvitationEntity> getAllByCommunityId(Integer communityId) {
        return invitationRepository.findAllByCommunityId(communityId);
    }

    @Transactional
    public InvitationEntity getByCodeAndEmail(String code, String email) {
        return invitationRepository.findByCodeAndUserEmail(code, email).orElseThrow(() -> new NotFoundException("Invitation not found"));
    }

    @Transactional
    public InvitationEntity createInvitation(InvitationCreationRequest creationRequest) {
        Community community = communityRepository.findById(creationRequest.getCommunityId()).orElseThrow(() -> new NotFoundException("La communauté a laquelle vous êtes invité n'existe pas")); // Check if community exists
        UserEntityDto user = restTemplate.getForObject(USER_API + creationRequest.getUserEmail() + "/email", UserEntityDto.class); // Check if user exists

        Optional<InvitationEntity> existingInvitation = invitationRepository.findByCommunityIdAndUserEmail(creationRequest.getCommunityId(), creationRequest.getUserEmail());
        if (existingInvitation.isPresent()) {
            throw new BadRequestException("User already invited to this community");
        }

        String code = generateToken(6);
        InvitationEntity invitation = new InvitationEntity(community, user, code);

        // TODO: Send email to user with code

        try {
            return invitationRepository.save(invitation);
        } catch (DuplicateKeyException e) {
            code = generateToken(6);
            invitation.setCode(code);
            return invitationRepository.save(invitation);
        }
    }

    @Transactional
    public boolean deleteInvitation(Long communityId) {
        try {
            invitationRepository.deleteById(communityId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean validateInvitation(String code) {
        InvitationEntity invitation = invitationRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Invitation not found"));

        UserToCommunityDto newUToC = new UserToCommunityDto(
                invitation.getCommunity().getId(),
                invitation.getUser().getId().toString()
        );
        UserToCommunityDto uToC = restTemplate.postForObject(USER_API + "/communities", newUToC, UserToCommunityDto.class);

        if (uToC != null) {
            invitationRepository.deleteById(invitation.getId());
            return true;
        }
        return false;
    }

    // Méthode pour générer un token de taille size
    private String generateToken(int size) {
        String authorizedChars = "0123456789";

        SecureRandom random = new SecureRandom();
        return random.ints(size, 0, authorizedChars.length())
                .mapToObj(i -> String.valueOf(authorizedChars.charAt(i)))
                .collect(Collectors.joining());
    }
}
