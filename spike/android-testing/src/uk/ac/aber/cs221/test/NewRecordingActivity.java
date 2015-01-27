package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import uk.ac.aber.cs221.util.Util;

/**
 * Essentially just sets up the onClickListener for the "start recording" button
 * in the activity, passing the user and site details to RecordingActivity
 * 
 * @author was4
 */
public class NewRecordingActivity extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_new_recording);
      this.setupOnClickListener();
   }
   
   /**
    * Sets up the onClickListener for the "start recording" button, passing the
    * contents of the TextViews as extras in the Intent
    */
   private void setupOnClickListener() {
      Button button = (Button) findViewById(R.id.nr_StartButton);
      
      button.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(NewRecordingActivity.this,
                  RecordingActivity.class);
            String noInts = "^[a-z A-Z]+$";
            String noChars = "^[0-9]";
            String emailShortVer = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            boolean noErrors = true;
            
            // get strings from UI
            String userName = ((EditText) findViewById(R.id.nr_Uname))
                  .getText().toString();
            if (!userName.matches(".+")) {
               Toast.makeText(NewRecordingActivity.this,
                     "Please enter your name", Toast.LENGTH_LONG).show();
               noErrors = false;
            }
            
            String siteName = ((EditText) findViewById(R.id.nr_SiteName))
                  .getText().toString();
            if (!siteName.matches(".+")) {
               Toast.makeText(NewRecordingActivity.this,
                     "Please enter the site name", Toast.LENGTH_LONG).show();
               noErrors = false;
            }
            
            String userNumber = ((EditText) findViewById(R.id.nr_uNumber))
                  .getText().toString();
            if (!userNumber.matches(noChars) && userNumber.length() != 11) {
               Toast.makeText(NewRecordingActivity.this,
                     "Please enter a valid phone number", Toast.LENGTH_LONG)
                     .show();
               noErrors = false;
            }
            
            String userEmail = ((EditText) findViewById(R.id.nr_uEmail))
                  .getText().toString();
            if (!userEmail.matches(emailShortVer)) {
               Toast.makeText(NewRecordingActivity.this,
                     "Please enter a valid email", Toast.LENGTH_LONG).show();
               noErrors = false;
            }
            
            String siteDescription = ((EditText) findViewById(R.id.nr_siteDescription))
                  .getText().toString();
            
            // add strings to intent if passes validation
            if (noErrors == true) {
               intent.putExtra("siteName", siteName);
               intent.putExtra("siteDescription", siteDescription);
               intent.putExtra("userName", userName);
               intent.putExtra("userNumber", userNumber);
               intent.putExtra("userEmail", userEmail);
               
               startActivity(intent);
            }
         }
      });
   }
}
