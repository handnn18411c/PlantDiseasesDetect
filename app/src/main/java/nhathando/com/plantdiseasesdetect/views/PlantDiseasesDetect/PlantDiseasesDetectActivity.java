package nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.models.PlantDiseases;
import nhathando.com.plantdiseasesdetect.util.AppUtil;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import nhathando.com.plantdiseasesdetect.views.HomeActivity;
import nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail.PlantDiseaseDetailActivity;
import nhathando.com.plantdiseasesdetect.views.PlantDiseasesDetail.PlantDiseasesDetailViewModel;

public class PlantDiseasesDetectActivity extends BaseActivity {

    private PlantDiseasesDetectViewModel plantDiseasesDetectViewModel;
    private Bundle bundle;
    private String name;
    private String rate;
    private SVProgressHUD svProgressHUD;
    private boolean isDetect;

    @BindView(R.id.imgBounding)
    ImageView imgBounding;
    @BindView(R.id.imgCheck)
    ImageView imgCheck;
    @BindView(R.id.tvTenBenh)
    TextView tvTenBenh;
    @BindView(R.id.tvDoChinhXac)
    TextView tvDoChinhXac;
    @BindView(R.id.tvXacDinh)
    TextView tvXacDinh;
    @BindView(R.id.btnThongtinbenh)
    Button btnThongtinbenh;

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
        if (bundle != null) {
            Bitmap croppedImage = bundle.getParcelable(Constant.IMAGE_BITMAP);
            imgBounding.setImageBitmap(croppedImage);
            File selectedImage = AppUtil.bitmapToFile(this, croppedImage, "plant.png");
            showProgress("Please wait");
            plantDiseasesDetectViewModel.getPlantDisease(selectedImage).observe(this, model -> {
                if (model != null) {
                    String data = model.getResult();
                    JSONArray jsonArr = null;
                    try {
                        jsonArr = new JSONArray(data);
                        if(jsonArr.length()==0) {
                            isDetect = false;
                            tvXacDinh.setText("Không nhận diện được");
                            btnThongtinbenh.setText("Trở lại");
                            imgCheck.setImageResource(R.drawable.ic_baseline_close_24);
                        } else {
                            isDetect = true;
                            tvXacDinh.setText("Đã nhận diện");
                            imgCheck.setImageResource(R.drawable.ic_baseline_check_24);
                        }
                        float rateDetect = 0f;
                        if (jsonArr.length() != 0) {
                            name = jsonArr.getJSONObject(jsonArr.length() - 1).get("name") + "";
                            rate = jsonArr.getJSONObject(jsonArr.length() - 1).get("conf") + "";
                        } else {
                            name = jsonArr.getJSONObject(0).get("name") + "";
                        }
                        rate = rate.substring(7, rate.length() - 1);
                        rateDetect = Float.parseFloat(rate) * 100;
                        tvDoChinhXac.setText(Math.round(rateDetect) + "%");
                        String disease_info = plantDiseasesDetectViewModel.loadJSONFromAsset(this);
                        setDiseasesInfo(disease_info, name);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    tvXacDinh.setText("Không nhận diện được");
                    imgCheck.setImageResource(R.drawable.ic_baseline_close_24);
                }
            });
            hideProgress();
        }
    }

    private void setDiseasesInfo(String disease_info, String disease_name) {
        if(disease_info != null) {
            try {
                JSONArray jsonArr = new JSONArray(disease_info);
                for (int i = 0; i < jsonArr.length(); i++) {
                    JSONObject jsonObject = jsonArr.getJSONObject(i);
                    String name = jsonObject.get("name") + "";
                    if (name.equalsIgnoreCase(disease_name)) {
                        String vnName = jsonObject.get("vn_name") + "";
                        tvTenBenh.setText(vnName);
                        String fullName = jsonObject.getString("full_name") + "";
                        String symptom = jsonObject.get("symptom") + "";
                        String solution = jsonObject.get("solution") + "";
                        String medicine = jsonObject.get("medicines") + "";
                        bundle.putString(Constant.PLANT_DISEASE_SCIENCE_NAME, fullName);
                        bundle.putString(Constant.PLANT_DISEASE_SYMPTOM, symptom);
                        bundle.putString(Constant.PLANT_DISEASE_SOLUTION, solution);
                        bundle.putString(Constant.PLANT_DISEASE_MEDICINES, medicine);
                        break;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            tvXacDinh.setText("Không nhận diện được");
            imgCheck.setImageResource(R.drawable.ic_baseline_close_24);
        }
    }

    public void switchActivity(View view) {
        Intent intent = new Intent(PlantDiseasesDetectActivity.this, PlantDiseaseDetailActivity.class);
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

    @OnClick(R.id.btnThongtinbenh)
    public void diseaseInfo() {
        if(isDetect) {
            showActivity(PlantDiseaseDetailActivity.class, bundle);
        } else {
            showActivity(HomeActivity.class);
        }
    }
}