package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.time.LocalDate;

import butterknife.BindView;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;

import static nhathando.com.plantdiseasesdetect.util.Constant.KEY_IMAGE;

public class ImagePreviewActivity extends BaseActivity {

    @BindView(R.id.imgImage)  ImageView imgImage ;
    private ImagePreviewViewModel imagePreviewViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void createView() {
        initView();

        Bundle bundleFromGallery = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if(bundleFromGallery != null ) {
            Uri imagePath = bundleFromGallery.getParcelable(KEY_IMAGE) ;
            if(imagePath != null ) {
                Bitmap bitmap= null;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imagePath));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                imgImage.setImageBitmap(bitmap);
                Log.d("aaaaaa" , imagePath.toString());
            }
            else Log.d("aaaaaa" , "failedd" ) ;

        }




    }

    private void initView() {
        imagePreviewViewModel = new ViewModelProvider(this).get(ImagePreviewViewModel.class);
    }
}