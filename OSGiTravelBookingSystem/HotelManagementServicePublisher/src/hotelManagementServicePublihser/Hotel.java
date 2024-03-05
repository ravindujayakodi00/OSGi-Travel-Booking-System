package hotelManagementServicePublihser;

public class Hotel {

    private String hotelName;
    private String location;
    private int capacity;

    public Hotel(String hotelName, String location, int capacity) {
        this.hotelName = hotelName;
        this.location = location;
        this.capacity = capacity;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "Hotel Details:\n" +
                "Hotel Name: " + hotelName +
                "\nLocation: " + location +
                "\nCapacity: " + capacity +
                "\n------------------------";
    }
}
