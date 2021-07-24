package nhathando.com.plantdiseasesdetect.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Face {

    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("persontype")
    @Expose
    private String persontype;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("idcard")
    @Expose
    private String idcard;
    @SerializedName("birthday")
    @Expose
    private String birthday;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPersontype() {
        return persontype;
    }

    public void setPersontype(String persontype) {
        this.persontype = persontype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

}