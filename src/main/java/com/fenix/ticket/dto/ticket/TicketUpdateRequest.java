package com.fenix.ticket.dto.ticket;

import lombok.Data;

@Data
public class TicketUpdateRequest {
    private Long id;
    private String title;
    private String description;
    private String status;
}
