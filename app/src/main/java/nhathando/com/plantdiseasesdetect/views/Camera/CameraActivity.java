package nhathando.com.plantdiseasesdetect.views.Camera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Camera;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;

public class CameraActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_camera;
    }

    @Override
    protected void createView() {
        initView();
    }

    private void initView() {
        //TODO

    }

    @OnClick(R.id.btnCamera)
    public void openCamera() {
        showActivity(CameraActivity.class);
    }

    @OnClick(R.id.btnGallery)
    public void openGallery() {
        //TODO
    }
}