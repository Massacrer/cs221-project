package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import uk.ac.aber.cs221.util.GpsLocator;
import uk.ac.aber.cs221.util.Util;

public class RecordSpeciesActivity extends Activity {
	
	GpsLocator gps;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.setupActionBar(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_species);
		this.setupOnClickListener();

		((Spinner) findViewById(R.id.spinner1))
		.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "Dominant", "Abundant",
				"Frequent", "Occasional", "Rare" }));
	}
		
	
	public void setupOnClickListener() {
		Button buttonSave = (Button) findViewById(R.id.rec_sp_SaveButton);
		
		buttonSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				gps = new GpsLocator(RecordSpeciesActivity.this);
				
				if(gps.canGetLocation()){
					
					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();
					
					Toast.makeText(RecordSpeciesActivity.this, "your Latitude is : " + latitude + "\nYour Longitude is : " + longitude, Toast.LENGTH_LONG).show();
				}else{
					
					gps.showSettingsAlert();
				}
			}
		});
		Button buttonScene = (Button) findViewById(R.id.rec_sp_ScenePic);

		buttonScene.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecordSpeciesActivity.this,
						PhotoPickerActivity.class);
					startActivity(intent);
			}
		});
		Button buttonSpecimen = (Button) findViewById(R.id.ma_ContinueButton);

		buttonSpecimen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecordSpeciesActivity.this,
						PhotoPickerActivity.class);
					startActivity(intent);
			}
		});
	}
}
