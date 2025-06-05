package com.fenix.ticket.dto.ticket;

import lombok.Data;

@Data
public class TicketListResponse {
    private boolean success;
    private String message;
    private java.util.List<TicketInfo> tickets;
}