package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightFiltering {

    public static List<Flight> filterDepartureBeforeCurrentDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size(); j++) {
                if (s.get(j).getDepartureDate().isBefore(LocalDateTime.now())) {
                    flightList.remove(i);
                    break;
                }
            }
            newFlightList.add(new Flight(s));
        }
        return newFlightList;
        //flightList.stream().sorted(s -> s.getSegments().);
    }

    public static List<Flight> filterArrivalDateBeforeDepartureDate(List<Flight> flightList) {
        List<Flight> newFlightList = new ArrayList<>();
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size(); j++) {
                if (s.get(j).getDepartureDate().isAfter(s.get(j).getArrivalDate())) {
                    flightList.remove(i);
                    break;
                }
            }
            newFlightList.add(new Flight(s));
        }

        return newFlightList;
    }

    public static List<Flight> filterNumberOfHoursOnEarth(List<Flight> flightList) {
        for (int i = 0; i < flightList.size(); i++) {
            List<Segment> s = flightList.get(i).getSegments();
            for (int j = 0; j < s.size() - 1; j++) {
                double beforeHours = s.get(j).getArrivalDate().getHour()
                        + s.get(j).getArrivalDate().getMinute() * 60 + s.get(j).getArrivalDate().getSecond() * 360;
                double afterHours = s.get(j + 1).getDepartureDate().getHour()
                        + s.get(j + 1).getArrivalDate().getMinute() * 60 + s.get(j + 1).getArrivalDate().getSecond() * 360;
                if (afterHours - beforeHours < 2) {
                    flightList.remove(i);
                    break;
                }
            }
        }

        return flightList;
    }
}
