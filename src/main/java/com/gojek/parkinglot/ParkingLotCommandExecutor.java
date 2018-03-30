package com.gojek.parkinglot;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.filter.TicketFilterDTO;
import com.gojek.parkinglot.domain.vehicle.Car;
import com.gojek.parkinglot.domain.vehicle.RegistrationPlate;
import com.gojek.parkinglot.impl.service.ParkingLotTicketingServiceImpl;
import com.gojek.parkinglot.service.ParkingLotTicketingService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ParkingLotCommandExecutor {
    private ParkingLotTicketingService parkingLotTicketingService;
    private static final String STATUS_ROW_MSG_FORMAT = "%s\t%s\t%s";

    public void execute(String command){
        List<String> commandTokens = Arrays.asList(command.split(" "));
        switch (commandTokens.get(0)) {
            case "create_parking_lot":
                this.parkingLotTicketingService = new ParkingLotTicketingServiceImpl(Integer.parseInt(commandTokens.get(1)));
                break;
            case "park":
                String carRegistrationNumber = commandTokens.get(1);
                String carColor = commandTokens.get(2);
                this.parkingLotTicketingService.allocateSlot(new Car(RegistrationPlate.getRegistrationPlate(carRegistrationNumber), carColor));
                break;
            case "leave":
                int slotId = Integer.parseInt(commandTokens.get(1));
                this.parkingLotTicketingService.unAllocateSlot(slotId);
                break;
            case "status":
            {
                List<Ticket> tickets = this.parkingLotTicketingService.filterTickets(new TicketFilterDTO());
                printStatus(tickets);
            }
                break;
            case "registration_numbers_for_cars_with_colour":
            {
                TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
                ticketFilterDTO.setColors(Arrays.asList(commandTokens.get(1)));
                List<Ticket> tickets = this.parkingLotTicketingService.filterTickets(ticketFilterDTO);
                printTicketProperties(tickets, "reg");
            }
                break;
            case "slot_numbers_for_cars_with_colour":
            {
                TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
                ticketFilterDTO.setColors(Arrays.asList(commandTokens.get(1)));
                List<Ticket> tickets = this.parkingLotTicketingService.filterTickets(ticketFilterDTO);
                printTicketProperties(tickets, "slot");
            }
                break;
            case "slot_number_for_registration_number":
            {
                TicketFilterDTO ticketFilterDTO = new TicketFilterDTO();
                ticketFilterDTO.setRegistrationPlates(Arrays.asList(RegistrationPlate.getRegistrationPlate(commandTokens.get(1))));
                List<Ticket> tickets = this.parkingLotTicketingService.filterTickets(ticketFilterDTO);
                printTicketProperties(tickets, "slot");
            }
            break;
            default:
                //Do Nothing
        }
    }

    private void printStatus(List<Ticket> tickets){
        System.out.println(String.format(STATUS_ROW_MSG_FORMAT, "Slot No", "Registration No.", "Colour"));
        tickets.forEach(ticket -> {
            System.out.println(String.format(STATUS_ROW_MSG_FORMAT, ticket.getParkingSlotId(), ticket.getVehicleRegistrationPlate().toString(), ticket.getVehicleColor()));
        });
    }

    private void printTicketProperties(List<Ticket> tickets, String property){
        List<Object> vals = new ArrayList<>();
        switch (property){
            case "slot" :
                tickets.forEach(ticket -> {
                    vals.add(ticket.getParkingSlotId());
                });
                break;
            case "reg":
                tickets.forEach(ticket -> {
                    vals.add(ticket.getVehicleRegistrationPlate().toString());
                });
                break;
        }
        printVals(vals);
    }

    private void printVals(List<Object> vals){
        if(vals.size() == 0 ){
            System.out.println("Not found");
            return;
        }
        String printStmt = vals.get(0).toString();
        for (int i =1; i < vals.size(); i++){
            printStmt += ", " + vals.get(i).toString();
        }
        System.out.println(printStmt);
    }

}
