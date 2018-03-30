package com.gojek.parkinglot.impl.service;

import com.gojek.parkinglot.domain.ParkingLot;
import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;
import com.gojek.parkinglot.domain.vehicle.Vehicle;
import com.gojek.parkinglot.filter.TicketFilterInvoker;
import com.gojek.parkinglot.service.ParkingLotTicketingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ParkingLotTicketingServiceImpl implements ParkingLotTicketingService {

    private ParkingLot parkingLot;
    private List<Ticket> tickets;
    private List<Ticket> usedTickets;
    private TicketFilterInvoker ticketFilterInvoker;

    public ParkingLotTicketingServiceImpl(int parkingLotSlots){
        this.parkingLot = new ParkingLot(parkingLotSlots);
        this.tickets = new ArrayList<>(parkingLotSlots);
        this.usedTickets = new ArrayList<>();
        this.ticketFilterInvoker = new TicketFilterInvoker();
    }

    @Override
    public Optional<Ticket> allocateSlot(Vehicle vehicle) {
        int slotId = parkingLot.allocateSlot();
        if(slotId != -1) {
            Ticket ticket = (new Ticket.Builder(slotId, vehicle.getRegistrationPlate(), vehicle.getColor())).build();
            tickets.add(ticket);
            return Optional.of(ticket);
        }
        return Optional.empty();
    }

    @Override
    public void unAllocateSlot(int parkingSlotId) {
        Ticket matchedTicket = tickets.stream()
                .filter(ticket -> parkingSlotId == ticket.getParkingSlotId())
                .collect(Collectors.toList())
                .get(0);
        unAllocateSlot(matchedTicket);
    }

    @Override
    public void unAllocateSlot(Ticket ticket) {
        usedTickets.add(ticket);
        tickets.remove(ticket);
        parkingLot.markSlotAvailable(ticket.getParkingSlotId());
    }

    @Override
    public List<Ticket> filterTickets(TicketFilterDTO ticketFilterDTO) {
        return this.ticketFilterInvoker.invoke(this.tickets, ticketFilterDTO);
    }
}
