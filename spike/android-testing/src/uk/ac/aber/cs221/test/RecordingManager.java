package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import uk.ac.aber.cs221.util.Util;

public class RecordingManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.setupActionBar(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recording_management);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 42, 0, "layout");
		menu.add(Menu.NONE, 43, 0, "title");
		return super.onCreateOptionsMenu(menu);
	}

	private void setUpOnClickServer() {
		Button syncButton = (Button) findViewById(R.id.rmgr_syncButton);
		syncButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		Button cancelButton = (Button) findViewById(R.id.rmgr_cancelButton);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}