package org.aelion.authentication.services;

import org.aelion.authentication.entity.AuthUserEntity;
import org.aelion.authentication.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;

    // Used by Spring security during authentication
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AuthUserEntity user = authUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority(user.getRole().getSlug()))
        );
    }
}
