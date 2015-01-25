package uk.ac.aber.cs221.storage;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.content.ContentValues;
import android.content.Context;

public class SpeciesStorage extends Storage<Species> {
   private static SpeciesStorage instance;
   public static final String table = "species";
   private DatabaseHelper database;
   
   SpeciesStorage(Context context) {
      instance = this;
      database = new DatabaseHelper(context);
   }
   
   public static SpeciesStorage getInstance(Context context) {
      
      if (instance == null) {
         instance = new SpeciesStorage(context);
      }
      return instance;
   }
   
   @Override
   public Cursor getCursor() {
      SQLiteDatabase connection = database.getReadableDatabase();
      Cursor c = connection.query(table, null, null, null, null, null, null);
      // database.close();
      return c;
      
   }
   
   @Override
   public long store(Species species) {
      ContentValues values = new ContentValues();
      values.put("name", species.name);
      values.put("comment", species.comment);
      values.put("date", dateTimeString(species.date));
      values.put("abundance", species.abundance);
      values.put("image_1", species.imageFile1);
      values.put("image_2", species.imageFile2);
      values.put("latitude", species.loc.getLatitude());
      values.put("longitude", species.loc.getLongitude());
      // name text not null, comment text not null, image_1 text not null,
      // image_2 text not null, latitude real, longitude real
      return database.getWritableDatabase().insert(table, null, values);
      
   }
   
   @Override
   public Species get(long id) {
      Cursor cursor = database.getReadableDatabase().rawQuery(
            "SELECT * FROM " + table + " WHERE _id = " + id + " LIMIT 1;",
            new String[] {});
      if (cursor.getCount() > 0) {
         Species species = new Species(cursor.getLong(cursor
               .getColumnIndexOrThrow("_id")));
         
         species.name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
         species.comment = cursor.getString(cursor
               .getColumnIndexOrThrow("comment"));
         species.date = dateFromString(cursor.getString(cursor
               .getColumnIndexOrThrow("date")));
         species.abundance = cursor.getInt(cursor
               .getColumnIndexOrThrow("abundance"));
         species.imageFile1 = cursor.getString(cursor
               .getColumnIndexOrThrow("imageFile1"));
         species.imageFile2 = cursor.getString(cursor
               .getColumnIndexOrThrow("imageFile2"));
         
         Location loc = new Location("RPSRrec");
         loc.setLatitude(cursor.getLong(cursor
               .getColumnIndexOrThrow("latitude")));
         loc.setLongitude(cursor.getLong(cursor
               .getColumnIndexOrThrow("longitude")));
         species.loc = loc;
         
      }
      return null;
   }
   
   @Override
   public Species createNew() {
      Species species = new Species(0);
      return get(store(species));
   }
   
   public Cursor getByRecordingId(long recordingId) {
      SQLiteDatabase connection = database.getReadableDatabase();
      Cursor cursor = connection.rawQuery("SELECT * FROM " + table
            + " WHERE recording = " + recordingId, new String[] {});
      // connection.close();
      return cursor;
   }
}
