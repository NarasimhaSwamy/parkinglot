package com.gojek.parkinglot.service;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;
import com.gojek.parkinglot.domain.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

/**
 *
 * Service responsible for managing parking slots and issuing tickets.
 *
 * @author ardhani
 */
public interface ParkingLotTicketingService {

    /**
     *  Allocates parking slot for the incoming vehicle.
     *  If not slot is available then will be returning null.
     *
     * @param vehicle vehicle arrived for parking
     * @return Ticket if present else returns null
     */
    Optional<Ticket> allocateSlot(Vehicle vehicle);

    /**
     *  Marks the issued parking slot available.
     *
     * @param slotId issued slot id
     */
    void unAllocateSlot(int slotId);

    /**
     *  Marks the issued parking slot available.
     *
     * @param ticket ticket issued at parking entrance
     */
    void unAllocateSlot(Ticket ticket);

    /**
     * Filters tickets by provided dto
     *
     * @param ticketFilterDTO dto with filter values
     * @return filtered tickets
     */
    List<Ticket> filterTickets(TicketFilterDTO ticketFilterDTO);

}
