package uk.ac.aber.cs221.test;

import uk.ac.aber.cs221.storage.Recording;
import uk.ac.aber.cs221.storage.RecordingStorage;
import uk.ac.aber.cs221.util.RecordingActivityListAdapter;
import uk.ac.aber.cs221.util.Util;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RecordingActivity extends Activity {
   private Recording recording;
   private RecordingStorage storage;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording);
      
      this.setupList();
      this.setupOnClickListener();
      this.setupRecording();
   }
   
   private void setupRecording() {
      storage = RecordingStorage.getInstance(this);
      
      int id = getIntent().getExtras().getInt("id");
      recording = (id == 0) ? storage.createNew() : storage.get(id);
   }
   
   private void setupList() {
      ListView list = (ListView) findViewById(R.id.ra_speciesList);
      list.setAdapter(new RecordingActivityListAdapter(this));
   }
   
   private void setupOnClickListener() {
      Button buttonAddSpecies = (Button) findViewById(R.id.ra_newSpecies);
      
      buttonAddSpecies.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(RecordingActivity.this,
                  RecordSpeciesActivity.class);
            startActivity(intent);
         }
         
      });
   }
   
}
