package hotelManagementServicePublihser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HotelServiceImpl implements HotelService {

    private Map<String, Hotel> hotels = new HashMap<>();
    private Map<String, Map<String, Integer>> bookings = new HashMap<>();

    @Override
    public void addHotel(String hotelName, String location, int capacity) {
        Hotel hotel = new Hotel(hotelName, location, capacity);
        hotels.put(hotelName, hotel);
    }

    @Override
    public String getHotelInfo(String hotelName) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            return "Hotel Name: " + hotel.getHotelName() +
                    "\nLocation: " + hotel.getLocation() +
                    "\nCapacity: " + hotel.getCapacity();
        } else {
            return "Hotel not found.";
        }
    }

    @Override
    public void bookRoom(String hotelName, String travelerName, int numRooms) {
        Hotel hotel = hotels.get(hotelName);
        if (hotel != null) {
            Map<String, Integer> travelerBookings = bookings.computeIfAbsent(hotelName, k -> new HashMap<>());
            travelerBookings.put(travelerName, numRooms);
            System.out.println(travelerName + " booked " + numRooms + " room(s) in " + hotelName + ".");
        } else {
            System.out.println("Hotel not found.");
        }
    }

    @Override
    public void cancelBooking(String hotelName, String travelerName) {
        Map<String, Integer> travelerBookings = bookings.getOrDefault(hotelName, new HashMap<>());
        Integer numRoomsBooked = travelerBookings.remove(travelerName);
        if (numRoomsBooked != null) {
            System.out.println(travelerName + "'s booking in " + hotelName + " canceled.");
        } else {
            System.out.println(travelerName + " has no booking in " + hotelName + ".");
        }
    }

    @Override
    public String getBookingInfo(String hotelName, String travelerName) {
        Map<String, Integer> travelerBookings = bookings.getOrDefault(hotelName, new HashMap<>());
        Integer numRoomsBooked = travelerBookings.get(travelerName);
        if (numRoomsBooked != null) {
            return travelerName + " has booked " + numRoomsBooked + " room(s) in " + hotelName + ".";
        } else {
            return travelerName + " has no booking in " + hotelName + ".";
        }
    }

    @Override
    public List<String> getAllHotelDetails() {
        // Implementation for getting all hotel details
        List<String> allDetails = new ArrayList<>();

        for (Hotel hotel : hotels.values()) {
            allDetails.add(hotel.toString()); // Assuming Hotel class has a toString method
        }

        return allDetails;
    }
}
