package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.HomeActivity;

public class PlantDiseaseDetailActivity extends BaseActivity {

    private PlantDiseasesDetailViewModel plantDiseasesDetailViewModel;
    private String disease_info;
    private String disease_name;
    private String disease_symptom;
    private String disease_solution;
    private String disease_medicines;

    @BindView(R.id.tvTrieuChung)
    TextView tvTrieuChung;
    @BindView(R.id.tvPhongBenh)
    TextView tvPhongBenh;
    @BindView(R.id.tvPhanthuoc)
    TextView tvPhanthuoc;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plant_disease_detail;
    }

    @Override
    protected void createView() {
        plantDiseasesDetailViewModel = new ViewModelProvider(this).get(PlantDiseasesDetailViewModel.class);
        disease_info = plantDiseasesDetailViewModel.loadJSONFromAsset(this);
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if (bundle != null) {
            disease_name = bundle.getString(Constant.PLANT_DISEASE_SCIENCE_NAME);
            disease_symptom = bundle.getString(Constant.PLANT_DISEASE_SYMPTOM);
            disease_solution = bundle.getString(Constant.PLANT_DISEASE_SOLUTION);
            disease_medicines = bundle.getString(Constant.PLANT_DISEASE_MEDICINES);
            mapDataToView(disease_name, disease_symptom, disease_solution, disease_medicines);
        }
        Log.d("DISEASESINFO", disease_info);
    }

    private void mapDataToView(String disease_name, String sympton, String solution, String medicine) {
        tvTrieuChung.setText("Tên khoa học là: " + disease_name + " - " + sympton);
        tvPhongBenh.setText(solution);
        tvPhanthuoc.setText(medicine);
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(PlantDiseaseDetailActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnReturn)
    public void returnBack() {
        finish();
    }

    @OnClick(R.id.btnQuit)
    public void quit() {
        showActivity(HomeActivity.class);
    }
}