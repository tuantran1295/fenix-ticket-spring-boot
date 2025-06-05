package com.fenix.ticket.dto.login;

import lombok.Data;

@Data
public class LoginResponse {
    private boolean success;
    private String message;
    private Long userId;
}