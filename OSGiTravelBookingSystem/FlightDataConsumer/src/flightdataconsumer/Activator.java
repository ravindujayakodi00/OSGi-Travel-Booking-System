package flightdataconsumer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import flightdataproducer.Flight;
import flightdataproducer.FlightDataProducerService;

public class Activator implements BundleActivator {
	
	private BundleContext context;
    private ServiceReference<FlightDataProducerService> flightServiceRef;
    private FlightDataProducerService flightService;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Starting Flight Service Consumer...");

        this.context = context;
        flightServiceRef = context.getServiceReference(FlightDataProducerService.class);
        flightService = context.getService(flightServiceRef);

        displayMenu();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Thank you for using the Flight Service Consumer.");
        context.ungetService(flightServiceRef);
    }

    public void displayMenu() {
        int choice = 0;

        do {
            System.out.println("\n##############################");
            System.out.println("Flight Management Menu");
            System.out.println("##############################\n");
            System.out.println("1. Add Flight");
            System.out.println("2. Update Flight");
            System.out.println("3. Delete Flight");
            System.out.println("4. Get All Flights");
            System.out.println("5. Get Flight By ID");
            System.out.println("6. Search Flights By Arrival Airport");
            System.out.println("0. Exit\n");
            System.out.println("##############################\n");

            choice = getChoiceFromUser();

            switch (choice) {
                case 1:
                    addFlight();
                    break;

                case 2:
                    updateFlight();
                    break;

                case 3:
                    deleteFlight();
                    break;

                case 4:
                    getAllFlights();
                    break;

                case 5:
                    getFlightById();
                    break;

                case 6:
                    searchFlightsByArrivalAirport();
                    break;
            }

            System.out.print("\nDo you want to continue (Y/N) : ");
            char ans = scanner.next().charAt(0);

            if (ans == 'N' || ans == 'n') {
                choice = 0;
            }
        } while (choice != 0);

        System.out.println("####################  GOOD BYE!! ####################");
    }

    private int getChoiceFromUser() {
        while (true) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                return choice;
            } else {
                System.out.println("Invalid input! Please enter an integer.");
                scanner.nextLine(); // Consume invalid input
            }
        }
    }

    public void addFlight() {
        System.out.println("\n############## ADD FLIGHT ##############\n");

        // Input flight details
        System.out.print("Enter Flight ID: ");
        String flightId = scanner.nextLine();

        System.out.print("Enter Flight Owner: ");
        String flightOwner = scanner.nextLine();

        System.out.print("Enter Flight Model: ");
        String flightModel = scanner.nextLine();

        System.out.print("Enter Flight Arrival Airport: ");
        String flightArrivalAirport = scanner.nextLine();

        System.out.print("Enter Flight Arrival Time (yyyy-MM-dd HH:mm:ss): ");
        Date flightArrivalTime = getDateInput();

        System.out.print("Enter Flight Departure Airport: ");
        String flightDepartureAirport = scanner.nextLine();

        System.out.print("Enter Flight Departure Time (yyyy-MM-dd HH:mm:ss): ");
        Date flightDepartureTime = getDateInput();

        // Create Flight object
        Flight flight = new Flight(flightId, flightOwner, flightModel, flightArrivalAirport, flightArrivalTime, flightDepartureAirport, flightDepartureTime);

        // Call service method to add flight
        flightService.addFlight(flight);
        System.out.println("Flight added successfully!");
    }

    public void updateFlight() {
        System.out.println("\n############## UPDATE FLIGHT ##############\n");

        // Input flight ID
        System.out.print("Enter Flight ID to update: ");
        String flightId = scanner.nextLine();

        // Call the service method to check if flight exists
        Flight existingFlight = flightService.getFlightById(flightId);
        if (existingFlight == null) {
            System.out.println("Flight with ID " + flightId + " does not exist.");
            return;
        }

        // Input updated details
        System.out.print("Enter new Flight Owner (leave blank to skip): ");
        String newFlightOwner = scanner.nextLine();

        System.out.print("Enter new Flight Model (leave blank to skip): ");
        String newFlightModel = scanner.nextLine();

        System.out.print("Enter new Flight Arrival Airport (leave blank to skip): ");
        String newFlightArrivalAirport = scanner.nextLine();

        System.out.print("Enter new Flight Arrival Time (yyyy-MM-dd HH:mm:ss, leave blank to skip): ");
        Date newFlightArrivalTime = null;
        String newFlightArrivalTimeString = scanner.nextLine();
        newFlightArrivalTime = getDateFromString(newFlightArrivalTimeString);

        System.out.print("Enter new Flight Departure Airport (leave blank to skip): ");
        String newFlightDepartureAirport = scanner.nextLine();

        System.out.print("Enter new Flight Departure Time (yyyy-MM-dd HH:mm:ss, leave blank to skip): ");
        Date newFlightDepartureTime = null;
        String newFlightDepartureTimeString = scanner.nextLine();
        newFlightDepartureTime = getDateFromString(newFlightDepartureTimeString);
        

        // Call the service method to update the flight
        boolean isUpdated = flightService.updateFlight(flightId, newFlightOwner, newFlightModel,
                newFlightArrivalAirport, newFlightArrivalTime, newFlightDepartureAirport, newFlightDepartureTime);
        if (isUpdated) {
            System.out.println("Flight updated successfully!");
        } else {
            System.out.println("Failed to update flight.");
        }
    }

    public void deleteFlight() {
        System.out.println("\n############## DELETE FLIGHT ##############\n");

        // Input flight ID to delete
        System.out.print("Enter Flight ID to delete: ");
        String flightId = scanner.nextLine();

        // Call the service method to delete the flight
        boolean isDeleted = flightService.deleteFlight(flightId);
        if (isDeleted) {
            System.out.println("Flight deleted successfully!");
        } else {
            System.out.println("Failed to delete flight.");
        }
    }

    public void getAllFlights() {
        System.out.println("\n############## ALL FLIGHTS ##############\n");

        List<Flight> flights = flightService.getAllFlights();
        if (flights != null && !flights.isEmpty()) {
            for (Flight flight : flights) {
                // Print flight details
                System.out.println(flight);
            }
        } else {
            System.out.println("No flights found.");
        }
    }

    public void getFlightById() {
        System.out.println("\n############## GET FLIGHT BY ID ##############\n");

        // Input flight ID to retrieve
        System.out.print("Enter Flight ID: ");
        String flightId = scanner.nextLine();

        // Call the service method to get flight by ID
        Flight flight = flightService.getFlightById(flightId);
        if (flight != null) {
            System.out.println("Flight found:");
            System.out.println(flight);
        } else {
            System.out.println("Flight with ID " + flightId + " not found.");
        }
    }

    public void searchFlightsByArrivalAirport() {
        System.out.println("\n############## SEARCH FLIGHTS BY ARRIVAL AIRPORT ##############\n");

        // Input arrival airport to search flights
        System.out.print("Enter Arrival Airport: ");
        String arrivalAirport = scanner.nextLine();

        // Call the service method to search flights by arrival airport
        List<Flight> flights = flightService.searchFlights(arrivalAirport);
        if (flights != null && !flights.isEmpty()) {
            System.out.println("Flights arriving at " + arrivalAirport + ":");
            for (Flight flight : flights) {
                System.out.println(flight);
            }
        } else {
            System.out.println("No flights arriving at " + arrivalAirport + ".");
        }
    }

    private Date getDateFromString(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in yyyy-MM-dd HH:mm:ss format.");
            return null;
        }
    }

    private Date getDateInput() {
        while (true) {
            System.out.print("Enter date (yyyy-MM-dd HH:mm:ss): ");
            String dateString = scanner.nextLine();
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return dateFormat.parse(dateString);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter date in yyyy-MM-dd HH:mm:ss format.");
            }
        }
    }

   
}

