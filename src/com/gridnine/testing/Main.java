package com.gridnine.testing;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("Исходный список перелётов");
        System.out.println(flightList);

        System.out.println("\nВылет до текущего момента времени");
        flightList = FlightFiltering.filterDepartureBeforeCurrentDate(flightList);
        System.out.println(flightList);

        System.out.println("\nИмеются сегменты с датой прилёта раньше даты вылета");
        flightList = FlightFiltering.filterArrivalDateBeforeDepartureDate(flightList);
        System.out.println(flightList);

        System.out.println("\nИмеются сегменты с датой прилёта раньше даты вылета");
        flightList = FlightFiltering.filterNumberOfHoursOnEarth(flightList);
        System.out.println(flightList);
    }
}
