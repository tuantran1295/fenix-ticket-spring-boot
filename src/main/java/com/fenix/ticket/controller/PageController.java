package com.fenix.ticket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String root() {
        return "index"; // renders index.html from templates
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/tickets")
    public String tickets() {
        return "list-ticket";
    }

    @GetMapping("/loadtest")
    public String loadTest() {
        return "loadtest";
    }
}
