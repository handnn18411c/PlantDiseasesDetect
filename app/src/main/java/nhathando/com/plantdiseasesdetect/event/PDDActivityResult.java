package nhathando.com.plantdiseasesdetect.event;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PDDActivityResult {
    void onResult();
    @Multipart
    @POST("upload")
    Call<ResponseBody> uploadPhoto(
            @Part MultipartBody.Part photo
            );
}
