package com.fenix.ticket.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public abstract class ResponseBase implements Serializable {
    private String requestName;

    public ResponseBase(RequestBase request) {
        if (request != null) {
            this.requestName = request.getRequestName();
        }
    }
}