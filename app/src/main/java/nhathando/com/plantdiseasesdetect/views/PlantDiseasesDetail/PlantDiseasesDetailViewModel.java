package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail;

import android.app.Application;
import android.content.Context;
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
import nhathando.com.plantdiseasesdetect.models.PlantDiseases;
import nhathando.com.plantdiseasesdetect.util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantDiseasesDetailViewModel extends AndroidViewModel {

    public PlantDiseasesDetailViewModel(@NonNull @NotNull Application application) {
        super(application);
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
