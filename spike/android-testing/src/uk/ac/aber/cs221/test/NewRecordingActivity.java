package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewRecordingActivity extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_new_recording);
      this.setupOnClickListener();
   }
   
   private void setupOnClickListener() {
      Button button = (Button) findViewById(R.id.ma_StartButton);
      
      button.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent = new Intent(NewRecordingActivity.this,
                  RecordingActivity.class);
            String userName = ((EditText) findViewById(R.id.nr_Uname))
                  .getText().toString();
            String siteName = ((EditText) findViewById(R.id.nr_SiteName))
                  .getText().toString();
            String userNumber = ((EditText) findViewById(R.id.nr_uNumber))
                  .getText().toString();
            String userEmail = ((EditText) findViewById(R.id.nr_uEmail))
                  .getText().toString();
            
            intent.putExtra("userName", userName);
            intent.putExtra("siteName", siteName);
            intent.putExtra("userNumber", userNumber);
            intent.putExtra("userEmail", userEmail);
            
            startActivity(intent);
         }
      });
   }
}
