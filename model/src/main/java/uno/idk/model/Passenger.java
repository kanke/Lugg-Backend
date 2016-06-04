package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by moham on 6/4/2016.
 */
public class Passenger implements Serializable{

    @SerializedName("firstName") @Expose
    private String firstName;

    @SerializedName("surname") @Expose
    private String surname;

    @SerializedName("passport") @Expose
    private Passport passport;

}
