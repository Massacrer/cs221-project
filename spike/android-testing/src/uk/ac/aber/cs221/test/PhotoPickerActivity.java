package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import uk.ac.aber.cs221.util.Util;

public class PhotoPickerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.setupActionBar(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_picker);
	}
}
