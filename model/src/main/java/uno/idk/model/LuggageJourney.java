package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by moham on 6/4/2016.
 */

public class LuggageJourney {
    //the luggage item will have a list of luggage journeys. This class simplifies those journeys
    @SerializedName("journey_type") @Expose
    private String journeyType;
}
