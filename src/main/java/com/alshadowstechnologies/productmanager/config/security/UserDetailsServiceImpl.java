package com.alshadowstechnologies.productmanager.config.security;

import com.alshadowstechnologies.productmanager.user.AppUser;
import com.alshadowstechnologies.productmanager.user.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        User.UserBuilder userDetailsBuilder = null;

        if (appUser.isPresent()) {
            final var currentUser = appUser.get();
            userDetailsBuilder = User.withUsername(username);
            userDetailsBuilder.password(currentUser.getPassword());
            userDetailsBuilder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not found");
        }

        return userDetailsBuilder.build();
    }
}
