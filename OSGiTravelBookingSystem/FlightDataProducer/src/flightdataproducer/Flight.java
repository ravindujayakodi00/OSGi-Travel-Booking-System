package flightdataproducer;

import java.util.Date;

public class Flight {
    private String flightId;
    private String flightOwner;
    private String flightModel;
    private String flightArrivalAirport;
    private Date flightArrivalTime;
    private String flightDepartureAirport;
    private Date flightDepartureTime;

    // Constructor
    public Flight(String flightId, String flightOwner, String flightModel, String flightArrivalAirport,
                  Date flightArrivalTime, String flightDepartureAirport, Date flightDepartureTime) {
        this.flightId = flightId;
        this.flightOwner = flightOwner;
        this.flightModel = flightModel;
        this.flightArrivalAirport = flightArrivalAirport;
        this.flightArrivalTime = flightArrivalTime;
        this.flightDepartureAirport = flightDepartureAirport;
        this.flightDepartureTime = flightDepartureTime;
    }

    // Getters and setters
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getFlightOwner() {
        return flightOwner;
    }

    public void setFlightOwner(String flightOwner) {
        this.flightOwner = flightOwner;
    }

    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getFlightArrivalAirport() {
        return flightArrivalAirport;
    }

    public void setFlightArrivalAirport(String flightArrivalAirport) {
        this.flightArrivalAirport = flightArrivalAirport;
    }

    public Date getFlightArrivalTime() {
        return flightArrivalTime;
    }

    public void setFlightArrivalTime(Date flightArrivalTime) {
        this.flightArrivalTime = flightArrivalTime;
    }

    public String getFlightDepartureAirport() {
        return flightDepartureAirport;
    }

    public void setFlightDepartureAirport(String flightDepartureAirport) {
        this.flightDepartureAirport = flightDepartureAirport;
    }

    public Date getFlightDepartureTime() {
        return flightDepartureTime;
    }

    public void setFlightDepartureTime(Date flightDepartureTime) {
        this.flightDepartureTime = flightDepartureTime;
    }
}
