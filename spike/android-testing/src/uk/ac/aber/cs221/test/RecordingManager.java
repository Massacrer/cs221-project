package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import uk.ac.aber.cs221.storage.RecordingStorage;
import uk.ac.aber.cs221.util.RecordingManagerListAdapter;
import uk.ac.aber.cs221.util.Util;

public class RecordingManager extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording_management);
      this.setUpOnClickServer();
      setupList();
   }
   
   private void setupList() {
      Cursor cursor = RecordingStorage.getInstance(this).getCursor();
      
      ListView listView = (ListView) findViewById(R.id.rmgr_list);
      listView.setAdapter(new RecordingManagerListAdapter(this, cursor, true));
      String message = "Cursor contains " + cursor.getCount() + " rows";
      Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(Menu.NONE, 42, 0, "layout");
      menu.add(Menu.NONE, 43, 0, "title");
      return super.onCreateOptionsMenu(menu);
   }
   
   private void setUpOnClickServer() {
      Button syncButton = (Button) findViewById(R.id.rmgr_syncButton);
      
      syncButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            ConnectivityManager cm = (ConnectivityManager) RecordingManager.this
                  .getSystemService(Context.CONNECTIVITY_SERVICE);
            
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null
                  && activeNetwork.isConnectedOrConnecting();
            
            if (isConnected) {
               
               // place code to start upload of selected data here
               Toast.makeText(RecordingManager.this,
                     "Internet connection is available", Toast.LENGTH_LONG)
                     .show();
               
            }
            else {
               AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                     RecordingManager.this);
               
               alertDialog.setTitle("Network Connection settings");
               alertDialog
                     .setMessage("No internet access. Please turn it on in settings");
               
               alertDialog.setPositiveButton("Settings",
                     new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           Intent intent = new Intent(Settings.ACTION_SETTINGS);
                           RecordingManager.this.startActivity(intent);
                        }
                     });
               
               alertDialog.setNegativeButton("Cancel",
                     new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                           dialog.cancel();
                        }
                     });
               
               alertDialog.show();
            }
         }
      });
      Button cancelButton = (Button) findViewById(R.id.rmgr_cancelButton);
      cancelButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            
         }
      });
   }
}