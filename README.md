# Welcome to ParkingLot - Glad to have you here.

ParkingLot manages parking slots and provides ability to get information by 
1. Slot
2. Parked vehicle properties

### Prerequisites
* Java 1.8
* Maven

### Hot to build
* With test cases **`mvn package`**
* Without test cases **`mvn package -DskipTests`**

### How to run

* **`java -cp target/parkinglot.jar com.gojek.parkinglot.ParkingLotApplication [file_name]`**

    OR 

    you can run using parking_lot script
* **`parking_lot [file_name]`**

Commands that needs to be executed can be provided one after the other or file with all commands.

Example: 

#### Commands in file:
* run **`./parking_lot parking_lot.txt`**

#### Commands as bash inputs:
* run **`./parking_lot`**
* Next enter commands

    Example : 
    
    create_parking_lot 6; for creating a parking lot with 6 parking spaces

* **Commands**

Command | Description | Example |
--- | --- | --- |
create_parking_lot | create parking lot | create_parking_lot 6 |
park | park vehicle | park KA-01-HH-1234 White |
leave | remove vehicle from parking lot | leave 4 |
status | find status of the parkinglot | status |
registration_numbers_for_cars_with_colour | filters cars by color and gets registration numbers| registration_numbers_for_cars_with_colour White |
slot_numbers_for_cars_with_colour | filters cars by color and gets parked slot numbers| slot_numbers_for_cars_with_colour White |
slot_number_for_registration_number | filters cars by registration number and gets slot numbers| slot_number_for_registration_number MH-04-AY-1111 |

Note : create_parking_lot should be the first command that should be executed, else will result in unexpected behavior of the application.

### Maintainer

* [Narasimha Swamy Ardhani](mailto:swamy.hajeed@gmail.com)


