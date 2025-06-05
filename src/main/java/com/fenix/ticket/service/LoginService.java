package com.fenix.ticket.service;

import com.fenix.ticket.repository.UserRepository;
import com.fenix.ticket.model.User;
import com.fenix.ticket.dto.login.LoginRequest;
import com.fenix.ticket.dto.login.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final UserRepository userRepository;

    public LoginService(UserRepository repo) {
        this.userRepository = repo;
    }

    public LoginResponse login(LoginRequest request) {
        var resp = new LoginResponse();
        var userOpt = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (userOpt.isPresent()) {
            resp.setSuccess(true);
            resp.setUserId(userOpt.get().getId());
            resp.setMessage("Login successful");
        } else {
            resp.setSuccess(false);
            resp.setMessage("Invalid username or password.");
        }
        return resp;
    }
}