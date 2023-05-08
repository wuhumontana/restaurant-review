package com.ex.base.security;

import com.ex.base.entity.User;
import com.ex.base.jpa.ApiUserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class ApiUserDetailsService implements UserDetailsService {

    @Autowired private ApiUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userRes = userRepo.findByEmail(email);

        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);

        User user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
