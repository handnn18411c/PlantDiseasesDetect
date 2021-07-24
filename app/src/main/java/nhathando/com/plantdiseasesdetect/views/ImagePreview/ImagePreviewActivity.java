package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Random;

import butterknife.BindView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.OnClick;
import lombok.SneakyThrows;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.api.RetrofitObject;
import nhathando.com.plantdiseasesdetect.models.Face;
import nhathando.com.plantdiseasesdetect.util.AppUtil;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetect.PlantDiseasesDetectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImagePreviewActivity extends BaseActivity {
    private static final String TAG = ImagePreviewActivity.class.getSimpleName();

    private ImagePreviewViewModel imagePreviewViewModel;
    private String function;
    private Bundle bundle;

    @BindView(R.id.imgPlant)
    CropImageView imgPlant;
    @BindView(R.id.btnAgain)
    Button btnAgain;
    @BindView(R.id.btnUseImage)
    Button btnUserImage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void createView() {
        initView();
    }

    private void initView() {
        imagePreviewViewModel = new ViewModelProvider(this).get(ImagePreviewViewModel.class);
        bundle = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if (bundle != null) {
            Uri uri = bundle.getParcelable(Constant.IMAGE_CAPTURE_URI);
            imgPlant.setImageUriAsync(uri);
        }
    }

    @OnClick(R.id.btnAgain)
    public void again() {
        finishAffinity();
    }

    @OnClick(R.id.btnUseImage)
    public void sendToServer() {
        Bitmap croppedImage = imgPlant.getCroppedImage();
        File selectedImage = AppUtil.bitmapToFile(this, croppedImage, "plant.png");
        // Two line below using for test if image crop convert to file success
        // We will modify to connect API later
        Log.d("aaaaaaaa" , selectedImage.getAbsolutePath());
        Bitmap bitmap = BitmapFactory.decodeFile(selectedImage.getAbsolutePath());
        imgPlant.setImageBitmap(bitmap);
        imagePreviewViewModel = new ViewModelProvider(this).get(ImagePreviewViewModel.class);
        imagePreviewViewModel.getFaceRecognize(selectedImage);
        showActivity(PlantDiseasesDetectActivity.class);
    }



}