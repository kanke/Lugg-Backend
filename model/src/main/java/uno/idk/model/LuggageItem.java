package uno.idk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by moham on 6/4/2016.
 */

public class LuggageItem implements Serializable{

    @SerializedName("barcode") @Expose
    private String barcode;

    @SerializedName("width") @Expose
    private Double width;

    @SerializedName("height") @Expose
    private Double height;

    @SerializedName("depth") @Expose
    private Double depth;

    @SerializedName("volume") @Expose
    private Double volume;

    @SerializedName("mass") @Expose
    private Double mass;

}
