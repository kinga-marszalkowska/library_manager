package com.km.auth.services;

import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.models.History;
import com.km.auth.repositories.LoanHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanHistoryService {
    private final LoanHistoryRepository repository;

    public List<History> getLoans(int id){
        return repository.getLoans(id);
    }

    public List<History> getCurrentUserLoans(){
        return getLoans(getUserId());
    }

    public int getUserId(){
        return repository.getUserId(getCurrentUser().getUsername());
    }

    public UserDetailsEx getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetailsEx userDetails = (UserDetailsEx) authentication.getPrincipal();
            return userDetails;
        }
        return null;
    }


}
