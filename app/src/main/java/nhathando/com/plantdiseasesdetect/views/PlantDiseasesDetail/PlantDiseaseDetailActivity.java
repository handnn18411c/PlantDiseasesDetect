package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.HomeActivity;

public class PlantDiseaseDetailActivity extends BaseActivity {

    private PlantDiseasesDetailViewModel plantDiseasesDetailViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_disease_detail;
    }

    @Override
    protected void createView() {
        plantDiseasesDetailViewModel = new ViewModelProvider(this).get(PlantDiseasesDetailViewModel.class);
    }

    public void switchActivity(View view) {
        Intent intent= new Intent(PlantDiseaseDetailActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}