package com.gojek.parkinglot.filter;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;
import com.gojek.parkinglot.filter.ticket.ParkingLotSlotFilter;
import com.gojek.parkinglot.filter.ticket.RegistrationNumberFilter;
import com.gojek.parkinglot.filter.ticket.TicketFilter;
import com.gojek.parkinglot.filter.ticket.VehicleColorFilters;

import java.util.ArrayList;
import java.util.List;

public class TicketFilterInvoker {
    List<TicketFilter> ticketFilters;

    public TicketFilterInvoker(){
        ticketFilters = new ArrayList<>();
        ticketFilters.add(new VehicleColorFilters());
        ticketFilters.add(new RegistrationNumberFilter());
        ticketFilters.add(new ParkingLotSlotFilter());
    }

    public List<Ticket> invoke(List<Ticket> tickets, TicketFilterDTO ticketFilterDTO){
        List<Ticket> filteredTickets = tickets;
        for (TicketFilter ticketFilter : ticketFilters) {
            filteredTickets = ticketFilter.filter(filteredTickets, ticketFilterDTO);
        }
        return filteredTickets;
    }
}
