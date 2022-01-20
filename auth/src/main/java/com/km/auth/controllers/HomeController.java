package com.km.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("user")
    public ResponseEntity user(){
        return ResponseEntity.ok("<h1>Welcome User</h1>");
    }

    @GetMapping("admin")
    public ResponseEntity admin(){
        return ResponseEntity.ok("<h1>Welcome Admin</h1>");
    }

}