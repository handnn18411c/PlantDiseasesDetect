package nhathando.com.plantdiseasesdetect.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.ImagePreview.ImagePreviewActivity;

import static nhathando.com.plantdiseasesdetect.util.Constant.KEY_IMAGE;


public class HomeActivity extends BaseActivity {
    @BindView(R.id.btnGallery) Button btnGallery;
    int PICK_IMAGE = 1 ;
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
        // btnGallery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PICK_IMAGE);

            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {

            Uri selectedImage = data.getData();
            Log.d("aaaaaa" , selectedImage.toString());
            Bundle bundle = new Bundle() ;
            bundle.putParcelable(KEY_IMAGE , selectedImage);
            showActivity(ImagePreviewActivity.class ,bundle);
        }
    }
    private void addView() {

    }

}