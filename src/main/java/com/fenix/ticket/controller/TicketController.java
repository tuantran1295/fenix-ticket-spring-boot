package com.fenix.ticket.controller;

import com.fenix.ticket.dto.*;
import com.fenix.ticket.dto.ticket.*;
import com.fenix.ticket.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TicketController {

    private final TicketService ticketService;
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/tickets")
    public TicketCreateResponse createTicket(@RequestBody TicketCreateRequest req) {
        return ticketService.createTicket(req);
    }

    @GetMapping("/tickets")
    public TicketListResponse listTickets() {
        return ticketService.listTickets();
    }

    @PutMapping("/tickets")
    public GenericResponse updateTicket(@RequestBody TicketUpdateRequest req) {
        return ticketService.updateTicket(req);
    }

    @DeleteMapping("/tickets")
    public GenericResponse deleteTicket(@RequestBody TicketDeleteRequest req) {
        return ticketService.deleteTicket(req);
    }
}