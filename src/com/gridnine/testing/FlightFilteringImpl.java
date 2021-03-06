package com.gridnine.testing;

import java.time.*;
import java.util.*;


public class FlightFilteringImpl implements FlightFiltering{

    public List<Flight> filterDepartureBeforeCurrentDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (Flight flight : flightList) {
            List<Segment> segmentList = new ArrayList<>();
            for (int j = 0; j < flight.getSegments().size(); j++) {
                if (flight.getSegments().get(j).getDepartureDate().isBefore(LocalDateTime.now())) {
                    segmentList.addAll(flight.getSegments());
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
            List<Segment> segmentList = new ArrayList<>();
            for (int j = 0; j < flight.getSegments().size(); j++) {
                if (flight.getSegments().get(j).getDepartureDate().isAfter(flight.getSegments().get(j).getArrivalDate())) {
                    segmentList.addAll(flight.getSegments());
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
            List<Segment> segmentList = new ArrayList<>();
            for (int j = 0; j < flight.getSegments().size() - 1; j++) {
                LocalDateTime from = flight.getSegments().get(j).getArrivalDate();
                LocalDateTime to = flight.getSegments().get(j + 1).getDepartureDate();
                if (Duration.between(from, to).getSeconds() >= 7200) {
                    segmentList.addAll(flight.getSegments());
                    break;
                }
            }
            if (!segmentList.isEmpty()) newFlightList.add(new Flight(segmentList));
        }

        return newFlightList;
    }
}
