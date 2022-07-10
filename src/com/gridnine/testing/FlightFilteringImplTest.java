package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FlightFilteringImplTest {

    private final List<Flight> flightList;
    private final FlightFiltering flightFiltering;

    FlightFilteringImplTest() {
        this.flightFiltering = new FlightFilteringImpl();
        this.flightList = new ArrayList<>();

        List<Segment> firstListSegment = new ArrayList<>(Arrays.asList(
                new Segment(LocalDateTime.now().minusDays(1), LocalDateTime.now()),
                new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().minusHours(1)),
                new Segment(LocalDateTime.now().plusHours(3), LocalDateTime.now().minusHours(4))
        ));

        List<Segment> secondListSegment = new ArrayList<>(Arrays.asList(
                new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(5)),
                new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(5))
        ));

        List<Segment> threeListSegment = new ArrayList<>(Arrays.asList(
              new Segment(LocalDateTime.now().minusHours(3), LocalDateTime.now().plusHours(5)),
              new Segment(LocalDateTime.now().plusHours(7), LocalDateTime.now().plusHours(9))
        ));

        flightList.add(new Flight(firstListSegment));
        flightList.add(new Flight(secondListSegment));
        flightList.add(new Flight(threeListSegment));
    }

    @org.junit.jupiter.api.Test
    void assertEqualsFilterDepartureBeforeCurrentDate() {
        List<Flight> fList = new ArrayList<>();
        fList.add(flightList.get(1));
        assertEquals(fList, flightFiltering.filterDepartureBeforeCurrentDate(flightList));
    }

    @org.junit.jupiter.api.Test
    void assertEqualsFilterArrivalDateBeforeDepartureDate() {
        List<Flight> fList = new ArrayList<>();
        fList.add(flightList.get(0));
        assertEquals(fList, flightFiltering.filterArrivalDateBeforeDepartureDate(flightList));
    }

    @org.junit.jupiter.api.Test
    void assertEqualsFilterNumberOfHoursOnEarth() {
        List<Flight> fList = new ArrayList<>(Arrays.asList(
                flightList.get(0),
                flightList.get(2)
        ));

        assertEquals(fList, flightFiltering.filterNumberOfHoursOnEarth(flightList));
    }
}