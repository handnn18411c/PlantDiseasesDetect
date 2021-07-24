package nhathando.com.plantdiseasesdetect.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class AppUtil {

    /**
     * This function design to conver Bitmap to File
     * @param context Activity or Fragment that use for crop image
     * @param bitmap bitmap from cropped image
     * @param fileNameToSave: file name like image.png
     * @return
     */
    public static File bitmapToFile(Context context, Bitmap bitmap, String fileNameToSave) {
        // create a file to write bitmap data
        File file = null;
        try {
            // Get the directory for the app's private pictures directory.
            file = new File(context.getExternalFilesDir(
                    Environment.DIRECTORY_DCIM), fileNameToSave);
            Log.d("FILEX1", file + "");
            int quality = 0;
            //Convert bitmap to byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, quality, bos); // can also save it in JPEG
            byte[] bitmapdata = bos.toByteArray();
            //write the bytes in file
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return file; // it will return null
        }
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        if(bm != null & !bm.isRecycled()) {
            bm.recycle();
            bm = null;
        }
        return resizedBitmap;
    }
}
