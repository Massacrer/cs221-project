package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import uk.ac.aber.cs221.storage.*;
import uk.ac.aber.cs221.util.*;

/**
 * Handles the Record Species activity, dealing with a single species
 * 
 * @author was4
 */
public class RecordSpeciesActivity extends Activity {
   private GpsLocator gps;
   private Species species;
   private boolean isEdited = false;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_record_species);
      Util.setupActionBar(this);
      setupButtons();
      setupSpinner();
      setupPhotoPickerButtons();
   }
   
   @Override
   protected void onPause() {
      super.onPause();
      // only store on save
      // SpeciesStorage.getInstance(this).store(species);
      
      // prevent saving accidentally created species'
      if (!isEdited) {
         SpeciesStorage.getInstance(this).delete(species.id);
      }
   }
   
   @Override
   protected void onResume() {
      super.onResume();
      setupSpecies();
      setupFields();
   }
   
   /**
    * Populates the Spinner(dropdown list) with the abundance values
    */
   private void setupSpinner() {
      ((Spinner) findViewById(R.id.abundanceSpinner))
            .setAdapter(new ArrayAdapter<String>(this,
                  android.R.layout.simple_list_item_1, new String[] {
                        "Dominant", "Abundant", "Frequent", "Occasional",
                        "Rare" }));
   }
   
   /**
    * Initialises the internal Species object to a sensible value.
    * <p>
    * This is slightly tricky as this activity can be called in 2 different ways
    * - either to create a new species or to update an existing one. The cases
    * are differentiated by the presence of one of two keys in the intent's
    * extras - recordingId or speciesId. If neither are present, an exception is
    * thrown, as it is an error to attempt to edit the null species
    */
   private void setupSpecies() {
      SpeciesStorage storage = SpeciesStorage.getInstance(this);
      Bundle extras = getIntent().getExtras();
      if (extras != null) {
         long recordingId = extras.getLong("recordingId");
         long speciesId = extras.getLong("speciesId");
         
         if (recordingId != 0) {
            this.species = storage.createNew();
            this.species.recordingId = recordingId;
         }
         else
            if (speciesId != 0) {
               this.species = storage.get(speciesId);
               this.isEdited = true;
            }
            else {
               throw new RuntimeException(
                     "called RecordSpeciesActivity without ids");
            }
         
      }
   }
   
   /**
    * Sets the UI fields to values based on those in the internal Species object
    */
   private void setupFields() {
      ((TextView) findViewById(R.id.rec_sp_SpName)).setText(species.name);
      // work with imageView1 here
      ((TextView) findViewById(R.id.rec_sp_Comment)).setText(species.comment);
      ((Spinner) findViewById(R.id.abundanceSpinner))
            .setSelection(species.abundance);
      
      Cursor cursor = null;
      ((AutoCompleteTextView) findViewById(R.id.rec_sp_SpName))
            .setAdapter(new CursorAdapter(RecordSpeciesActivity.this, cursor,
                  false) {
               @Override
               public View newView(Context context, Cursor cursor,
                     ViewGroup parent) {
                  return null;
               }
               
               @Override
               public void bindView(View view, Context context, Cursor cursor) {
                  
               }
            });
   }
   
   /**
    * Sets up the save and delete buttons. Both end the activity.
    */
   private void setupButtons() {
      ((Button) findViewById(R.id.rec_sp_SaveButton))
            .setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  storeDetails();
                  SpeciesStorage.getInstance(RecordSpeciesActivity.this).store(
                        species);
                  RecordSpeciesActivity.this.finish();
               }
            });
      
      ((Button) findViewById(R.id.rec_sp_delete))
            .setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  SpeciesStorage.getInstance(RecordSpeciesActivity.this)
                        .delete(RecordSpeciesActivity.this.species.id);
                  RecordSpeciesActivity.this.finish();
               }
            });
   }
   
   /**
    * Fills the internal Species object's fields with data from the UI
    */
   private void storeDetails() {
      // TODO: fill in every field of this.species from the ui data here
      
      gps = new GpsLocator(RecordSpeciesActivity.this);
      if (gps.canGetLocation()) {
         double latitude = gps.getLatitude();
         double longitude = gps.getLongitude();
         
         // TODO: remove this call for prod
         Toast.makeText(
               RecordSpeciesActivity.this,
               "your Latitude is : " + latitude + "\nYour Longitude is : "
                     + longitude, Toast.LENGTH_LONG).show();
         Location temp = new Location("RPSRrec");
         temp.setLatitude(latitude);
         temp.setLongitude(longitude);
         species.loc = temp;
      }
      else {
         gps.showSettingsAlert();
      }
      
      AutoCompleteTextView nameField = (AutoCompleteTextView) findViewById(R.id.rec_sp_SpName);
      species.name = (nameField.getText().toString());
      TextView commentField = (TextView) findViewById(R.id.rec_sp_Comment);
      species.comment = (commentField.getText().toString());
      species.abundance = ((Spinner) findViewById(R.id.abundanceSpinner))
            .getSelectedItemPosition();
   }
   
   /**
    * Sets up the buttons that launch the photo picker activity
    */
   private void setupPhotoPickerButtons() {
      Button buttonScene = (Button) findViewById(R.id.rec_sp_ScenePic);
      buttonScene.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            Intent intent = new Intent(RecordSpeciesActivity.this,
                  PhotoPickerActivity.class);
            intent.putExtra("picture", "specimen");
            startActivityForResult(intent, 1);
         }
      });
      
      Button buttonSpecimen = (Button) findViewById(R.id.ma_ContinueButton);
      buttonSpecimen.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            Intent intent = new Intent(RecordSpeciesActivity.this,
                  PhotoPickerActivity.class);
            intent.putExtra("picture", "scene");
            startActivity(intent);
         }
      });
   }
}
