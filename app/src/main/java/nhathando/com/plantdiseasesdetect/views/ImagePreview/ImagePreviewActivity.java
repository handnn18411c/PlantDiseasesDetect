package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;

public class ImagePreviewActivity extends BaseActivity {

    private ImagePreviewViewModel imagePreviewViewModel;

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
    }
}