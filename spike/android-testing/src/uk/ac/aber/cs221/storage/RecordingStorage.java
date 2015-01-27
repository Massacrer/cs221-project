package uk.ac.aber.cs221.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

/**
 * @author was4
 * 
 *         Implementation of Storage that deals with Recordings. Contains logic
 *         to construct instances from the database, and store them back
 */
public class RecordingStorage extends Storage<Recording> {
   private static RecordingStorage instance;
   public static final String table = "recordings";
   private Storage.DatabaseHelper database;
   
   /**
    * Private constructor to enforce use of factory method for singleton pattern
    * 
    * @param context
    *           Context to use for database access, required by
    *           {@link DatabaseHelper}
    */
   private RecordingStorage(Context context) {
      instance = this;
      database = new Storage.DatabaseHelper(context);
   }
   
   /**
    * Gets the singleton instance of this class
    * 
    * @param context
    *           The {@link Context} to use to enable database access
    * @return The instance of this class
    */
   public static RecordingStorage getInstance(Context context) {
      if (instance == null) {
         instance = new RecordingStorage(context);
      }
      return instance;
   }
   
   /**
    * @return A {@link Cursor} containing all Recordings in the database table
    */
   public Cursor getCursor() {
      SQLiteDatabase connection = database.getReadableDatabase();
      Cursor cursor = connection.query(table, null, null, null, null, null,
            null);
      // database.close(); // needs to be left open for the cursor to work
      return cursor;
   }
   
   @Override
   public void store(Recording recording) {
      ContentValues values = new ContentValues();
      values.put("name", recording.name);
      values.put("description", recording.description);
      values.put("latitude",
            recording.loc == null ? null : recording.loc.getLatitude());
      values.put("longitude",
            recording.loc == null ? null : recording.loc.getLongitude());
      values.put("date", recording.date == null ? null
            : dateTimeString(recording.date));
      values.put("user_name", recording.userName);
      values.put("user_number", recording.userNumber);
      values.put("user_email", recording.userEmail);
      
      database.getWritableDatabase().update(table, values,
            "_id = " + recording.id, null);
   }
   
   @Override
   public Recording get(long id) {
      Cursor cursor = database.getReadableDatabase().rawQuery(
            "SELECT * FROM " + table + " WHERE _id = " + id + " LIMIT 1",
            new String[] {});
      if (cursor.getCount() > 0) {
         int columnIndex = cursor.getColumnIndexOrThrow("_id");
         cursor.moveToFirst();
         Recording recording = new Recording(cursor.getLong(columnIndex));
         
         recording.name = cursor
               .getString(cursor.getColumnIndexOrThrow("name"));
         recording.description = cursor.getString(cursor
               .getColumnIndexOrThrow("description"));
         
         Location loc = new Location("RPSRrec");
         loc.setLatitude(cursor.getDouble(cursor
               .getColumnIndexOrThrow("latitude")));
         loc.setLongitude(cursor.getDouble(cursor
               .getColumnIndexOrThrow("longitude")));
         recording.loc = loc;
         
         recording.date = dateFromString(cursor.getString(cursor
               .getColumnIndexOrThrow("date")));
         recording.userName = cursor.getString(cursor
               .getColumnIndexOrThrow("user_name"));
         recording.userEmail = cursor.getString(cursor
               .getColumnIndexOrThrow("user_email"));
         recording.userNumber = cursor.getString(cursor
               .getColumnIndexOrThrow("user_number"));
         
         return recording;
      }
      else {
         return null;
      }
   }
   
   @Override
   public Recording createNew() {
      long id = database.getWritableDatabase().insert(table, "name",
            new ContentValues());
      return get(id);
   }
   
   @Override
   public void delete(long id) {
      database.getWritableDatabase().delete(table, "_id = " + id, null);
   }
}
