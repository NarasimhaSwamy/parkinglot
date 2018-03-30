package com.gojek.parkinglot.filter.ticket;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingLotSlotFilter implements TicketFilter {
    @Override
    public List<Ticket> filter(List<Ticket> tickets, TicketFilterDTO ticketFilterDTO) {
        if(ticketFilterDTO.getParkingSlotIds()  == null || ticketFilterDTO.getParkingSlotIds().size() == 0 )
            return tickets;

        return tickets.parallelStream()
                .filter(ticket -> ticketFilterDTO.getParkingSlotIds().indexOf(ticket.getParkingSlotId()) != -1)
                .collect(Collectors.toList());
    }
}
