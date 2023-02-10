package com.falsefalse.where2.security;

import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role ->
                                new SimpleGrantedAuthority(
                                        role.name()))
                        .toList());
    }
}
