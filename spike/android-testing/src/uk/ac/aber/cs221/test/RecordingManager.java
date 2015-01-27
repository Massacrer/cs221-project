package uk.ac.aber.cs221.test;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.Toast;
import uk.ac.aber.cs221.storage.RecordingStorage;
import uk.ac.aber.cs221.upload.Uploader;
import uk.ac.aber.cs221.util.RecordingManagerListAdapter;
import uk.ac.aber.cs221.util.Util;

/**
 * Handles the RecordingManager activity
 * 
 * @author was4
 */
public class RecordingManager extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording_management);
      this.setUpOnClickServer();
      setupList();
      setupOnClickListeners();
   }
   
   /**
    * Sets up the list of Recordings using a {@link RecordingManagerListAdapter}
    */
   private void setupList() {
      Cursor cursor = RecordingStorage.getInstance(this).getCursor();
      
      ListView listView = (ListView) findViewById(R.id.rmgr_list);
      listView.setAdapter(new RecordingManagerListAdapter(this, cursor, true));
      
      // DEBUG
      // String message = "Cursor contains " + cursor.getCount() + " rows";
      // Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }
   
   /**
    * Reloads the data in the list by simply getting a new Cursor
    */
   private void refreshList() {
      ListView list = (ListView) findViewById(R.id.rmgr_list);
      CursorAdapter adapter = (CursorAdapter) list.getAdapter();
      /*
       * adapter.notifyDataSetChanged(); list.refreshDrawableState();
       * list.invalidateViews();
       */
      adapter.changeCursor(RecordingStorage.getInstance(this).getCursor());
   }
   
   /**
    * Method to be called when a row is selected - open the Recording
    * corresponding to the row
    * 
    * @param selected
    *           The row that was selected
    */
   public void rowSelectCallback(View selected) {
      Intent intent = new Intent(this, RecordingActivity.class);
      intent.putExtra("id", (Long) selected.getTag());
      startActivity(intent);
   }
   
   /**
    * Sets up the delete button to delete rows that have their checkboxes
    * checked
    */
   private void setupOnClickListeners() {
      ((Button) findViewById(R.id.rmgr_deleteButton))
            .setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  List<ViewGroup> selected = getSelectedRows();
                  if (selected.size() > 0) {
                     for (ViewGroup vg : selected) {
                        // delete recording
                        RecordingStorage.getInstance(RecordingManager.this)
                              .delete((Long) vg.getTag());
                        
                        // uncheck old checkbox - necessary because adapter uses
                        // view recycling
                        CheckBox checkBox = (CheckBox) vg
                              .findViewById(R.id.rmgr_syncCheck);
                        checkBox.setChecked(false);
                        refreshList();
                     }
                  }
               }
            });
   }
   
   /**
    * @return A list of all rows that have been ticked
    */
   private List<ViewGroup> getSelectedRows() {
      ArrayList<ViewGroup> list = new ArrayList<ViewGroup>();
      ViewGroup parent = ((ViewGroup) findViewById(R.id.rmgr_list));
      for (int child = 0; child < parent.getChildCount(); child++) {
         ViewGroup listEntry = (ViewGroup) parent.getChildAt(child);
         CheckBox checkBox = (CheckBox) listEntry
               .findViewById(R.id.rmgr_syncCheck);
         if (checkBox.isChecked()) {
            list.add(listEntry);
         }
      }
      return list;
   }
   
   /**
    * Sets up the upload button handler
    */
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
               Uploader u = new Uploader();
               JSONObject j = new JSONObject();
               try {
                  j.put("name", "RecordingTest");
                  j.put("description", "We like to move it move it");
                  j.put("latitude", 50.5);
                  j.put("longitude", 20.6);
                  j.put("date", "0000-00-00-00");
                  j.put("user_name", "Riott");
                  j.put("user_number", "07528875810");
                  j.put("user_email", "acid_zepplin@hotmail.co.uk");
               }
               catch (JSONException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
               u.execute(j);
               
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
   }
}