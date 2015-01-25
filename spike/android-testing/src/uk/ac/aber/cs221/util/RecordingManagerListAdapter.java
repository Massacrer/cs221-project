package uk.ac.aber.cs221.util;

import uk.ac.aber.cs221.test.R;
import uk.ac.aber.cs221.test.RecordingManager;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class RecordingManagerListAdapter extends CursorAdapter {
   
   public RecordingManagerListAdapter(Context context, Cursor c,
         boolean autoRequery) {
      super(context, c, autoRequery);
   }
   
   @Override
   public View newView(Context context, Cursor cursor, ViewGroup parent) {
      return LayoutInflater.from(context).inflate(R.layout.rmgr_recording,
            parent, false);
   }
   
   @Override
   public void bindView(View view, Context context, Cursor cursor) {
      TextView recName = (TextView) view.findViewById(R.id.rmgr_recName);
      TextView recDetails = (TextView) view.findViewById(R.id.rmgr_recDetails);
      
      String recordingName = cursor.getString(cursor
            .getColumnIndexOrThrow("name"));
      String recordingDescription = cursor.getString(cursor
            .getColumnIndexOrThrow("description"));
      
      recName.setText(recordingName);
      recDetails.setText(recordingDescription);
      
      // tag the view with its id
      view.setTag(cursor.getLong(cursor.getColumnIndexOrThrow("_id")));
   }
}