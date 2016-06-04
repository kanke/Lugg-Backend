package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by moham on 6/4/2016.
 */
public class Passport implements Serializable{

    @SerializedName("passport_number") @Expose
    private String passportNumber;

    @SerializedName("firstName") @Expose
    private String firstName;

    @SerializedName("surname") @Expose
    private String surname;

    @SerializedName("image_url") @Expose
    private String image_url;

    @SerializedName("expiry_month") @Expose
    private int expiryMonth;

    @SerializedName("expiry_year") @Expose
    private int expiryYear;

    //ISO 3 LETTER COUNTRY CODE
    @SerializedName("country") @Expose
    private String country;

}
