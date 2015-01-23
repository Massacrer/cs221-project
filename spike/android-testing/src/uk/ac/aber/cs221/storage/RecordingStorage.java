package uk.ac.aber.cs221.storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

public class RecordingStorage extends Storage<Recording> {
   private static RecordingStorage instance;
   private static final String table = "recordings";
   private Storage.DatabaseHelper database;
   
   private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
   
   RecordingStorage(Context context) {
      instance = this;
      database = new Storage.DatabaseHelper(context);
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
      String[] columns = { "columns", "here" };
      Cursor cursor = connection.query(table, null, null, null, null, null,
            null);
      database.close();
      return cursor;
      
   }
   
   @Override
   public void store(Recording recording) {
      ContentValues values = new ContentValues();
      values.put("name", recording.name);
      values.put("description", recording.description);
      values.put("latitude", recording.loc.getLatitude());
      values.put("longitude", recording.loc.getLongitude());
      values.put("date", dateTimeString(recording.date));
      values.put("user_name", recording.userName);
      values.put("user_number", recording.userNumber);
      values.put("user_email", recording.userEmail);
      
      database.getWritableDatabase().insert(table, null, values);
   }
   
   private String dateTimeString(Date date) {
      return new SimpleDateFormat(dateTimeFormat, Locale.UK).format(date);
   }
   
   private Date dateFromString(String date) {
      try {
         return new SimpleDateFormat(dateTimeFormat, Locale.UK).parse(date);
      }
      catch (ParseException e) {
         return null;
      }
   }
   
   @Override
   public Recording get(int id) {
      Cursor cursor = database.getReadableDatabase().rawQuery(
            "SELECT * FROM " + table + " WHERE ID = " + id + " LIMIT 1;",
            new String[] {});
      if (cursor.getCount() > 0) {
         Recording recording = new Recording();
         
         recording.id = cursor.getInt(cursor.getColumnIndex("id"));
         recording.name = cursor.getString(cursor.getColumnIndex("name"));
         recording.description = cursor.getString(cursor
               .getColumnIndex("description"));
         
         Location loc = new Location("RPSRrec");
         loc.setLatitude(cursor.getDouble(cursor.getColumnIndex("latitude")));
         loc.setLongitude(cursor.getDouble(cursor.getColumnIndex("longitude")));
         recording.loc = loc;
         
         recording.date = dateFromString(cursor.getString(cursor
               .getColumnIndex("date")));
         
         return recording;
      }
      else {
         return null;
      }
   }
}
