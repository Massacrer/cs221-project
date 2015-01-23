package uk.ac.aber.cs221.util;

import java.util.Calendar;
import java.util.List;

import uk.ac.aber.cs221.storage.Species;
import uk.ac.aber.cs221.test.MainActivity;
import uk.ac.aber.cs221.test.R;
import uk.ac.aber.cs221.test.RecordingActivity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class RecordingActivityListAdapter extends BaseAdapter {
   
   private final RecordingActivity recordingActivity;
   private List<Species> speciesList;
   
   Calendar calendar = Calendar.getInstance();
   
   public RecordingActivityListAdapter(RecordingActivity context) {
      this.recordingActivity = context;
   }
   
   @Override
   public int getCount() {
      if (speciesList == null)
         return 0;
      else
         return speciesList.size();
   }
   
   @Override
   public Object getItem(int position) {
      return speciesList.get(position);
   }
   
   @Override
   public long getItemId(int position) {
      return position;
   }
   
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      // may need changing
      Species s = speciesList.get(position);
      View v = recordingActivity.getLayoutInflater().inflate(
            R.id.ra_speciesList, null);
      return v;
   }
   
   @Override
   public boolean isEmpty() {
      if (speciesList.size() <= 0 || speciesList == null) {
         return true;
      }
      else
         return false;
   }
   
}
