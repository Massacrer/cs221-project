package uk.ac.aber.cs221.storage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Abstract superclass of the Storage classes, defining abstract methods and
 * some static helper methods and classes to enable easy access to the
 * Android-provided sqlite3 implementation
 * 
 * @author was4
 * 
 * @param <T>
 *           The type of object to store. Concrete implementations store
 *           {@link Recording}s and {@link Species}'
 */
public abstract class Storage<T> {
   static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss.SSS";
   
   public abstract void store(T t);
   
   public abstract T get(long id);
   
   public abstract T createNew();
   
   public abstract void delete(long id);
   
   // apparently a method can't be both abstract and static in java. Shame.
   // Declaring them manually in concrete implementations it is then - was4
   // public static abstract Storage<T> getInstance(Context context);
   
   /**
    * Utility method for converting a Java Date to a String suitable for
    * database storage
    * 
    * @param date
    *           The {@link Date} to store
    * @return The string representation of the date, created using
    *         {@link SimpleDateFormat}
    */
   public static String dateTimeString(Date date) {
      if (date == null) {
         return null;
      }
      return new SimpleDateFormat(dateTimeFormat, Locale.UK).format(date);
   }
   
   /**
    * Utility method for converting a Date from it's String format as stored in
    * the database, back to a Java Date object
    * 
    * @param date
    *           The String to read
    * @return The Date that is represented by the String, or null if there is a
    *         formatting error
    */
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
   
   /**
    * Class to help with creating and opening the database. See the Android
    * sqlite3 and Storage documentation for more information
    * 
    * @author was4
    */
   static class DatabaseHelper extends SQLiteOpenHelper {
      private static final String nn = "";// "NOT NULL"; // no longer used -
                                          // fields are initialized to null //
                                          // EN-US spelling used here for
                                          // project management reasons
      
      // The following Strings define SQL fragments used in the following
      // methods
      private static final String idColumn = "_id INTEGER PRIMARY KEY AUTOINCREMENT";
      private static final String recordings_create = "name TEXT " + nn
            + ", description TEXT " + nn
            + ", latitude REAL, longitude REAL, date TEXT " + nn
            + ", user_name TEXT " + nn + ", user_email TEXT " + nn
            + ", user_number TEXT " + nn;
      private static final String species_create = "name TEXT " + nn
            + ", comment TEXT " + nn + ", date TEXT " + nn
            + ", recording INTEGER " + nn + ", abundance INTEGER " + nn
            + ", image_1 TEXT " + nn + ", image_2 TEXT " + nn
            + ", latitude REAL, longitude REAL";
      
      /**
       * @param context
       *           The Context to access the database of
       */
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
