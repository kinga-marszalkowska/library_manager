package com.km.auth.services;

import com.km.auth.contracts.User;
import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailExService implements UserDetailsService {
    @Autowired
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findUserByUsername(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not found " + username));
        return user.map(UserDetailsEx::new).get();
    }
}
