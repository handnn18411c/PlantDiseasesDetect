package nhathando.com.plantdiseasesdetect.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Face {

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("persontype")
    private String persontype;

    @SerializedName("name")
    private String name;

    @SerializedName("idcard")
    private String idcard;

    @SerializedName("birthday")
    private String birthday;


}