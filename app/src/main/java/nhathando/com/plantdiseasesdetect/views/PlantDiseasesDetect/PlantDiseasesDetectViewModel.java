package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetect;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

public class PlantDiseasesDetectViewModel extends AndroidViewModel {

    private MutableLiveData<PlantDiseases> plantDiseasesData;
    private String[] result;

    public PlantDiseasesDetectViewModel(@NonNull @NotNull Application application) {
        super(application);
        plantDiseasesData = new MutableLiveData<>();
    }

    public MutableLiveData<PlantDiseases> getPlantDisease(File imageFile) {
        // create multipart
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", imageFile.getName(), requestFile);
        RequestMethod requestMethod = RetrofitObject.getClient(Constant.BASE_URL).create(RequestMethod.class);
        Call<List<PlantDiseases>> callback = requestMethod.postPlantImage(body);

        callback.enqueue(new Callback<List<PlantDiseases>>() {
            @Override
            public void onResponse(Call<List<PlantDiseases>> call, Response<List<PlantDiseases>> response) {
                List<PlantDiseases> plantDiseasesRespone = response.body();
                if (plantDiseasesRespone != null) {
                    plantDiseasesData.postValue(plantDiseasesRespone.get(0));
//                    result = plantDiseasesData.getResult().split(" ");
                    Log.d("PLANTX", plantDiseasesData + "");
                }
            }

            @Override
            public void onFailure(Call<List<PlantDiseases>> call, Throwable t) {
                t.printStackTrace();
                Log.d("PLANTXF", t + "");
            }
        });
        Log.d("PLANTXF", plantDiseasesData + "");
        return plantDiseasesData;
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("plant_diseases_db.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
