package uk.ac.aber.cs221.storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class Storage<T> {
   static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
   static final String idColumn = "_id INTEGER PRIMARY KEY AUTOINCREMENT";
   static final String nn = "";// "NOT NULL";
   
   public abstract long store(T t);
   
   public abstract T get(long id);
   
   public abstract T createNew();
   
   public static String dateTimeString(Date date) {
      if (date == null) {
         return null;
      }
      return new SimpleDateFormat(dateTimeFormat, Locale.UK).format(date);
   }
   
   public static Date dateFromString(String date) {
      if (date == null) {
         return null;
      }
      try {
         return new SimpleDateFormat(dateTimeFormat, Locale.UK).parse(date);
      }
      catch (ParseException e) {
         return null;
      }
   }
   
   static class DatabaseHelper extends SQLiteOpenHelper {
      private String recordings_create = "name TEXT " + nn
            + ", description TEXT " + nn
            + ", latitude REAL, longitude REAL, date TEXT " + nn
            + ", user_name TEXT " + nn + ", user_email TEXT " + nn
            + ", user_number TEXT " + nn;
      private String species_create = "name TEXT " + nn + ", comment TEXT "
            + nn + ", date TEXT " + nn + ", recording INTEGER " + nn
            + ", abundance INTEGER " + nn + ", image_1 TEXT " + nn
            + ", image_2 TEXT " + nn + ", latitude REAL, longitude REAL";
      
      public DatabaseHelper(Context context) {
         super(context, "database", null, 1);
      }
      
      @Override
      public void onCreate(SQLiteDatabase db) {
         Log.i("RPSRrec", "Creating new database");
         db.execSQL("CREATE TABLE " + RecordingStorage.table + " (" + idColumn
               + ", " + recordings_create + ");");
         db.execSQL("CREATE TABLE " + SpeciesStorage.table + " (" + idColumn
               + ", " + species_create + ");");
      }
      
      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL("DROP TABLE IF EXISTS recordings");
         db.execSQL("DROP TABLE IF EXISTS species");
         onCreate(db);
         // could use ALTER TABLE instead
      }
   }
}
