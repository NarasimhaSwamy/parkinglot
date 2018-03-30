package com.gojek.parkinglot.impl.service;

import com.gojek.parkinglot.domain.Ticket;
import com.gojek.parkinglot.domain.vehicle.Car;
import com.gojek.parkinglot.domain.vehicle.RegistrationPlate;
import com.gojek.parkinglot.domain.vehicle.Vehicle;
import com.gojek.parkinglot.service.ParkingLotTicketingService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class ParkingLotTicketingServiceImplTest {
    private ParkingLotTicketingService parkingLotTicketingService;

    @Before
    public void setParkingLotTicketingService(){
        this.parkingLotTicketingService = new ParkingLotTicketingServiceImpl(2);
    }

    @Test
    public void testParkingSlotAllocationTest(){
        this.parkingLotTicketingService = new ParkingLotTicketingServiceImpl(2);
        Vehicle vehicle1 = prepCar("AP-05-AN-2231", "White");
        Vehicle vehicle2 = prepCar("AP-05-AN-2232", "Grey");

        Ticket t1 = getParkingTicket(vehicle1);
        Assert.assertEquals(1, t1.getParkingSlotId());

        Ticket t2 = getParkingTicket(vehicle2);
        Assert.assertEquals(2, t2.getParkingSlotId());

        Vehicle vehicle3 = prepCar("AP-05-AN-2233", "Orange");
        Assert.assertNull(getParkingTicket(vehicle3));
    }

    @Test
    public void testParkingAllocationAndUnallocationTest(){
        this.parkingLotTicketingService = new ParkingLotTicketingServiceImpl(4);
        Vehicle vehicle1 = prepCar("AP-05-AN-2231", "White");
        Vehicle vehicle2 = prepCar("AP-05-AN-2232", "Grey");
        Vehicle vehicle3 = prepCar("AP-05-AN-2233", "Orange");
        Vehicle vehicle4 = prepCar("AP-05-AN-2234", "Orange");
        Vehicle vehicle5 = prepCar("AP-05-AN-2235", "Orange");

        Ticket t1 = getParkingTicket(vehicle1);
        Assert.assertEquals(1, t1.getParkingSlotId());

        Ticket t2 = getParkingTicket(vehicle2);
        Assert.assertEquals(2, t2.getParkingSlotId());

        Ticket t3 = getParkingTicket(vehicle3);
        Assert.assertEquals(3, t3.getParkingSlotId());

        this.parkingLotTicketingService.unAllocateSlot(t2);
        this.parkingLotTicketingService.unAllocateSlot(1);

        Ticket t4 = getParkingTicket(vehicle4);
        Assert.assertEquals(1, t4.getParkingSlotId());

        Ticket t5 = getParkingTicket(vehicle5);
        Assert.assertEquals(2, t5.getParkingSlotId());
    }

    private Ticket getParkingTicket(Vehicle vehicle){
        Optional<Ticket> optTicket = this.parkingLotTicketingService.allocateSlot(vehicle);
        if(optTicket.isPresent())
            return optTicket.get();
        return null;
    }

    private Vehicle prepCar(String regNum, String color){
        return new Car((new RegistrationPlate.Builder(regNum)).build(), color);
    }
}
