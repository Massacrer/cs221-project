package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class RecordingManager extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording_management);
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(Menu.NONE, 42, 0, "layout");
      menu.add(Menu.NONE, 43, 0, "title");
      return super.onCreateOptionsMenu(menu);
   }
}
