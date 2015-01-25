package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import uk.ac.aber.cs221.storage.*;
import uk.ac.aber.cs221.util.*;

public class RecordSpeciesActivity extends Activity {
   private GpsLocator gps;
   private Species species;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_record_species);
      Util.setupActionBar(this);
      setupSaveButton();
      setupSpinner();
      setupPhotoPickerButtons();
      setupSpecies();
   }
   
   private void setupSpinner() {
      ((Spinner) findViewById(R.id.spinner1))
            .setAdapter(new ArrayAdapter<String>(this,
                  android.R.layout.simple_list_item_1, new String[] {
                        "Dominant", "Abundant", "Frequent", "Occasional",
                        "Rare" }));
   }
   
   private void setupSpecies() {
      SpeciesStorage storage = SpeciesStorage.getInstance(this);
      long id = getIntent().getExtras().getLong("id");
      this.species = (id == 0) ? storage.createNew() : storage.get(id);
   }
   
   private void setupSaveButton() {
      Button buttonSave = (Button) findViewById(R.id.rec_sp_SaveButton);
      buttonSave.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            SpeciesStorage storage = SpeciesStorage
                  .getInstance(RecordSpeciesActivity.this);
            
            storeDetails();
            storage.store(species);
         }
      });
   }
   
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
         Location temp = new Location("");
         temp.setLatitude(latitude);
         temp.setLongitude(longitude);
         species.loc = temp;
      }
      else {
         gps.showSettingsAlert();
      }
      
      AutoCompleteTextView nameField = (AutoCompleteTextView) findViewById(R.id.rec_sp_SpName);
      species.name = (nameField.getText().toString());
      AutoCompleteTextView commentField = (AutoCompleteTextView) findViewById(R.id.rec_sp_Comment);
      species.comment = (commentField.getText().toString());
   }
   
   private void setupPhotoPickerButtons() {
      Button buttonScene = (Button) findViewById(R.id.rec_sp_ScenePic);
      buttonScene.setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
            Intent intent = new Intent(RecordSpeciesActivity.this,
                  PhotoPickerActivity.class);
            intent.putExtra("picture", "specimen");
            startActivity(intent);
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
