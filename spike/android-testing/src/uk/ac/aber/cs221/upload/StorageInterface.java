package uk.ac.aber.cs221.upload;

import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.JsonWriter;
import uk.ac.aber.cs221.storage.Recording;
import uk.ac.aber.cs221.storage.RecordingStorage;
import uk.ac.aber.cs221.storage.Storage;
import static org.json.JSONObject.quote;

public class StorageInterface {
   public JSONObject getRecording(Context context, long id) {
      Recording recording = RecordingStorage.getInstance(context).get(id);
      JSONObject values = new JSONObject();
      try {
         values.put("name", quote(recording.name));
         values.put("description", quote(recording.description));
         values.put(
               "latitude",
               recording.loc == null ? "" : JSONObject
                     .numberToString(recording.loc.getLatitude()));
         values.put(
               "longitude",
               recording.loc == null ? "" : JSONObject
                     .numberToString(recording.loc.getLongitude()));
         values.put(
               "date",
               recording.date == null ? null : Storage
                     .dateTimeString(recording.date));
         
         values.put("user_name", quote(recording.userName));
         values.put("user_number", quote(recording.userNumber));
         values.put("user_email", quote(recording.userEmail));
         JSONArray speciesArray = new JSONArray();
         values.put("species", speciesArray);
         
         // not needed
         // JsonWriter writer = new JsonWriter(new
         // OutputStreamWriter(System.out));
      }
      catch (JSONException e) {
      }
      return null;
   }
   
}
