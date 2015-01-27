package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main activity of the app, reached on launch. See xml file for layout. This
 * class sets up action listeners for the main activity buttons
 * 
 * @author was4
 */
public class MainActivity extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }
   
   @Override
   protected void onResume() {
      super.onResume();
      this.setupOnClickListener();
   }
   
   /**
    * Sets up the onClickListeners for the buttons in this activity
    */
   private void setupOnClickListener() {
      Button buttonStart = (Button) findViewById(R.id.ma_StartButton);
      Button buttonContinue = (Button) findViewById(R.id.ma_ContinueButton);
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
      
      final long lastRecordingId = getSharedPreferences("prefs", MODE_PRIVATE)
            .getLong("lastRecording", 0);
      if (lastRecordingId == 0) {
         buttonContinue.setEnabled(false);
      }
      else {
         buttonContinue.setEnabled(true);
      }
      
      buttonContinue.setOnClickListener(new OnClickListener() {
         
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,
                  RecordingActivity.class);
            
            intent.putExtra("id", lastRecordingId);
            startActivity(intent);
         }
      });
      
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
