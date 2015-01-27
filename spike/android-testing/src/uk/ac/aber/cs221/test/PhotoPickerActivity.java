package uk.ac.aber.cs221.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity that allows the user to either take a new picture or select an
 * existing picture from the device storage. Returns the chosen image's filename
 * to the caller
 * 
 * @author was4
 */
public class PhotoPickerActivity extends Activity {

   private File currentImage;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_photo_picker);

      findViewById(R.id.pp_CameraButton).setOnClickListener(
            new OnClickListener() {
               public void onClick(View v) {
                  takePicture();
               }
            });
      findViewById(R.id.pp_SelectButton).setOnClickListener(
            new OnClickListener() {
               public void onClick(View v) {
                  selectPicture();
               }
            });
   }

   /**
    * Opens the device camera app to take a picture, and informs the device
    * media storage of the new image so that it can be added to e.g. the Gallery
    * view
    */
   private void takePicture() {
      String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      // vFile picture = new File(this.getFilesDir(), "img_" + now);

      Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

      try {
         File extDir = Environment
               .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
         this.currentImage = new File(extDir, now + ".jpg");
         currentImage.createNewFile();
         /*
          * this.currentImage = File.createTempFile("img_", ".jpg",
          * getFilesDir()
          * 
          * extDir);
          */
         cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
               Uri.fromFile(currentImage));

         Uri uri = new Uri.Builder().path(currentImage.getPath()).build();
         // DEBUG
         ((TextView) findViewById(R.id.pp_CameraButton))
               .setText(uri.toString());
         Log.i("CS221_test", uri.toString());

         mediaScanIntent.setData(uri);
         this.sendBroadcast(mediaScanIntent);

      } catch (IOException e) {
         Toast.makeText(this, "Could not save image file", Toast.LENGTH_LONG)
               .show();
         return;
      }

      if (cameraIntent.resolveActivity(getPackageManager()) != null) {
         startActivityForResult(cameraIntent, 1);
      } else {
         Toast.makeText(this, "No camera app available", Toast.LENGTH_LONG)
               .show();
      }
   }

   /**
    * Opens the device Media Picker activity, allowing the user to select an
    * image, whos filename is then returned to onActivityResult
    */
   private void selectPicture() {
      Intent intent = new Intent(Intent.ACTION_PICK,
            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
      intent.setType("image/*");
      startActivityForResult(Intent.createChooser(intent, "Select File"), 2);
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (resultCode == RESULT_OK) {
         switch (requestCode) {
         // 1 -> camera result
         case 1: {
            Intent mediaScanIntent = new Intent(
                  Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(currentImage);
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);

            displayBitmap();
            showPhotoChoiceAlert();
            break;
         }
         // 2 -> gallery result
         case 2:
            Uri image = data.getData();
            String[] path = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(image, path, null, null,
                  null);
            cursor.moveToFirst();

            currentImage = new File(cursor.getString(cursor
                  .getColumnIndex(path[0])));
            cursor.close();
            displayBitmap();
            showPhotoChoiceAlert();

            break;
         }
      }
   }

   /**
    * Displays the bitmap image stored in currentImage in the main view,
    * downscaling it as necessary to fit on the screen and minimise memory use
    */
   private void displayBitmap() {
      ImageView imageView = (ImageView) findViewById(R.id.imageView1);
      imageView.setVisibility(View.VISIBLE);

      BitmapFactory.Options bmOptions = new BitmapFactory.Options();
      bmOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(currentImage.getPath(), bmOptions);

      /*
       * int scaleFactor = calculateInSampleSize(bmOptions,
       * imageView.getWidth(), imageView.getHeight());
       */

      /*
       * int photoW = bmOptions.outWidth; int photoH = bmOptions.outHeight; int
       * scaleFactor = Math.min(photoW / imageView.getWidth(), photoH /
       * imageView.getHeight());
       */

      float rotation = 0;
      try {
         ExifInterface eif = new ExifInterface(currentImage.getPath());
         String orientation = eif.getAttribute(ExifInterface.TAG_ORIENTATION);
         if (orientation.equals("6"))
            rotation = 90;
         if (orientation.equals("3"))
            rotation = 180;
         if (orientation.equals("8"))
            rotation = 270;
      } catch (IOException e) {
      }

      boolean sideways = rotation == 90 || rotation == 270;

      int vW = imageView.getWidth();
      int vH = imageView.getHeight();

      int scaleFactor = bmOptions.inSampleSize = Math.max(bmOptions.outWidth
            / vW, bmOptions.outHeight / vH);

      Toast.makeText(this, "scale: " + scaleFactor, Toast.LENGTH_LONG).show();

      bmOptions.inJustDecodeBounds = false;
      // bmOptions.inSampleSize = scaleFactor;
      bmOptions.inPurgeable = true;

      Bitmap bitMap = BitmapFactory.decodeFile(currentImage.getPath(),
            bmOptions);

      Matrix matrix = new Matrix();
      matrix.preRotate(rotation);

      bitMap = Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(),
            bitMap.getHeight(), matrix, true);

      bitMap = Bitmap.createScaledBitmap(bitMap, bitMap.getWidth()
            / scaleFactor, bitMap.getHeight() / scaleFactor, false);

      imageView.setImageBitmap(bitMap);

   }

   public void showPhotoChoiceAlert() {

      AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
      alertDialog.setTitle("Photo choice");
      alertDialog.setMessage("Is this the photo you wish to use?");

      alertDialog.setPositiveButton("Yes",
            new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {

                  Intent data = new Intent();
                  data.putExtra("fileName", currentImage.getAbsolutePath());
                  data.putExtra("picture", PhotoPickerActivity.this.getIntent()
                        .getStringExtra("picture"));
                  // Activity finished ok, return the data
                  setResult(RESULT_OK, data);
                  finish();

                  // PhotoPickerActivity.this.finish();

               }
            });

      alertDialog.setNegativeButton("No",
            new DialogInterface.OnClickListener() {

               @Override
               public void onClick(DialogInterface dialog, int which) {
                  dialog.cancel();

               }
            });

      alertDialog.show();

   }
}
