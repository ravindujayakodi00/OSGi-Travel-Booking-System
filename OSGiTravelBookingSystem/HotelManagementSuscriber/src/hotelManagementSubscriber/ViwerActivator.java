package hotelManagementSubscriber;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import hotelManagementServicePublihser.HotelService;

import java.util.List;
import java.util.Scanner;

public class ViwerActivator implements BundleActivator,HotelViewerService {

    private HotelService hotelService;
    private Scanner scanner;

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Hotel Viewer Bundle Activated!");

        // Retrieve the HotelService reference
        ServiceReference<HotelService> hotelServiceReference = context.getServiceReference(HotelService.class);

        if (hotelServiceReference != null) {
            // Get the HotelService from the reference
            hotelService = context.getService(hotelServiceReference);
         // Initialize the scanner
            scanner = new Scanner(System.in);

            // Start the interactive console
            startConsole();
        } else {
            System.out.println("HotelService not found!");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Hotel Viewer Bundle Deactivated!");
    }
    
    




    private void startConsole() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
        	System.out.println("\n============================================================================================ Services ============================================================================================");
        	System.out.println("\n");
        	System.out.println("\n                                                                +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        	System.out.println("\n");                                              
        	System.out.println("                                                                                           1. Add Hotel");
        	System.out.println("                                                                                           2. Get Hotel Info");
        	System.out.println("                                                                                           3. Book Room");
        	System.out.println("                                                                                           4. Cancel Booking");
        	System.out.println("                                                                                           5. Get Booking Info");
        	System.out.println("                                                                                           6. Show All Hotels details");
        	System.out.println("                                                                                           0. Exit");
        	System.out.println("\n");                                              
        	System.out.println("                                                                                           Enter your choice: ");
        	System.out.println("\n");
        	System.out.println("\n                                                                +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    getHotelInfo();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    getBookingInfo();
                    break;
                case 6:
                	viewHotelsDetails();
                	break;
                	
                case 0:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return; // Exit the loop and stop the console
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
    
   

    public void addHotel() {
        System.out.println("\nEnter Hotel details:");
        System.out.print("Hotel Name: ");
        String hotelName = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        hotelService.addHotel(hotelName, location, capacity);
        System.out.println("Hotel added successfully!");
    }

    public void getHotelInfo() {
        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.nextLine();

        String hotelInfo = hotelService.getHotelInfo(hotelName);
        System.out.println("\n" + hotelInfo);
    }

    public void bookRoom() {
        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.nextLine();

        System.out.print("Enter Traveler Name: ");
        String travelerName = scanner.nextLine();

        System.out.print("Enter Number of Rooms to Book: ");
        int numRooms = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        hotelService.bookRoom(hotelName, travelerName, numRooms);
        System.out.println("Room(s) booked successfully!");
    }

    public void cancelBooking() {
        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.nextLine();

        System.out.print("Enter Traveler Name: ");
        String travelerName = scanner.nextLine();

        hotelService.cancelBooking(hotelName, travelerName);
        System.out.println("Booking canceled successfully!");
    }

    public void getBookingInfo() {
        System.out.print("Enter Hotel Name: ");
        String hotelName = scanner.nextLine();

        System.out.print("Enter Traveler Name: ");
        String travelerName = scanner.nextLine();

        String bookingInfo = hotelService.getBookingInfo(hotelName, travelerName);
        System.out.println("\n" + bookingInfo);
    }

    @Override
    public void viewHotelsDetails() {
        // Use the HotelService to get all hotel details
        List<String> allHotelDetails = hotelService.getAllHotelDetails();

        // Display the hotel details
        System.out.println("\nAll Hotel Details:\n" + allHotelDetails);
    }
}
