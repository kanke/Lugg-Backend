package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by moham on 6/4/2016.
 */

public class Journey {

    @SerializedName("flight") @Expose
    private Flight flight;

    @SerializedName("seat") @Expose
    private String seat;

    @SerializedName("class_code") @Expose
    private String classCode;

    @SerializedName("class_name") @Expose
    private String className;

    //Served from google maps, the place id will refer to the hotel the customer is staying in or the house or whatever. This will be used for bag pick up.
    @SerializedName("start_placeId") @Expose
    private String startPlaceId;

    //This will be used for bag drop off upon arrival
    @SerializedName("end_placeId") @Expose
    private String endPlaceId;

    //This will be used to allow the user to make a note for the start place. I.E "my room is 213, please pick up bags from room
    @SerializedName("start_placeId_note") @Expose
    private String startPlaceIdNote;

    //This will be used to allow the user to make a note for the start place. I.E "my room is 213, please pick up bags from room
    @SerializedName("end_placeId_note") @Expose
    private String endPlaceIdNote;

    @SerializedName("pieces") @Expose
    private List<LuggageItem> pieces;

    //parent ticket
    @SerializedName("ticket") @Expose
    private Ticket ticket;

}
