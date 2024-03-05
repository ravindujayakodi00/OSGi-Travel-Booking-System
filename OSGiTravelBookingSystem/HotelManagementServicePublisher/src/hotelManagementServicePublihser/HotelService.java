package hotelManagementServicePublihser;

import java.util.List;

public interface HotelService {
    
    void addHotel(String hotelName, String location, int capacity);

    String getHotelInfo(String hotelName);

    void bookRoom(String hotelName, String travelerName, int numRooms);

    void cancelBooking(String hotelName, String travelerName);

    String getBookingInfo(String hotelName, String travelerName);
    

	List<String> getAllHotelDetails();
    
    
}
