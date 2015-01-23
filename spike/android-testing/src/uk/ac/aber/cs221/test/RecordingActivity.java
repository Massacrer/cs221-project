package uk.ac.aber.cs221.test;

import uk.ac.aber.cs221.util.RecordingActivityListAdapter;
import uk.ac.aber.cs221.util.Util;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class RecordingActivity extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording);
      setupList();
   }
   
   private void setupList() {
      ListView list = (ListView) findViewById(R.id.ra_speciesList);
      list.setAdapter(new RecordingActivityListAdapter(this));
   }
   
}
