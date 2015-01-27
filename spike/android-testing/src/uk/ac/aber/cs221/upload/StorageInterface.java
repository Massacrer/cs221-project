package uk.ac.aber.cs221.upload;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.database.Cursor;
import uk.ac.aber.cs221.storage.Recording;
import uk.ac.aber.cs221.storage.RecordingStorage;
import uk.ac.aber.cs221.storage.Species;
import uk.ac.aber.cs221.storage.SpeciesStorage;
import uk.ac.aber.cs221.storage.Storage;
import static org.json.JSONObject.quote;

/**
 * Class to assist in converting stored Recordings and Species' into
 * serializable form for upload
 * 
 * @author was4
 */
public class StorageInterface {
   
   private static String quote(String s) {
      return s;
   }
   
   public static String serverDateString(Date date) {
      String format = "yyyy-MM-dd HH:mm:ss";
      return new SimpleDateFormat(format, Locale.UK).format(date);
   }
   
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
         values.put("date", recording.date == null ? null
               : serverDateString(recording.date));
         
         values.put("user_name", quote(recording.userName));
         values.put("user_number", quote(recording.userNumber));
         values.put("user_email", quote(recording.userEmail));
         JSONArray speciesArray = new JSONArray();
         
         SpeciesStorage storage = SpeciesStorage.getInstance(context);
         Cursor cursor = storage.getByRecordingId(recording.id);
         cursor.moveToFirst();
         
         while (!cursor.isAfterLast()) {
            JSONObject speciesObject = new JSONObject();
            Species species = storage.get(cursor.getLong(cursor
                  .getColumnIndexOrThrow("_id")));
            
            speciesObject.put("abundance", species.abundance);
            speciesObject.put("date",
                  quote(Storage.dateTimeString(species.date)));
            speciesObject.put("name", quote(species.name));
            speciesObject.put("comment", quote(species.comment));
            speciesObject.put("latitude", species.loc.getLatitude());
            speciesObject.put("longitude", species.loc.getLongitude());
            // handle image file upload here
            
            speciesArray.put(speciesObject);
            cursor.moveToNext();
         }
         values.put("species", speciesArray);
      }
      catch (JSONException e) {
         return null;
      }
      return values;
   }
   
}
