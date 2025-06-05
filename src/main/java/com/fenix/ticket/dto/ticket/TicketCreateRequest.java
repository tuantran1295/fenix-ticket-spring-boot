package com.fenix.ticket.dto.ticket;

import lombok.Data;

@Data
public class TicketCreateRequest {
    private String title;
    private String description;
    private String status;
}
