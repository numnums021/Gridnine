package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();
        FlightFiltering flightFiltering = new FlightFilteringImpl();

        System.out.println("Исходный список полётов:");
        flightFiltering.printFlight(flightList);

        System.out.println("\nВылет до текущего момента времени");
        flightFiltering.printFlight(flightFiltering.filterDepartureBeforeCurrentDate(flightList));

        System.out.println("\nИмеются сегменты с датой прилёта раньше даты вылета");
        flightFiltering.printFlight(flightFiltering.filterArrivalDateBeforeDepartureDate(flightList));

        System.out.println("\nОбщее время, проведённое на земле превышает два часа");
        flightFiltering.printFlight(flightFiltering.filterNumberOfHoursOnEarth(flightList));
    }
}
