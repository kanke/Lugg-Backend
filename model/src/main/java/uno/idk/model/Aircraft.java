package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by moham on 6/4/2016.
 */
public class Aircraft implements Serializable {


    @SerializedName("aircraft_reg") @Expose
    private String aircraftReg;

    @SerializedName("aircraft_type") @Expose
    private String aircraftType;

    //maybe connect some sort of aircraft api

}
