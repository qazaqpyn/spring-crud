package com.example.nobsv2.nobsv2.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/open")
    public String open() {
        return "OPEN";
    }

    @GetMapping("/closed")
    public String closed() {
        return "Closed";
    }

    @PreAuthorize("hasRole('superuser')")
    @GetMapping("/special")
    public String special() {
        return "Special";
    }

    @PreAuthorize("hasRole('basicuser') or hasRole('superuser')")
    @GetMapping("/basic")
    public String basic() {
        return "Basic";
    }
}
