package uk.ac.aber.cs221.test;

import java.util.Calendar;

import uk.ac.aber.cs221.storage.*;
import uk.ac.aber.cs221.util.*;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Implements the Recording activity, containing the list of species' found in
 * the current recording
 * 
 * @author was4
 */
public class RecordingActivity extends Activity {
   private Recording recording;
   private RecordingStorage storage;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording);
      
      this.setupOnClickListeners();
      this.setupRecording();
      this.setupList();
   }
   
   @Override
   public void onPause() {
      super.onPause();
      storeRecording();
   }
   
   @Override
   protected void onResume() {
      super.onResume();
      CursorAdapter adapter = (CursorAdapter) ((ListView) findViewById(R.id.ra_speciesList))
            .getAdapter();
      adapter.changeCursor(SpeciesStorage.getInstance(this).getByRecordingId(
            this.recording.id));
   }
   
   /**
    * Method to be called whenever a row is selected. Assigned in the adapter
    * 
    * @see RecordingActivityCursorListAdapter
    * 
    * @param selected
    *           The row that was selected
    */
   public void rowSelectCallback(View selected) {
      Intent intent = new Intent(this, RecordSpeciesActivity.class);
      intent.putExtra("speciesId", (Long) selected.getTag());
      startActivity(intent);
   }
   
   /**
    * Stores this recording in the database
    */
   private void storeRecording() {
      storage.store(recording);
   }
   
   /**
    * Sets up the internal Recording object so that it is non-null and
    * references a valid record in the database by its id field, creating a new
    * one if necessary.
    */
   private void setupRecording() {
      // TODO:DEBUG REMOVE THIS CALL FOR PRODUCTION
      // this.deleteDatabase("database");
      
      storage = RecordingStorage.getInstance(this);
      
      long id = getIntent().getExtras().getLong("id");
      recording = storage.get(id);
      
      // check if id references a valid recording, if not create new
      if (recording == null) {
         recording = storage.createNew();
         
         // set recording.date to now
         recording.date = Calendar.getInstance().getTime();
         recording.userName = getIntent().getStringExtra("userName");
         recording.userNumber = getIntent().getStringExtra("userNumber");
         recording.userEmail = getIntent().getStringExtra("userEmail");
         recording.name = getIntent().getStringExtra("siteName");
         recording.description = getIntent().getStringExtra("siteDescription");
      }
      
      // record that this was the recording we edited last
      this.getSharedPreferences("prefs", MODE_PRIVATE).edit()
            .putLong("lastRecording", recording.id).commit();
   }
   
   /**
    * Sets up the ListView containing all of the Species' in this recording.
    * Gets the Cursor containing the result of the database query and passes it
    * to a new {@link RecordingActivityCursorListAdapter} to automatically
    * populate the list
    */
   private void setupList() {
      ListView list = (ListView) findViewById(R.id.ra_speciesList);
      
      Cursor cursor = SpeciesStorage.getInstance(this).getByRecordingId(
            this.recording.id);
      
      list.setAdapter(new RecordingActivityCursorListAdapter(this, cursor, true));
      TextView emptyView = new TextView(this);
      emptyView.setText("No species' recorded");
      // TODO: make this not break the layout
      // list.setEmptyView(emptyView);
      
      // DEBUG
      // String message = "Cursor contains " + cursor.getCount() + " rows";
      // Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }
   
   /**
    * Sets up the button to start the RecordSpeciesActivity when the
    * "add species" button is clicked, passing the current recording id
    */
   private void setupOnClickListeners() {
      Button buttonAddSpecies = (Button) findViewById(R.id.ra_newSpecies);
      buttonAddSpecies.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(RecordingActivity.this,
                  RecordSpeciesActivity.class);
            intent.putExtra("recordingId", RecordingActivity.this.recording.id);
            startActivity(intent);
         }
      });
   }
}
