package uk.ac.aber.cs221.test;

import uk.ac.aber.cs221.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.setupOnClickListener();
	}

	private void setupOnClickListener() {
		Button buttonStart = (Button) findViewById(R.id.ma_StartButton);
		Button buttonContinue = (Button)findViewById(R.id.ma_ContinueButton);
		Button buttonManage = (Button) findViewById(R.id.ma_ManageRecordingsButton);
		Button buttonSettings = (Button) findViewById(R.id.ma_SettingsButton);

		buttonStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						NewRecordingActivity.class);
				startActivity(intent);
			}
		});

		
		  buttonContinue.setOnClickListener(new OnClickListener() {
		  
		  @Override public void onClick(View v) { Intent intent = new
		  Intent(MainActivity.this, NewRecordingActivity.class);
		  startActivity(intent); } });
		 

		buttonManage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						RecordingManager.class);
				startActivity(intent);
			}
		});

		buttonSettings.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SettingsActivity.class);
				startActivity(intent);
			}
		});

	}
}
