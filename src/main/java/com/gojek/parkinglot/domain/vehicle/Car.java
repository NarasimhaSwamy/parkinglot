package com.gojek.parkinglot.domain.vehicle;

public class Car extends Vehicle {
    public Car(RegistrationPlate registrationPlate, String color) {
        this.setRegistrationPlate(registrationPlate);
        this.setColor(color);
    }
}
