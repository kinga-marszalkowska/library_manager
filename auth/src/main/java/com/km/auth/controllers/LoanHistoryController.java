package com.km.auth.controllers;

import com.km.auth.contracts.User;
import com.km.auth.contracts.UserDetailsEx;
import com.km.auth.services.LoanHistoryService;
import com.km.librarydata.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Repository
@RequiredArgsConstructor
public class LoanHistoryController {
    private final LoanHistoryService loanHistoryService;
    private final BookService bookService;

    @GetMapping("/")
    public String loans(Model model) {
        model.addAttribute("loans", loanHistoryService.getCurrentUserLoans());
        model.addAttribute("books", bookService.getBooks());
        return "loans_history";
    }

    @GetMapping("/lend")
    public void lend(){

    }

}
