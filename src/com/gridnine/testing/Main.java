package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flightList = FlightBuilder.createFlights();
        FlightFiltering flightFiltering = new FlightFilteringImpl();

        System.out.println("Исходный список полётов:");
        printFlight(flightList);

        System.out.println("\nВылет до текущего момента времени");
        printFlight(flightFiltering.filterDepartureBeforeCurrentDate(flightList));

        System.out.println("\nИмеются сегменты с датой прилёта раньше даты вылета");
        printFlight(flightFiltering.filterArrivalDateBeforeDepartureDate(flightList));

        System.out.println("\nОбщее время, проведённое на земле превышает два часа");
        printFlight(flightFiltering.filterNumberOfHoursOnEarth(flightList));
    }

    public static void printFlight(List<Flight> flightList) {
        for (Flight flight : flightList) {
            System.out.println(flight.getSegments());
        }
    }
}
