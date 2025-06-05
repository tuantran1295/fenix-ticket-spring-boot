package com.fenix.ticket.dto.ticket;

import lombok.Data;

@Data
public class TicketCreateResponse {
    private boolean success;
    private String message;
    private Long ticketId;
}
