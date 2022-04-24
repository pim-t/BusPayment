import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TripCalculatorTest {

    @Test
    void tripDuration() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        assertAll(() -> assertEquals(300, TripCalculator.tripDuration(format.parse("22-01-2018 13:00:00"), format.parse("22-01-2018 13:05:00"))),
                () -> assertEquals(0, TripCalculator.tripDuration( format.parse("22-01-2018 13:05:00"), format.parse("22-01-2018 13:05:00")))
//                () -> assertEquals(null, TripCalculator.tripDuration(null, null)))
        );

    }

    @Test
    void journeyStatus() {
        
    }

    @Test
    void tripCost() {
    }
}