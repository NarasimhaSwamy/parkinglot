package com.gojek.parkinglot.domain;

import com.gojek.parkinglot.domain.vehicle.RegistrationPlate;

public class Ticket {
    private int parkingSlotId;
    private String vehicleColor;
    private RegistrationPlate vehicleRegistrationPlate;

    private Ticket(Builder builder){
        this.parkingSlotId = builder.getParkingSlotId();
        this.vehicleColor  = builder.getVehicleColor();
        this.vehicleRegistrationPlate = builder.getVehicleRegistrationPlate();
    }

    public int getParkingSlotId() {
        return parkingSlotId;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public RegistrationPlate getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public static class Builder {
        private int parkingSlotId;
        private RegistrationPlate vehicleRegistrationPlate;
        private String vehicleColor;

        public Builder(int parkingSlotId, RegistrationPlate vehicleRegistrationPlate, String vehicleColor) {
            this.parkingSlotId = parkingSlotId;
            this.vehicleRegistrationPlate = vehicleRegistrationPlate;
            this.vehicleColor = vehicleColor;
        }

        public Ticket  build(){
            return new Ticket(this);
        }

        public int getParkingSlotId() {
            return parkingSlotId;
        }

        public Builder setParkingSlotId(int parkingSlotId) {
            this.parkingSlotId = parkingSlotId;
            return this;
        }

        public RegistrationPlate getVehicleRegistrationPlate() {
            return vehicleRegistrationPlate;
        }

        public Builder setVehicleRegistrationPlate(RegistrationPlate vehicleRegistrationPlate) {
            this.vehicleRegistrationPlate = vehicleRegistrationPlate;
            return this;
        }

        public String getVehicleColor() {
            return vehicleColor;
        }

        public Builder setVehicleColor(String vehicleColor) {
            this.vehicleColor = vehicleColor;
            return this;
        }
    }
}
