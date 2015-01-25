package uk.ac.aber.cs221.test;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.ListView;
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
      setupOnClickListeners();
   }
   
   private void setupList() {
      Cursor cursor = RecordingStorage.getInstance(this).getCursor();
      
      ListView listView = (ListView) findViewById(R.id.rmgr_list);
      listView.setAdapter(new RecordingManagerListAdapter(this, cursor, true));
      String message = "Cursor contains " + cursor.getCount() + " rows";
      Toast.makeText(this, message, Toast.LENGTH_LONG).show();
   }
   
   private void refreshList() {
      ListView list = (ListView) findViewById(R.id.rmgr_list);
      CursorAdapter adapter = (CursorAdapter) list.getAdapter();
      /*
       * adapter.notifyDataSetChanged(); list.refreshDrawableState();
       * list.invalidateViews();
       */
      adapter.changeCursor(RecordingStorage.getInstance(this).getCursor());
   }
   
   public void rowSelectCallback(View selected) {
      Intent intent = new Intent(this, RecordingActivity.class);
      intent.putExtra("id", (Long) selected.getTag());
      startActivity(intent);
   }
   
   private void setupOnClickListeners() {
      ((Button) findViewById(R.id.rmgr_deleteButton))
            .setOnClickListener(new OnClickListener() {
               @Override
               public void onClick(View v) {
                  List<ViewGroup> selected = getSelectedRows();
                  if (selected.size() > 0) {
                     for (ViewGroup vg : selected) {
                        RecordingStorage.getInstance(RecordingManager.this)
                              .delete((Long) vg.getTag());
                        CheckBox checkBox = (CheckBox) vg
                              .findViewById(R.id.rmgr_syncCheck);
                        checkBox.setChecked(false);
                     }
                  }
                  refreshList();
               }
            });
   }
   
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
      Button cancelButton = (Button) findViewById(R.id.rmgr_deleteButton);
      cancelButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            
         }
      });
   }
}