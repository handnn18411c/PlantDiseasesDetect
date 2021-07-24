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
import nhathando.com.plantdiseasesdetect.util.Constant;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagePreviewViewModel extends AndroidViewModel {



    public ImagePreviewViewModel(@NonNull @NotNull Application application) {
        super(application);

    }
    public void getFaceRecognize(File selectedImage) {


        // create multipart
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), selectedImage);
        Log.d("aaaaaa" , selectedImage.getName()) ;
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", selectedImage.getName(), requestFile);
        RequestMethod requestMethod = RetrofitObject.getClient(Constant.BASE_URL) .create(RequestMethod.class);
        Call<Face> callback = requestMethod.postFace(body);

        callback.enqueue(new Callback<Face>() {
            @Override
            public void onResponse(Call<Face> call, Response<Face> response) {
                Log.d("aaaaaa" , "Sucess") ;
                if(response.body()!= null)
                Log.d("aaaaaa" , response.body().getName()) ;
                else  Log.d("aaaaaa" , "Nothing") ;

            }
            @Override
            public void onFailure(Call<Face> call, Throwable t) {
                Log.d("aaaaaa" , "Failed") ;
            }
        });


    }



}
