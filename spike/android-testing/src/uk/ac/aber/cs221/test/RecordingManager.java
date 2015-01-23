package uk.ac.aber.cs221.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
<<<<<<< Updated upstream
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
=======
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
>>>>>>> Stashed changes
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< Updated upstream
import android.widget.Toast;
import uk.ac.aber.cs221.util.Util;

public class RecordingManager extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Util.setupActionBar(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recording_management);
		this.setUpOnClickServer();
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
					
					//place code to start upload of selected data here
					Toast.makeText(RecordingManager.this, "Internet connection is available", Toast.LENGTH_LONG).show();

				} else {
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(RecordingManager.this);
					
					alertDialog.setTitle("Network Connection settings");
					alertDialog.setMessage("No internet access. Please turn it on in settings");
					
					alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(Settings.ACTION_SETTINGS);
							RecordingManager.this.startActivity(intent);
						}
					});
					
					alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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
=======
import android.widget.TextView;
import uk.ac.aber.cs221.util.Util;

public class RecordingManager extends Activity {
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      Util.setupActionBar(this);
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_recording_management);
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
            
         }
      });
      Button cancelButton = (Button) findViewById(R.id.rmgr_cancelButton);
      cancelButton.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            
         }
      });
   }
   
   class RecordingAdapter extends CursorAdapter {
      
      public RecordingAdapter(Context context, Cursor cursor) {
         super(context, cursor, CursorAdapter.FLAG_AUTO_REQUERY);
      }
      
      @Override
      public View newView(Context context, Cursor cursor, ViewGroup parent) {
         return LayoutInflater.from(context).inflate(R.layout.rmgr_recording,
               parent, false);
      }
      
      @Override
      public void bindView(View view, Context context, Cursor cursor) {
         TextView name = (TextView) view.findViewById(R.id.rmgr_RecName);
         TextView details = (TextView) view.findViewById(R.id.rmgr_RecDetails);
         
         name.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
         details.setText(cursor.getString(cursor
               .getColumnIndexOrThrow("description")));
      }
      
   }
>>>>>>> Stashed changes
}