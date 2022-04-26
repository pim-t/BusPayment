package tripServices;

import dataModels.Status;
import dataModels.Tap;
import dataModels.Trip;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AllTripsTest {

    // I acknowledge this was not a good idea - but here's test cases
    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    Tap tap1Customer1 = new Tap("1", LocalDateTime.parse("22-01-2018 13:00:00", df),  "ON","Stop1", "Company1", "Bus37", "5500005555555559");
    Tap tap2Customer1 = new Tap("2", LocalDateTime.parse("22-01-2018 13:05:00", df), "OFF", "Stop2", "Company1", "Bus37", "5500005555555559");
    Tap tap3Customer1 = new Tap("3", LocalDateTime.parse("22-01-2018 13:05:00", df), "OFF", "Stop1", "Company1", "Bus37", "5500005555555559");
    Tap tap4Customer1 = new Tap("4", LocalDateTime.parse("22-01-2018 13:04:00", df), "ON", "Stop3", "Company1", "Bus37", "5500005555555559");
    Trip trip1Customer1 = new Trip(LocalDateTime.parse("22-01-2018 13:00:00", df), LocalDateTime.parse("22-01-2018 13:05:00", df), 300, "Stop1", "Stop2", 3.25, "Company1", "Bus37","5500005555555559", Status.COMPLETE);
    Trip trip2Customer1 = new Trip(LocalDateTime.parse("22-01-2018 13:00:00", df), LocalDateTime.parse("22-01-2018 13:05:00", df), 300, "Stop1", "Stop1", 0, "Company1", "Bus37","5500005555555559", Status.CANCELLED);
    Trip trip3Customer1 = new Trip(LocalDateTime.parse("22-01-2018 13:04:00", df), null, 0, "Stop3", "INVALID", 7.30, "Company1","Bus37", "5500005555555559", Status.INCOMPLETE );

    Tap tap1Customer2 = new Tap("1", LocalDateTime.parse("22-01-2018 13:00:00", df),  "ON","Stop1", "Company1", "Bus37", "55000055555554412");
    Tap tap2Customer2 = new Tap("2", LocalDateTime.parse("22-01-2018 13:05:00", df), "OFF", "Stop2", "Company1", "Bus37", "55000055555554412");
    Tap tap3Customer2 = new Tap("3", LocalDateTime.parse("22-01-2018 13:05:00", df), "OFF", "Stop1", "Company1", "Bus37", "55000055555554412");

    Trip trip1Customer2 = new Trip(LocalDateTime.parse("22-01-2018 13:00:00", df), LocalDateTime.parse("22-01-2018 13:05:00", df), 300, "Stop1", "Stop2", 3.25, "Company1", "Bus37","55000055555554412", Status.COMPLETE);
    Trip trip2Customer2 = new Trip(LocalDateTime.parse("22-01-2018 13:00:00", df), LocalDateTime.parse("22-01-2018 13:05:00", df), 300, "Stop1", "Stop1", 0, "Company1", "Bus37","55000055555554412", Status.CANCELLED);


    @Test
    void convertTapsToTrips() {
        // One customer
        List<Tap> tapListOneCustomer = Arrays.asList(tap1Customer1, tap2Customer1, tap1Customer1, tap3Customer1, tap4Customer1);
        List<Trip> tripListOneCustomer = Arrays.asList(trip1Customer1, trip2Customer1, trip3Customer1);

        List<Tap> tapListTwoCustomers = Arrays.asList(tap1Customer1, tap1Customer2, tap2Customer1, tap2Customer2);
        List<Trip> tripListTwoCustomers = Arrays.asList(trip1Customer1, trip1Customer2);

        List<Tap> emptyTap = new ArrayList<>();
        List<Trip> emptyTrip = new ArrayList<>();

        assertAll ( () -> assertEquals(tripListOneCustomer, AllTrips.convertTapsToTrips(tapListOneCustomer)),
                () -> assertEquals(tripListTwoCustomers, AllTrips.convertTapsToTrips(tapListTwoCustomers)), // two customers
                () -> assertEquals(emptyTrip, AllTrips.convertTapsToTrips(emptyTap)) // empty list
        );

    }

    @Test
    void calculateTripFromTapPair() {

        assertAll ( () -> assertEquals(trip1Customer1, AllTrips.calculateTripFromTapPair(tap1Customer1, tap2Customer1)), // okay trip
                () -> assertEquals(trip2Customer1, AllTrips.calculateTripFromTapPair(tap1Customer1, tap3Customer1)), // cancelled trip
                () -> assertEquals(trip3Customer1, AllTrips.calculateTripFromTapPair(tap4Customer1, null)) // incomplete
        );
    }

    @Test
    void isTapPair() {
        assertAll ( () -> assertEquals(true, AllTrips.isTapPair(tap1Customer1, tap2Customer1)),
                () -> assertEquals(true, AllTrips.isTapPair(tap1Customer1, tap3Customer1)),
                () -> assertEquals(false, AllTrips.isTapPair(tap2Customer1, tap3Customer1))
        );
    }
}