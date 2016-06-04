package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by moham on 6/4/2016.
 */

public class Ticket {

    @SerializedName("ticket_number") @Expose
    private String ticketnumber;

    @SerializedName("passenger") @Expose
    private Passenger passenger;

    @SerializedName("date_of_purchase") @Expose
    private Date dateOfPurchase;


    @SerializedName("booking_ref") @Expose
    private String booking_reg;

    @SerializedName("itinerary") @Expose
    private List<Journey> itinerary;

}
