package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import nhathando.com.plantdiseasesdetect.views.BaseActivity;

public class PlantDiseaseDetailActivity extends BaseActivity {

    private PlantDiseasesDetailViewModel plantDiseasesDetailViewModel;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void createView() {
        plantDiseasesDetailViewModel = new ViewModelProvider(this).get(PlantDiseasesDetailViewModel.class);
    }
}