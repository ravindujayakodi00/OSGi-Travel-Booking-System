package flightdataproducer;

import java.util.Date;
import java.util.List;

public interface FlightDataProducerService {
	
	void addFlight(Flight flight);
    boolean updateFlight(String flightId, String flightOwner, String flightModel, String flightArrivalAirport, Date flightArrivalTime, String flightDepartureAirport, Date flightDepartureTime);
    boolean deleteFlight(String flightId);
    List<Flight> getAllFlights();
    Flight getFlightById(String flightId);
    List<Flight> searchFlights(String arrivalAirport);

}
