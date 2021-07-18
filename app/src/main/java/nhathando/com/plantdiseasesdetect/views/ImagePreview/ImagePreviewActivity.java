package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;

public class ImagePreviewActivity extends BaseActivity {

    private ImagePreviewViewModel imagePreviewViewModel;
    private String function;

    @BindView(R.id.imgPlant)
    ImageView imgPlan;
    @BindView(R.id.btnAgain)
    Button btnAgain;

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
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if (bundle != null) {
            Uri uri = bundle.getParcelable(Constant.IMAGE_CAPTURE_URI);
            imgPlan.setImageURI(uri);
            String function = bundle.getString(Constant.KEY_CAMERA_OR_GALLERY);
            String cameraActivity = getResources().getString(R.string.ActivityCamera);
            if(cameraActivity.equals(function)) {
                btnAgain.setText(getResources().getString(R.string.Camera));
            } else {
                btnAgain.setText(getResources().getString(R.string.Gallery));
            }
        }
    }

    @OnClick(R.id.btnAgain)
    public void again() {
        if(function.equals(getResources().getString(R.string.ActivityCamera))) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Constant.REQUEST_IMAGE_CAPTURE);
        } else {
            //TODO
        }

    }
}