package com.fenix.ticket.service;

import com.fenix.ticket.dto.ticket.*;
import com.fenix.ticket.model.Ticket;
import com.fenix.ticket.dto.*;
import com.fenix.ticket.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepo;

    public TicketService(TicketRepository repo) { this.ticketRepo = repo; }

    public TicketCreateResponse createTicket(TicketCreateRequest request) {
        var resp = new TicketCreateResponse();
        var ticket = new Ticket(null, request.getTitle(), request.getDescription(), request.getStatus());
        var saved = ticketRepo.save(ticket);
        resp.setSuccess(true);
        resp.setTicketId(saved.getId());
        resp.setMessage("Ticket created");
        return resp;
    }

    public TicketListResponse listTickets() {
        var resp = new TicketListResponse();
        var list = ticketRepo.findAll().stream().map(t -> {
            var info = new TicketInfo();
            info.setId(t.getId());
            info.setTitle(t.getTitle());
            info.setDescription(t.getDescription());
            info.setStatus(t.getStatus());
            return info;
        }).collect(Collectors.toList());
        resp.setSuccess(true);
        resp.setTickets(list);
        resp.setMessage("Tickets fetched");
        return resp;
    }

    public GenericResponse updateTicket(TicketUpdateRequest req) {
        var resp = new GenericResponse();
        var ticket = ticketRepo.findById(req.getId());
        if (ticket.isEmpty()) {
            resp.setSuccess(false); resp.setMessage("Not found");
            return resp;
        }
        var t = ticket.get();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setStatus(req.getStatus());
        ticketRepo.save(t);
        resp.setSuccess(true);
        resp.setMessage("Updated");
        return resp;
    }

    public GenericResponse deleteTicket(TicketDeleteRequest req) {
        var resp = new GenericResponse();
        if (!ticketRepo.existsById(req.getTicketId())) {
            resp.setSuccess(false); resp.setMessage("Not found");
            return resp;
        }
        ticketRepo.deleteById(req.getTicketId());
        resp.setSuccess(true);
        resp.setMessage("Deleted");
        return resp;
    }
}