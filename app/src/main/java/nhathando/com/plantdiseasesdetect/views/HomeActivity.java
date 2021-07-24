package nhathando.com.plantdiseasesdetect.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.ImagePreview.ImagePreviewActivity;

import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.ImagePreview.ImagePreviewActivity;

public class HomeActivity extends BaseActivity {

    private ContentValues values;
    private Uri imageUri;
    private Bundle bundle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createView() {
        addView();
        checkPermission();
    }

    private void addView() {
        bundle = new Bundle();
        /*set Slider Show*/
        ImageSlider imageSlider = findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<SlideModel>();
        slideModels.add(new SlideModel(R.drawable.caphe1, null));
        slideModels.add(new SlideModel(R.drawable.caphe2, null));
        slideModels.add(new SlideModel(R.drawable.caphe3, null));
        slideModels.add(new SlideModel(R.drawable.caphe4, null));
        imageSlider.setImageList(slideModels, ScaleTypes.CENTER_CROP);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(
                HomeActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HomeActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, Constant.REQUEST_IMAGE_CAPTURE);
        }
    }

    @OnClick(R.id.btnCamera)
    public void openCamera() {
        bundle.putString(Constant.KEY_CAMERA_OR_GALLERY, getResources().getString(R.string.ActivityCamera));
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "MyPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Constant.REQUEST_IMAGE_CAPTURE);
    }

    @OnClick(R.id.btnGallery)
    public void openGallery() {
        bundle.putString(Constant.KEY_CAMERA_OR_GALLERY, getResources().getString(R.string.ActivityGallery));
        Intent i = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, Constant.PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constant.REQUEST_IMAGE_CAPTURE:
                if (requestCode == Constant.REQUEST_IMAGE_CAPTURE)
                    if (resultCode == RESULT_OK) {
                        try {
                            bundle.putParcelable(Constant.IMAGE_CAPTURE_URI, imageUri);
                            showActivity(ImagePreviewActivity.class, bundle);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                break;
            case Constant.PICK_IMAGE:
                Uri selectedImage = data.getData();
                Log.d("aaaaaa", selectedImage.toString());
                bundle.putParcelable(Constant.IMAGE_CAPTURE_URI, selectedImage);
                showActivity(ImagePreviewActivity.class, bundle);
                break;

        }
    }

}