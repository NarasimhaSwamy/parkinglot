package com.gojek.parkinglot.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {

    private ParkingLot parkingLot;

    @Before
    public void setUp(){
        parkingLot = new ParkingLot(8);
    }

    @Test
    public void testParkingLotInitialization(){
        Assert.assertEquals(8, parkingLot.getAvailableSlotsCount());
        Assert.assertEquals(true, parkingLot.hasFreeSlots());
    }

    @Test
    public void testParkingLotSlotAllocation(){
        Assert.assertEquals(1, parkingLot.allocateSlot());
        Assert.assertEquals(2, parkingLot.allocateSlot());
        Assert.assertEquals(3, parkingLot.allocateSlot());
        Assert.assertEquals(4, parkingLot.allocateSlot());

        parkingLot.markSlotAvailable(3);
        Assert.assertEquals(3, parkingLot.allocateSlot());
        Assert.assertEquals(5, parkingLot.allocateSlot());

        parkingLot.markSlotAvailable(2);
        Assert.assertEquals(2, parkingLot.allocateSlot());
    }

    @Test
    public void testParkingLotSlotNotAvailable(){
        this.parkingLot = new ParkingLot(2);
        Assert.assertEquals(1, parkingLot.allocateSlot());
        Assert.assertEquals(2, parkingLot.allocateSlot());
        Assert.assertEquals(-1, parkingLot.allocateSlot());

        parkingLot.markSlotAvailable(2);
        Assert.assertEquals(2, parkingLot.allocateSlot());
        Assert.assertEquals(-1, parkingLot.allocateSlot());

    }
}
