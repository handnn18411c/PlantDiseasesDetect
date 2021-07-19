package nhathando.com.plantdiseasesdetect.views.ImagePreview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Random;

import butterknife.BindView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import butterknife.OnClick;
import nhathando.com.plantdiseasesdetect.R;
import nhathando.com.plantdiseasesdetect.util.Constant;
import nhathando.com.plantdiseasesdetect.views.BaseActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ImagePreviewActivity extends BaseActivity {

    private ImagePreviewViewModel imagePreviewViewModel;
    private String function;
    private Bundle bundle;

    @BindView(R.id.imgPlant)
    CropImageView imgPlant;
    @BindView(R.id.btnAgain)
    Button btnAgain;
    @BindView(R.id.btnUseImage)
    Button btnUserImage;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void createView() {
        initView();
    }

    private void initView() {
        imagePreviewViewModel = new ViewModelProvider(this).get(ImagePreviewViewModel.class);
        Bundle bundle = getIntent().getBundleExtra(Constant.KEY_EXTRA);
        if (bundle != null) {
            Uri uri = bundle.getParcelable(Constant.IMAGE_CAPTURE_URI);
            imgPlant.setImageUriAsync(uri);
           //  cropImageView.setImageUriAsync(uri);
            String function = bundle.getString(Constant.KEY_CAMERA_OR_GALLERY);
            String cameraActivity = getResources().getString(R.string.ActivityCamera);
            if(cameraActivity.equals(function)) {
                btnAgain.setText(getResources().getString(R.string.Camera));
            } else {
                btnAgain.setText(getResources().getString(R.string.Gallery));
            }

        }
    }

    @OnClick(R.id.btnAgain)
    public void again() {
        if(function.equals(getResources().getString(R.string.ActivityCamera))) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, Constant.REQUEST_IMAGE_CAPTURE);
        } else {
            //TODO
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @OnClick(R.id.btnUseImage)
    public void sendToServer() {
        Bitmap croppedImage = imgPlant.getCroppedImage();
        File file = bitmapToFile(this , croppedImage , "plant.png") ;
     //   imgPlant.setImageBitmap(croppedImage);
        Log.d("aaaaaaaa" , file.getAbsolutePath());
//        Log.d("aaaaaaaaaa" , Environment.getExternalStorageDirectory().toString());
//        Log.d("aaaaaaaaaa" , Environment.getExternalStorageDirectory().toPath().toString());
       // SaveImage(croppedImage);
   //    Bitmap bmImg = BitmapFactory.decodeFile(file.getPath());
    //   imgPlant.setImageBitmap(bmImg);
      //  imgPlant.setImageUriAsync(Uri.fromFile(file));


    }
    private void SaveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ())
            file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + Environment.getExternalStorageDirectory())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
    public static File bitmapToFile(Context context,Bitmap bitmap, String fileNameToSave) { // File name like "image.png"
        //create a file to write bitmap data
        File file = null;
        try {

           file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + fileNameToSave);
//            File dir = new File(sdcard.getAbsolutePath() + " /dir - name /");
//            Log.d("aaaaa" , dir.toString());
//            dir.mkdir();
//            File file1 = new File(dir, fileNameToSave);
//            Log.d("aaaaa" , file1.toString());


//Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , bos); // YOU can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        }catch (Exception e){
            e.printStackTrace();
            return file; // it will return null
        }
    }


}