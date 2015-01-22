package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import uk.ac.aber.cs221.util.Util;

public class RecordSpeciesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.setupActionBar(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_species);

		((Spinner) findViewById(R.id.spinner1))
		.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, new String[] { "Dominant", "Abundant",
				"Frequent", "Occasional", "Rare" }));

	}
}
