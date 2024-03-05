package flightdataconsumer;

import java.util.Date;
import java.util.List;

import flightdataproducer.Flight;

public interface FlightDataConsumerService {

	void addFlight(String flightId, String flightOwner, String flightModel, String flightArrivalAirport,
	            Date flightArrivalTime, String flightDepartureAirport, Date flightDepartureTime);
	void updateFlight(String flightId, String flightOwner, String flightModel, String flightArrivalAirport,
	               Date flightArrivalTime, String flightDepartureAirport, Date flightDepartureTime);
	void deleteFlight(String flightId);
	List<Flight> getAllFlights();
	Flight getFlightById(String flightId);
	List<Flight> getFlightsByArrivalAirport(String arrivalAirport);
}
