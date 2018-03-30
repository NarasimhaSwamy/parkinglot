package com.gojek.parkinglot.domain.vehicle;

public abstract class Vehicle {
    private String color;
    private RegistrationPlate registrationPlate;

    public RegistrationPlate getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(RegistrationPlate registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
