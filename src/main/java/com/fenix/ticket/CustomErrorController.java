package com.fenix.ticket;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Get error status
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        if (statusCode != null && statusCode == HttpStatus.NOT_FOUND.value()) {
            return "redirect:/login";
        }
        // Handle other errors normally or show error page
        return "redirect:/login";
    }
}
