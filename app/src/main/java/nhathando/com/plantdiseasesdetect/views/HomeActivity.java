package nhathando.com.plantdiseasesdetect.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
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
        addEvents();
    }

    private void addEvents() {

    }

    private void addView() {
        bundle = new Bundle();
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
        }
    }
}