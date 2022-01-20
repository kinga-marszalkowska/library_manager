package com.km.auth.controllers;

import com.km.auth.contracts.User;
import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.services.LoanHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Repository
@RequiredArgsConstructor
public class LoanHistoryController {
    private final LoanHistoryService loanHistoryService;
    @GetMapping("/")
    public ResponseEntity index(){
        return ResponseEntity.ok(loanHistoryService.getCurrentUserLoans());
    }

}
