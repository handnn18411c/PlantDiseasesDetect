package nhathando.com.plantdiseasesdetect.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import nhathando.com.plantdiseasesdetect.R;

public class HomeActivity extends BaseActivity {

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

    }
}