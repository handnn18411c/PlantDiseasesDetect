package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.HomeActivity;
import nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail.PlantDiseaseDetailActivity;

public class PlantDiseasesDetectActivity extends BaseActivity {

    private PlantDiseasesDetectViewModel plantDiseasesDetectViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_diseases_detect;
    }

    @Override
    protected void createView() {
        initView();
    }

    private void initView() {
        plantDiseasesDetectViewModel = new ViewModelProvider(this).get(PlantDiseasesDetectViewModel.class);
    }

    public void switchActivity(View view) {
        Intent intent= new Intent(PlantDiseasesDetectActivity.this, PlantDiseaseDetailActivity.class);
        startActivity(intent);
    }
}