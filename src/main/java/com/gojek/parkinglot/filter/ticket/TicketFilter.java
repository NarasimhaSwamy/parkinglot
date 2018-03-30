package com.gojek.parkinglot.filter.ticket;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;

import java.util.List;

public interface TicketFilter {
    /**
     * Filters tickets with given invoke dto.
     *
     * @param tickets tickets that should be filtered
     * @param ticketFilterDTO dto with values on which tickets should be filtered
     *
     * @return filtered tickets
     */
    List<Ticket> filter(List<Ticket> tickets, TicketFilterDTO ticketFilterDTO);
}
