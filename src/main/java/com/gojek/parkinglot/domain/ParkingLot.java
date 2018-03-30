package com.gojek.parkinglot.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingLot {

    private List<Slot> slots;
    private static final String PARKING_LOT_INITIALIZATION_MSG_FORMAT = "Created a parking lot with %s slots";
    private static final String PARKING_LOT_SLOT_ALLOCATION_MSG_FORMAT = "Allocated slot number: %s";
    private static final String PARKING_LOT_SLOT_UNALLOCATION_MSG_FORMAT = "Slot number %s is free";
    private static final String NO_SLOTS_AVAILABLE_MSG = "Sorry, parking lot is full";

    public ParkingLot(int parkingSlotsCount){
        List<Slot> tempSlosts = new ArrayList<Slot>(parkingSlotsCount);
        for(int i=1; i < (parkingSlotsCount + 1) ; i++){
            tempSlosts.add(new Slot(i, true));
        }
        this.slots = new ArrayList<>(tempSlosts);
        System.out.println(String.format(PARKING_LOT_INITIALIZATION_MSG_FORMAT, parkingSlotsCount));
    }

    public int allocateSlot(){
        if(!hasFreeSlots()){
            System.out.println(NO_SLOTS_AVAILABLE_MSG);
            return  -1;
        }
        Slot slot =  getAvailableSlots().get(0);
        slot.setAvailable(false);
        System.out.println(String.format(PARKING_LOT_SLOT_ALLOCATION_MSG_FORMAT, slot.getSlotId()));
        return slot.getSlotId();
    }

    public void markSlotAvailable(int slotId){
        System.out.println(String.format(PARKING_LOT_SLOT_UNALLOCATION_MSG_FORMAT, slotId));
        this.slots
                .stream()
                .filter(slot -> slotId == slot.getSlotId())
                .forEach(slot -> slot.setAvailable(true));
    }

    public boolean hasFreeSlots(){
        return getAvailableSlotsCount() > 0 ? true : false;
    }

    public int getAvailableSlotsCount(){
         return getAvailableSlots().size();
    }

    private List<Slot> getAvailableSlots(){
        return this.slots
                .stream()
                .filter(slot -> slot.isAvailable())
                .collect(Collectors.toList());
    }

    private class Slot {
        int slotId;
        boolean isAvailable;

        public Slot(int slotId, boolean isAvailable) {
            this.slotId = slotId;
            this.isAvailable = isAvailable;
        }

        public int getSlotId() {
            return slotId;
        }

        public void setSlotId(int slotId) {
            this.slotId = slotId;
        }

        public boolean isAvailable() {
            return isAvailable;
        }

        public void setAvailable(boolean available) {
            isAvailable = available;
        }
    }
}
