package com.fenix.ticket.service.stub;

import com.fenix.ticket.dto.login.LoginRequest;
import com.fenix.ticket.dto.login.LoginResponse;
import com.fenix.ticket.service.LoginService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("stub")
@Service
public class LoginServiceStub extends LoginService {
    public LoginServiceStub() { super(null); }

    @Override
    public LoginResponse login(LoginRequest req) {
        var resp = new LoginResponse();
        if ("admin".equals(req.getUsername()) && "admin".equals(req.getPassword())) {
            resp.setSuccess(true);
            resp.setUserId(123L);
            resp.setMessage("Stub login OK");
        } else {
            resp.setSuccess(false);
            resp.setMessage("Stub invalid user/pass");
        }
        return resp;
    }
}