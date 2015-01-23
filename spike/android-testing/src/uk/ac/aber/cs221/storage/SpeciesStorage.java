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
   public void store(Species species) {
      // TODO Auto-generated method stub
      
   }
   
   @Override
   public Species get(int id) {
      // TODO Auto-generated method stub
      return null;
   }
}
