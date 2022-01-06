package com.jwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/protected")
public class HelloWorldController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("hello/admin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/manager")
    @PreAuthorize("hasAnyRole('MANAGER')")
    public String helloManager() {
        return "Hello Manager!";
    }
}
