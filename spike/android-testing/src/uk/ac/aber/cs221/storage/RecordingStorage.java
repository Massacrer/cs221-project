package uk.ac.aber.cs221.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

public class RecordingStorage extends Storage<Recording> {
   private static RecordingStorage instance;
   public static final String table = "recordings";
   private Storage.DatabaseHelper database;
   
   RecordingStorage(Context context) {
      instance = this;
      database = new Storage.DatabaseHelper(context);
      // TODO:DEBUG REMOVE THIS CALL FOR PRODUCTION
      
   }
   
   public static RecordingStorage getInstance(Context context) {
      if (instance == null) {
         instance = new RecordingStorage(context);
      }
      return instance;
   }
   
   @Override
   public Cursor getCursor() {
      SQLiteDatabase connection = database.getReadableDatabase();
      Cursor cursor = connection.query(table, null, null, null, null, null,
            null);
      // database.close();
      return cursor;
   }
   
   @Override
   public long store(Recording recording) {
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
      
      return database.getWritableDatabase().insert(table, null, values);
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
      ContentValues values = new ContentValues();
      values.put("name", "name");
      long id = database.getWritableDatabase().insert(table, null, values);
      return get(id);
      
      /*
       * Recording recording = new Recording(0); return get(store(recording));
       */
      
   }
}
