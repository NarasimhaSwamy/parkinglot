package com.gojek.parkinglot.domain.filter;

import com.gojek.parkinglot.domain.vehicle.RegistrationPlate;

import java.util.List;

public class TicketFilterDTO {
    private List<String> colors;
    private List<Integer> parkingSlotIds;
    private List<RegistrationPlate> registrationPlates;

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public List<Integer> getParkingSlotIds() {
        return parkingSlotIds;
    }

    public void setParkingSlotIds(List<Integer> parkingSlotIds) {
        this.parkingSlotIds = parkingSlotIds;
    }

    public List<RegistrationPlate> getRegistrationPlates() {
        return registrationPlates;
    }

    public void setRegistrationPlates(List<RegistrationPlate> registrationPlates) {
        this.registrationPlates = registrationPlates;
    }
}
