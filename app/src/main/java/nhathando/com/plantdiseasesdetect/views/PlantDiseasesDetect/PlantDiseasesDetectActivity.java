package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.models.PlantDiseases;
import nhathando.com.plantdiseasesdetect.util.AppUtil;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.HomeActivity;
import nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail.PlantDiseaseDetailActivity;

public class PlantDiseasesDetectActivity extends BaseActivity {

    private PlantDiseasesDetectViewModel plantDiseasesDetectViewModel;
    private Bundle bundle;
    private PlantDiseases plantDiseases;
    private SVProgressHUD svProgressHUD;
    private String[] result;

    @BindView(R.id.imgBounding)
    ImageView imgBounding;
    @BindView(R.id.tvTenBenh)
    TextView tvTenBenh;
    @BindView(R.id.tvDoChinhXac)
    TextView tvDoChinhXac;

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
        svProgressHUD = new SVProgressHUD(this);
        bundle = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if(bundle != null) {
            Bitmap croppedImage = bundle.getParcelable(Constant.IMAGE_BITMAP);
            imgBounding.setImageBitmap(croppedImage);
            File selectedImage = AppUtil.bitmapToFile(this, croppedImage, "plant.png");
            showProgress("Please wait");
            plantDiseasesDetectViewModel.getPlantDisease(selectedImage).observe(this, model -> {
                if(model != null) {
                    String data = model.getResult();
                    JSONArray jsonArr = null;
                    try {
                        jsonArr = new JSONArray(data);

                        tvTenBenh.setText(jsonArr.getJSONObject(0).get("name") + "");
                        String rate = jsonArr.getJSONObject(0).get("conf") + "";
                        rate = rate.substring(7, rate.length() - 1);
                        float rateDetect = Float.parseFloat(rate)*100;
                        tvDoChinhXac.setText(Math.round(rateDetect) + "%");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            Log.d("PLANTX", plantDiseases + "");
            hideProgress();
        }
    }

    private void mapDataToView(PlantDiseases diseases){
        tvTenBenh.setText(diseases.getResult());
//        String data = plantDiseases.getResult();
//        JSONArray jsonArr = null;
//        try {
//            jsonArr = new JSONArray(data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.d("PLANTXXX", jsonArr + "");
    }

    public void switchActivity(View view) {
        Intent intent= new Intent(PlantDiseasesDetectActivity.this, PlantDiseaseDetailActivity.class);
        startActivity(intent);
    }

    private void showProgress(String message) {
        if (svProgressHUD == null) {
            svProgressHUD = new SVProgressHUD(this);
        }
        if (!svProgressHUD.isShowing()) {
            if (message != null) {
                svProgressHUD.showWithStatus(message, SVProgressHUD.SVProgressHUDMaskType.Clear);
            } else {
                svProgressHUD.showWithStatus("Loading", SVProgressHUD.SVProgressHUDMaskType.Clear);
            }
            svProgressHUD.show();
        }
    }

    private void hideProgress() {
        if (svProgressHUD != null && svProgressHUD.isShowing()) {
            svProgressHUD.dismiss();
            svProgressHUD.dismissImmediately();
        }
    }
}