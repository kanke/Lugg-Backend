package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by moham on 6/4/2016.
 */

public class Flight {

    //2-Letter IATA Airline Code
    @SerializedName("airline") @Expose
    private String airline;

    //3 LETTER IATA AIRPORT CODE
    @SerializedName("departure_airport") @Expose
    private String departureAirport;

    @SerializedName("departure_terminal") @Expose
    private String departureTerminal;

    @SerializedName("arrival_airport") @Expose
    private String arrivalAirport;

    @SerializedName("arrival_terminal") @Expose
    private String arrivalTerminal;

    @SerializedName("ETA") @Expose
    private Date estimatedTimeOfArrival;

    @SerializedName("ATA") @Expose
    private Date actualTimeOfArrival;

    @SerializedName("RTA") @Expose
    private Date revisedTimeOfArrival;

    @SerializedName("ETD") @Expose
    private Date estimatedTimeOfDeparture;

    @SerializedName("ATD") @Expose
    private Date actualDepartureTime;

    @SerializedName("RTD") @Expose
    private Date revisedTimeOfDeparture;

    @SerializedName("aircraft") @Expose
    private Aircraft aircraft;

}
