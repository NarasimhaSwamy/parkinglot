package com.gojek.parkinglot;

import org.junit.Test;

/**
 * Test to verify the execution of commands are happening correctly or not.
 * Here we are not asserting anything, will be checking the output.
 */
public class ParkingLotCommandExecutorTest {

    @Test
    public void textCommandExecutor(){
        ParkingLotCommandExecutor executor = new ParkingLotCommandExecutor();
        executor.execute("create_parking_lot 6");
        executor.execute("park KA-01-HH-1234 White");
        executor.execute("park KA-01-HH-1236 Grey");
        executor.execute("park KA-01-HH-1256 Grey");
        executor.execute("park KA-01-HH-1235 White");
        executor.execute("leave 4");
        executor.execute("park KA-01-HH-1237 Orange");
        executor.execute("park KA-01-HH-1238 Green");
        executor.execute("slot_numbers_for_cars_with_colour White");
        executor.execute("registration_numbers_for_cars_with_colour Grey");
        executor.execute("slot_number_for_registration_number MH-04-AY-1111");
        executor.execute("status");
    }
}
