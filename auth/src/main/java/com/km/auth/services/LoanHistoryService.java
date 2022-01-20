package com.km.auth.services;

import com.km.auth.contracts.LoanDto;
import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.models.History;
import com.km.auth.repositories.LoanHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanHistoryService {
    private final LoanHistoryRepository repository;

    public List<LoanDto> getLoans(int id){
        return repository.getLoans(id).stream()
                .map(loan ->new LoanDto(
                        loan.getLoanId(),
                        repository.getBookById(id).getTitle(),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        loan.getReturnDate() == null
                        )).collect(Collectors.toList());
    }

    public List<LoanDto> getCurrentUserLoans(){
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
