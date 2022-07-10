package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FlightFilteringImpl implements FlightFiltering{

    public List<Flight> filterDepartureBeforeCurrentDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (Flight flight : flightList) {
            List<Segment> segmentList = flight.getSegments();
            for (int j = 0; j < segmentList.size(); j++) {
                if (segmentList.get(j).getDepartureDate().isBefore(LocalDateTime.now())) {
                    segmentList.remove(j);
                    break;
                }
            }
            if (!segmentList.isEmpty()) newFlightList.add(new Flight(segmentList));
        }

        return newFlightList;
    }

    public List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (Flight flight : flightList) {
            List<Segment> segmentList = flight.getSegments();
            for (int j = 0; j < segmentList.size(); j++) {
                if (segmentList.get(j).getDepartureDate().isAfter(segmentList.get(j).getArrivalDate())) {
                    segmentList.remove(j);
                    break;
                }
            }
            if (!segmentList.isEmpty()) newFlightList.add(new Flight(segmentList));
        }

        return newFlightList;
    }

    public List<Flight> filterNumberOfHoursOnEarth(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (Flight flight : flightList) {
            List<Segment> segmentList = flight.getSegments();
            for (int j = 0; j < segmentList.size() - 1; j++) {
                double beforeHours = segmentList.get(j).getArrivalDate().getHour()
                        + (segmentList.get(j).getArrivalDate().getMinute() / 60.0)
                        + (segmentList.get(j).getArrivalDate().getSecond() / 3600.0);
                double afterHours = segmentList.get(j + 1).getDepartureDate().getHour()
                        + segmentList.get(j + 1).getDepartureDate().getMinute() / 60.0
                        + (segmentList.get(j + 1).getDepartureDate().getSecond() / 3600.0);
                if (afterHours - beforeHours >= 2 && afterHours > 0) {
                    segmentList.remove(j);
                    break;
                }
            }
            if (!segmentList.isEmpty()) newFlightList.add(new Flight(segmentList));
        }

        return newFlightList;
    }

    public void printFlight(List<Flight> flightList) {
        flightList.forEach(System.out::println);
    }
}
