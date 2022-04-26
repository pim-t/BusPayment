import dataModels.Tap;
import io.CsvIO;
import tripServices.AllTripsHandler;

import java.io.File;
import java.util.List;


public class Application {
    public static void main(String[] args) throws Exception {
        File inputFile = new File("BusPayment/inputs/input.csv");
        if (args.length > 0) {
            inputFile = new File(args[0]);
        }
        // improvements to this can also be made
        File outputFile = new File("output.csv");

        List<Tap> taps = CsvIO.readTapsFromCsv(inputFile);
        var trips = AllTripsHandler.convertTapsToTrips(taps);
        CsvIO.writeTripsToCsv(outputFile, trips);
    }
}
