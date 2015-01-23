package uk.ac.aber.cs221.test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

	private void takePicture() {
		String now = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		// vFile picture = new File(this.getFilesDir(), "img_" + now);

		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		Intent mediaScanIntent = new Intent(
				Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

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
			((TextView) findViewById(R.id.pp_CameraButton)).setText(uri
					.toString());
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
				break;
			}
			// 2 -> gallery result
			case 2:
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
		 * int photoW = bmOptions.outWidth; int photoH = bmOptions.outHeight;
		 * int scaleFactor = Math.min(photoW / imageView.getWidth(), photoH /
		 * imageView.getHeight());
		 */

		float rotation = 0;
		try {
			ExifInterface eif = new ExifInterface(currentImage.getPath());
			String orientation = eif
					.getAttribute(ExifInterface.TAG_ORIENTATION);
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

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(Menu.NONE, 42, 0, "RecordingManager");
		menu.add(Menu.NONE, 43, 0, "Main");
		menu.add(Menu.NONE, 44, 0, "New Recording");
		menu.add(Menu.NONE, 45, 0, "Recording");
		menu.add(Menu.NONE, 46, 0, "Record Species");
		menu.add(Menu.NONE, 47, 0, "Photo Picker");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Class clazz = null;
		switch (id) {
		case R.id.action_settings: {
			clazz = SettingsActivity.class;
			break;
		}
		case 42: {
			clazz = RecordingManager.class;
			break;
		}
		case 43: {
			clazz = MainActivity.class;
			break;
		}
		case 44: {
			clazz = NewRecordingActivity.class;
			break;
		}
		case 45: {
			clazz = RecordingActivity.class;
			break;
		}
		case 46: {
			clazz = RecordSpeciesActivity.class;
			break;
		}
		case 47: {
			clazz = PhotoPickerActivity.class;
			break;
		}
		}
		if (clazz != null) {
			this.startActivity(new Intent(this, clazz));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
