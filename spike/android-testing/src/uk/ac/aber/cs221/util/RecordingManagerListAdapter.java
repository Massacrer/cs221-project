package uk.ac.aber.cs221.util;
import java.util.Calendar;
import java.util.List;
import uk.ac.aber.cs221.storage.Recording;
import uk.ac.aber.cs221.test.R;
import uk.ac.aber.cs221.test.RecordingManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RecordingManagerListAdapter extends BaseAdapter {

private  RecordingManager recordingManager;
private List <Recording> recordingList;


Calendar calendar = Calendar.getInstance();

public RecordingManagerListAdapter (RecordingManager context){
this.recordingManager=context;

}

@Override
public int getCount() {
if (recordingList==null) return 0;
else return recordingList.size();
}

@Override
public Object getItem(int position) {	
	return recordingList.get(position);
}

@Override
public long getItemId(int position) {
return position;
}

@Override
public View getView(int position, View convertView, ViewGroup parent) {
Recording r = recordingList.get(position);
View v = recordingManager.getLayoutInflater().inflate(R.id.recordingsList,null);
	return v;
}	

@Override
public boolean isEmpty() {
	if(recordingList.size()<=0 || recordingList==null){
    return true;
	}
	else return false;
}

}