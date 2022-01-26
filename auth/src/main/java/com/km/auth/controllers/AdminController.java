package com.km.auth.controllers;

import com.km.auth.services.AdminService;
import com.km.auth.services.LoanHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping("admin")
    public String approve(Model model, @RequestParam(value= "approve", required = false) String loanId){
        if(loanId != null && !loanId.equals("")){
            adminService.approveReturn(loanId);
        }
        model.addAttribute("loans", adminService.getAllLoans());
        return "approveLoans";

    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

}