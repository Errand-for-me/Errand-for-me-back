package com.errand.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainPageController {

    @GetMapping("/")
    public String homepage() {
        return "hello world";
    }
}
