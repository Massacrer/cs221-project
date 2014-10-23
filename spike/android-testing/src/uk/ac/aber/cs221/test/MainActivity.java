package uk.ac.aber.cs221.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.ac.aber.cs221.test.R;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

// TODO: prototype camera functionality - open system camera app, get picture

public class MainActivity extends Activity {
   
   private File currentImage;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      
      setContentView(R.layout.activity_main);
      findViewById(R.id.cameraButton).setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            takePicture();
         }
      });
      findViewById(R.id.galleryButton).setOnClickListener(
            new OnClickListener() {
               public void onClick(View v) {
                  selectPicture();
               }
            });
   }
   
   private void takePicture() {
      String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
      // vFile picture = new File(this.getFilesDir(), "img_" + now);
      
      Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
      
      try {
         File extDir = Environment
               .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
         this.currentImage = File.createTempFile("img_", "", /* getFilesDir() */
               extDir);
         cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
               Uri.fromFile(currentImage));
         
         Uri uri = new Uri.Builder().path(currentImage.getPath()).build();
         // DEBUG
         ((TextView) findViewById(R.id.textView1)).setText(uri.toString());
         Log.i("CS221_test", uri.toString());
      }
      catch (IOException e) {
         Toast.makeText(this, "Could not save image file", Toast.LENGTH_LONG)
               .show();
         return;
      }
      
      if (cameraIntent.resolveActivity(getPackageManager()) != null) {
         startActivityForResult(cameraIntent, 1);
      }
      else {
         Toast.makeText(this, "No camera app available", Toast.LENGTH_LONG)
               .show();
      }
   }
   
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
               displayBitmap();
               break;
            }
            // 2 -> gallery result
            case 2: {
               Uri image = data.getData();
               String[] path = { MediaStore.Images.Media.DATA };
               Cursor cursor = getContentResolver().query(image, path, null,
                     null, null);
               cursor.moveToFirst();
               
               currentImage = new File(cursor.getString(cursor
                     .getColumnIndex(path[0])));
               cursor.close();
               displayBitmap();
               break;
            }
         }
      }
   }
   
   private void displayBitmap() {
      ImageView imageView = (ImageView) findViewById(R.id.imageView1);
      imageView.setVisibility(View.VISIBLE);
      
      BitmapFactory.Options bmOptions = new BitmapFactory.Options();
      bmOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeFile(currentImage.getPath(), bmOptions);
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
      }
      catch (IOException e) {
      }
      
      boolean sideways = rotation == 90 || rotation == 270;
      
      int scaleFactor = bmOptions.inSampleSize = Math
            .min(bmOptions.outWidth / imageView.getWidth(), bmOptions.outHeight
                  / imageView.getHeight());
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
   
   private static int calculateInSampleSize(BitmapFactory.Options options,
         int reqWidth, int reqHeight) {
      // Raw height and width of image
      final int height = options.outHeight;
      final int width = options.outWidth;
      int inSampleSize = 1;
      
      if (height > reqHeight || width > reqWidth) {
         
         final int halfHeight = height / 2;
         final int halfWidth = width / 2;
         
         // Calculate the largest inSampleSize value that is a power of 2 and
         // keeps both
         // height and width larger than the requested height and width.
         while ((halfHeight / inSampleSize) > reqHeight
               && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
         }
      }
      
      return inSampleSize;
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      // Handle action bar item clicks here. The action bar will
      // automatically handle clicks on the Home/Up button, so long
      // as you specify a parent activity in AndroidManifest.xml.
      int id = item.getItemId();
      if (id == R.id.action_settings) {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
}
