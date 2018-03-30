package com.gojek.parkinglot.filter.ticket;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationNumberFilter implements TicketFilter{
    @Override
    public List<Ticket> filter(List<Ticket> tickets, TicketFilterDTO ticketFilterDTO) {
        if(ticketFilterDTO.getRegistrationPlates() == null || ticketFilterDTO.getRegistrationPlates().size() ==0)
            return tickets;

        return tickets.stream()
                .filter(ticket -> ticketFilterDTO.getRegistrationPlates().indexOf(ticket.getVehicleRegistrationPlate()) != -1)
                .collect(Collectors.toList());
    }
}
