package com.gojek.parkinglot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParkingLotApplication {
    public static void main(String args[]){
        ParkingLotCommandExecutor executor = new ParkingLotCommandExecutor();
        if(args.length == 1) {
            List<String> commands = getComamndsFromFile(args[0]);
            commands.forEach(command -> executor.execute(command));
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input:");
            while(true){
                if (scanner.hasNext()) {
                    System.out.println("\nOutput:");
                    executor.execute(scanner.nextLine());
                    System.out.println("\nInput:");
                }
            }
        }
    }

    private static List<String> getComamndsFromFile(String fileName){
        List<String> commands = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            String command;
            while ((command = bufferedReader.readLine()) != null){
                commands.add(command);
            }
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return commands;
    }
}
