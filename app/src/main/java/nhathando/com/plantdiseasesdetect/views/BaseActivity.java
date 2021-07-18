package nhathando.com.plantdiseasesdetect.views;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import nhathando.com.plantdiseasesdetect.event.PDDActivityResult;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.ImagePreview.ImagePreviewActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        createView();
    }

    /**
     * get layout id of activity
     *
     * @return layout id
     */
    protected abstract int getLayoutId();

    /**
     * create view
     */
    protected abstract void createView();

    /**
     * show toast message
     *
     * @param msg message to show
     */
    public void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (msg != null) {
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * go to other activity
     *
     * @param t Activity class
     */
    public void showActivity(Class t) {
        Intent intent = new Intent(this, t);
        startActivity(intent);
    }

    public void showActivity(Class t, Bundle bundle) {
        Intent intent = new Intent(this, t);
        intent.putExtra(Constant.KEY_EXTRA, bundle);
        startActivity(intent);
    }

}
