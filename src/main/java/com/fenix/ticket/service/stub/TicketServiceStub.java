package com.fenix.ticket.service.stub;

import com.fenix.ticket.dto.*;
import com.fenix.ticket.dto.ticket.*;
import com.fenix.ticket.service.TicketService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Profile("stub")
@Service
public class TicketServiceStub extends TicketService {
    public TicketServiceStub() { super(null); }

    @Override
    public TicketCreateResponse createTicket(TicketCreateRequest req) {
        var resp = new TicketCreateResponse();
        resp.setSuccess(true);
        resp.setTicketId(9999L); // stub id
        resp.setMessage("Stub: Ticket created");
        return resp;
    }

    @Override
    public TicketListResponse listTickets() {
        var resp = new TicketListResponse();
        List<TicketInfo> tickets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            TicketInfo info = new TicketInfo();
            info.setId((long) i);
            info.setTitle("Stub Ticket " + i);
            info.setDescription("This is a stub ticket #" + i);
            info.setStatus("OPEN");
            tickets.add(info);
        }
        resp.setTickets(tickets);
        resp.setSuccess(true);
        resp.setMessage("Stub: list");
        return resp;
    }

    @Override
    public GenericResponse updateTicket(TicketUpdateRequest req) {
        var resp = new GenericResponse();
        resp.setSuccess(true);
        resp.setMessage("Stub: updated ticket " + req.getId());
        return resp;
    }

    @Override
    public GenericResponse deleteTicket(TicketDeleteRequest req) {
        var resp = new GenericResponse();
        resp.setSuccess(true);
        resp.setMessage("Stub: deleted ticket " + req.getTicketId());
        return resp;
    }
}
