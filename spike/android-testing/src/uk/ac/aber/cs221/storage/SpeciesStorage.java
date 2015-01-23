package uk.ac.aber.cs221.storage;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;

public class SpeciesStorage extends Storage<Species> {
   private static SpeciesStorage instance;
   private static final String table = "species";
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
       Cursor c = connection.query(table, null, null, null, null, null ,null);
       database.close();
       return c;

   }
   
   @Override
   public long store(Species species) {
       ContentValues values = new ContentValues();
       values.put("name", species.name);
       values.put("comment", species.comment);
       values.put("image_1", species.imageFile1);
       values.put("image_2", species.imageFile2);
       values.put("latitude", species.loc.getLatitude());
       values.put("longitude", species.loc.getLongitude());
      // name text not null, comment text not null, image_1 text not null,
      // image_2 text not null, latitude real, longitude real
      return database.getWritableDatabase().insert(table, null, values);

   }
   
   @Override
   public Species get(int id) {
      Cursor c = database.getReadableDatabase().rawQuery(
              "SELECT * FROM " + table + " WHERE ID = " + id + " LIMIT 1;",
              new String[] {});
       if(c.getCount() > 0) {
           Species species = new Species();

           species.id = c.getInt(c.getColumnIndex("id"));

       }
       //toDO
       return null;

   }
   
   @Override
   public Species createNew() {

      return null;
   }
}
