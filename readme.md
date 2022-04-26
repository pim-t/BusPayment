Hello! Thanks for having a look at this :D - Written by Pim. 

## Run instructions

Requirements - Java 17, Maven 3.8.5. 

Run `mvn clean install` in the project directory. 

If looking to run from command line: `mvn clean compile assembly:single` to include all dependencies in the jar file. 

Run `java -jar target/BusPayment-1.0-SNAPSHOT-jar-with-dependencies.jar [input file]`

If running from IDE (such as IntelliJ)
1. Add input file at BusPayment/inputs/input.csv
2. Run application file.
3. Check output in output.csv

## Test Cases

The unit tests can be found under ../test, and are set up using JUnit5. 

These include unit tests for: 
1. TripCalculator
2. All Trips

## Assumptions

- Tap offs before tap ons are ignored
- A tap ON will always have a tap OFF pair (unless it is an incomplete trip)  
- All data is in order. 

## Improvements 

With more time, I would: 

- Change the serialisation method for constants (e.g. Stops, and TapType) to support an ENUM.
- Create a TapPair class to group TapOn and TapOff pairs that get parsed often in each function.
- Instead of test cases within the unit test files, ability to import and batch test unit tests - as it starts to get large and unwieldy. 
