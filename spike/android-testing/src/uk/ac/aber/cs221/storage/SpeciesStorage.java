package uk.ac.aber.cs221.storage;

import java.util.List;

import android.content.Context;

public class SpeciesStorage extends Storage<Species> {
   private static SpeciesStorage instance;
   private static final String table = "species";
   private final Context context;
   
   SpeciesStorage(Context context) {
      instance = this;
      this.context = context;
   }
   
   public static SpeciesStorage getInstance() {
      return instance;
   }
   
   @Override
   public List<Species> getList() {
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
