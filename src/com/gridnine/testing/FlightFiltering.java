package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightFiltering {

    public List<Flight> filterDepartureBeforeCurrentDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size(); j++) {
                if (s.get(j).getDepartureDate().isBefore(LocalDateTime.now())) {
                    s.remove(j);
                    break;
                }
            }
            newFlightList.add(new Flight(s));
        }

        return newFlightList;
    }

    public List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size(); j++) {
                if (s.get(j).getDepartureDate().isAfter(s.get(j).getArrivalDate())) {
                    s.remove(j);
                    break;
                }
            }
            newFlightList.add(new Flight(s));
        }

        return newFlightList;
    }

    public List<Flight> filterNumberOfHoursOnEarth(List<Flight> flightList) {
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size() - 1; j++) {
                double beforeHours = s.get(j).getArrivalDate().getHour()
                        + (s.get(j).getArrivalDate().getMinute() / 60.0)
                        + (s.get(j).getArrivalDate().getSecond() / 3600.0);
                double afterHours = s.get(j + 1).getDepartureDate().getHour()
                        + s.get(j + 1).getDepartureDate().getMinute() / 60.0
                        + (s.get(j + 1).getDepartureDate().getSecond() / 3600.0);
                if (afterHours - beforeHours >= 2 && afterHours > 0) {
                    flightList.remove(i);
                    break;
                }
            }
        }

        return flightList;
    }

    public void printFlight(List<Flight> flightList) {
        flightList.stream().forEach(System.out::println);
    }
}
