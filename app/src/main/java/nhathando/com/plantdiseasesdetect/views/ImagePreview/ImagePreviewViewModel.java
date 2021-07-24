package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import android.app.Application;
import android.database.Observable;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

import nhathando.com.plantdiseasesdetect.api.RequestMethod;
import nhathando.com.plantdiseasesdetect.api.RetrofitObject;
import nhathando.com.plantdiseasesdetect.models.Face;
import nhathando.com.plantdiseasesdetect.models.PlantDiseases;
import nhathando.com.plantdiseasesdetect.util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagePreviewViewModel extends AndroidViewModel {

    private static final String TAG = ImagePreviewViewModel.class.getSimpleName();
    private PlantDiseases plantDiseasesData;

    public ImagePreviewViewModel(@NonNull @NotNull Application application) {
        super(application);
        plantDiseasesData = new PlantDiseases();
    }

    public void getFaceRecognize(File selectedImage) {
        // create multipart
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), selectedImage);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", selectedImage.getName(), requestFile);
        RequestMethod requestMethod = RetrofitObject.getClient(Constant.TEST_URL).create(RequestMethod.class);
        Call<List<Face>> callback = requestMethod.postFace(body);

        callback.enqueue(new Callback<List<Face>>() {
            @Override
            public void onResponse(Call<List<Face>> call, Response<List<Face>> response) {
                List<Face> faceRespone = response.body();
                if(faceRespone != null) {
                    Log.d("FACEX", faceRespone + "");
                }
            }

            @Override
            public void onFailure(Call<List<Face>> call, Throwable t) {
                Log.d("FACEXX", t + "");
            }
        });

    }

    public void getPlantDisease(File imageFile) {
        // create multipart
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);
        RequestMethod requestMethod = RetrofitObject.getClient(Constant.BASE_URL).create(RequestMethod.class);
        Call<List<PlantDiseases>> callback = requestMethod.postPlantImage(body);

        callback.enqueue(new Callback<List<PlantDiseases>>() {
            @Override
            public void onResponse(Call<List<PlantDiseases>> call, Response<List<PlantDiseases>> response) {
                List<PlantDiseases> plantDiseasesRespone = response.body();
                if(plantDiseasesRespone != null) {
                    plantDiseasesData = plantDiseasesRespone.get(0);
                    Log.d("PLANTX", plantDiseasesData + "");
                }
            }

            @Override
            public void onFailure(Call<List<PlantDiseases>> call, Throwable t) {
                t.printStackTrace();
                Log.d("PLANTXF", t + "");
            }
        });
    }
}
