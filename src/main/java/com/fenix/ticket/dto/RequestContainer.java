package com.fenix.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestContainer implements Serializable {
    private String objectName;
    private Map<String, Object> parameter = new HashMap<>();
}