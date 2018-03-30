package com.gojek.parkinglot.domain.vehicle;

import org.junit.Assert;
import org.junit.Test;

public class RegistrationPlateTest {

    @Test
    public void registrationNumberParsingTest(){
        String registrationNumber = "AP-05-AN-2230";
        RegistrationPlate registrationPlate = (new RegistrationPlate.Builder(registrationNumber)).build();
        Assert.assertEquals(registrationNumber, registrationPlate.toString());
    }

}
