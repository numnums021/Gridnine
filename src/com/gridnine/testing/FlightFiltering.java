package com.gridnine.testing;

import java.util.List;

public interface FlightFiltering {
    List<Flight> filterDepartureBeforeCurrentDate(List<Flight> flightList);

    List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flightList);

    List<Flight> filterNumberOfHoursOnEarth(List<Flight> flightList);

    void printFlight(List<Flight> flightList);


}
