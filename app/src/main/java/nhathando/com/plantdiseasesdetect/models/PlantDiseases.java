package nhathando.com.plantdiseasesdetect.models;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlantDiseases {

    @SerializedName("url")
    private String imageUrl;

    @SerializedName("result")
    private String result;

    @SerializedName("file_path")
    private String filePath;
    
}
