package com.gojek.parkinglot.filter;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;
import com.gojek.parkinglot.domain.vehicle.RegistrationPlate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketFilterInvokerTest {
    private static List<Ticket> tickets;
    private static TicketFilterInvoker ticketFilterInvoker;

    @BeforeClass
    public static void setUp(){
        ticketFilterInvoker = new TicketFilterInvoker();
        tickets = new ArrayList<>(8);

        // White 3, Grey 3, Orange 2
        tickets.add(getTicket(1, "AP-05-AC-2211", "White"));
        tickets.add(getTicket(2, "AP-05-AC-2212", "Grey"));
        tickets.add(getTicket(3, "AP-05-AC-2213", "Grey"));
        tickets.add(getTicket(4, "AP-05-AC-2214", "White"));
        tickets.add(getTicket(5, "AP-05-AC-2215", "Orange"));
        tickets.add(getTicket(6, "AP-05-AC-2216", "Grey"));
        tickets.add(getTicket(7, "AP-05-AC-2217", "Orange"));
        tickets.add(getTicket(8, "AP-05-AC-2218", "White"));
    }

    @Test
    public void testTicketFilterInvokerEmptyFilterDTO(){
        TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
        Assert.assertEquals(8, ticketFilterInvoker.invoke(tickets, ticketFilterDTO).size());
    }

    @Test
    public void testTicketColorFilterSearch(){
        List<String> colors = Arrays.asList("White", "Grey");
        TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
        ticketFilterDTO.setColors(colors);
        List<Ticket> filteredTickets = this.ticketFilterInvoker.invoke(tickets, ticketFilterDTO);
        Assert.assertEquals(6, filteredTickets.size());
        filteredTickets.forEach(filteredTicket -> Assert.assertTrue(colors.indexOf(filteredTicket.getVehicleColor()) != -1));
    }

    @Test
    public void testTicketRegistrationPlateSearch(){
        String registrationNumber = "AP-05-AC-2212";
        List<RegistrationPlate> registrationPlates = Arrays.asList(getRegistrationPlate(registrationNumber));

        TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
        ticketFilterDTO.setRegistrationPlates(registrationPlates);

        List<Ticket> filteredTickets = this.ticketFilterInvoker.invoke(tickets, ticketFilterDTO);
        Assert.assertEquals(1, filteredTickets.size());

        Ticket ticket = filteredTickets.get(0);
        Assert.assertEquals(registrationNumber, ticket.getVehicleRegistrationPlate().toString());
        Assert.assertEquals("Grey", ticket.getVehicleColor());
        Assert.assertEquals(2, ticket.getParkingSlotId());
    }

    @Test
    public void testTicketParkingLotSlotFilterTest(){
        TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
        ticketFilterDTO.setParkingSlotIds(Arrays.asList(5));

        List<Ticket> filteredTickets = this.ticketFilterInvoker.invoke(tickets, ticketFilterDTO);
        Assert.assertEquals(1, filteredTickets.size());

        Ticket ticket = filteredTickets.get(0);
        Assert.assertEquals("AP-05-AC-2215", ticket.getVehicleRegistrationPlate().toString());
        Assert.assertEquals("Orange", ticket.getVehicleColor());
        Assert.assertEquals(5, ticket.getParkingSlotId());
    }

    private static Ticket getTicket(int slotId, String registrationPlateStr, String color) {
        return (new Ticket.Builder(slotId, getRegistrationPlate(registrationPlateStr), color)).build();
    }

    private static RegistrationPlate getRegistrationPlate(String registrationPlateStr) {
        return (new RegistrationPlate.Builder(registrationPlateStr)).build();
    }

}
