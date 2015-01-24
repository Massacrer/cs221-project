package uk.ac.aber.cs221.util;

import uk.ac.aber.cs221.test.R;
import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class RecordingActivityCursorListAdapter extends CursorAdapter implements
      ListAdapter {
   private final Context context;
   
   public RecordingActivityCursorListAdapter(Context context, Cursor c,
         boolean autoRequery) {
      super(context, c, autoRequery);
      this.context = context;
   }
   
   @Override
   public void bindView(View view, Context context, Cursor cursor) {
      
   }
   
   @Override
   public View newView(Context context, Cursor cursor, ViewGroup parent) {
      return LayoutInflater.from(context).inflate(R.layout.rec_species, null,
            false);
   }
   
   @Override
   public int getCount() {
      // TODO Auto-generated method stub
      return 0;
   }
   
   @Override
   public Object getItem(int position) {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public long getItemId(int position) {
      // TODO Auto-generated method stub
      return 0;
   }
   
   @Override
   public boolean hasStableIds() {
      // TODO Auto-generated method stub
      return false;
   }
   
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public int getItemViewType(int position) {
      // TODO Auto-generated method stub
      return 0;
   }
   
   @Override
   public int getViewTypeCount() {
      // TODO Auto-generated method stub
      return 0;
   }
   
   @Override
   public boolean isEmpty() {
      // TODO Auto-generated method stub
      return false;
   }
   
   @Override
   public boolean areAllItemsEnabled() {
      // TODO Auto-generated method stub
      return false;
   }
   
   @Override
   public boolean isEnabled(int position) {
      // TODO Auto-generated method stub
      return false;
   }
}
