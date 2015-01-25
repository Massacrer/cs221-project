package uk.ac.aber.cs221.test;

import uk.ac.aber.cs221.storage.*;
import uk.ac.aber.cs221.util.*;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class RecordingActivity extends Activity {
   private Recording recording;
   private RecordingStorage storage;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording);
      
      this.setupOnClickListener();
      this.setupRecording();
      this.setupList();
   }
   
   @Override
   public void onPause() {
      super.onPause();
      storeSpeciesList();
   }
   
   private void storeSpeciesList() {
      // TODO: store list of species in recording - possibly add them elsewhere
      storage.store(recording);
   }
   
   private void setupRecording() {
      // TODO:DEBUG REMOVE THIS CALL FOR PRODUCTION
      this.deleteDatabase("database");
      
      storage = RecordingStorage.getInstance(this);
      
      long id = getIntent().getExtras().getLong("id");
      recording = (id == 0) ? storage.createNew() : storage.get(id);
   }
   
   private void setupList() {
      ListView list = (ListView) findViewById(R.id.ra_speciesList);
      
      Cursor cursor = SpeciesStorage.getInstance(this).getByRecordingId(
            this.recording.id);
      
      list.setAdapter(new RecordingActivityCursorListAdapter(this, cursor,
            false));
      TextView emptyView = new TextView(this);
      emptyView.setText("No species' recorded");
      list.setEmptyView(emptyView);
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
