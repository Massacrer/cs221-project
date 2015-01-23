package uk.ac.aber.cs221.storage;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SpeciesStorage extends Storage<Species> {
   private static SpeciesStorage instance;
   private static final String table = "species";
   private SQLiteDatabase database;
   
   SpeciesStorage() {
      instance = this;
   }
   
   public static SpeciesStorage getInstance() {
      return instance;
   }
   
   @Override
   public Cursor getCursor() {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public long store(Species species) {
      // name text not null, comment text not null, image_1 text not null,
      // image_2 text not null, latitude real, longitude real
      return 0;
   }
   
   @Override
   public Species get(int id) {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public Species createNew() {
      // TODO Auto-generated method stub
      return null;
   }
}
