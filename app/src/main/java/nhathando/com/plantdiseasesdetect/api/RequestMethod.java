package nhathando.com.plantdiseasesdetect.api;

import java.io.File;
import java.util.List;

import nhathando.com.plantdiseasesdetect.models.Face;
import nhathando.com.plantdiseasesdetect.models.PlantDiseases;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface RequestMethod {

    @Multipart
    @POST("device/facerecognize")
    Call<List<Face>> postFace(@Part MultipartBody.Part selectedFile);

    @Multipart
    @PUT("disease/uploadimages")
    Call<List<PlantDiseases>> postPlantImage(@Part MultipartBody.Part imageFile);

}
