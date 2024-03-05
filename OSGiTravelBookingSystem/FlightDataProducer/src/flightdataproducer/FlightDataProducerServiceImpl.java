package flightdataproducer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightDataProducerServiceImpl implements FlightDataProducerService {
	
	private List<Flight> flights;

    public FlightDataProducerServiceImpl() {
        this.flights = new ArrayList<>();
    }

    @Override
    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    @Override
    public boolean updateFlight(String flightId, String flightOwner, String flightModel, String flightArrivalAirport, Date flightArrivalTime, String flightDepartureAirport, Date flightDepartureTime) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                flight.setFlightOwner(flightOwner);
                flight.setFlightModel(flightModel);
                flight.setFlightArrivalAirport(flightArrivalAirport);
                flight.setFlightArrivalTime(flightArrivalTime);
                flight.setFlightDepartureAirport(flightDepartureAirport);
                flight.setFlightDepartureTime(flightDepartureTime);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteFlight(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                flights.remove(flight);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public Flight getFlightById(String flightId) {
        for (Flight flight : flights) {
            if (flight.getFlightId().equals(flightId)) {
                return flight;
            }
        }
        return null;
    }

    @Override
    public List<Flight> searchFlights(String arrivalAirport) {
        List<Flight> flightsByAirport = new ArrayList<>();
        for (Flight flight : flights) {
            if (flight.getFlightArrivalAirport().equalsIgnoreCase(arrivalAirport)) {
                flightsByAirport.add(flight);
            }
        }
        return flightsByAirport;
    }

}

