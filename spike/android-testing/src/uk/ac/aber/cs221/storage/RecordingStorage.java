package uk.ac.aber.cs221.storage;

import java.util.List;

import android.content.Context;

public class RecordingStorage extends Storage<Recording> {
   private static RecordingStorage instance;
   private static final String table = "recordings";
   private final Context context;
   
   RecordingStorage(Context context) {
      instance = this;
      this.context = context;
      // new Storage.DatabaseHelper()
   }
   
   public static RecordingStorage getInstance() {
      return instance;
   }
   
   @Override
   public List<Recording> getList() {
      // TODO Auto-generated method stub
      return null;
   }
   
   @Override
   public void store(Recording t) {
      // TODO Auto-generated method stub
      
   }
   
   @Override
   public Recording get(int id) {
      // TODO Auto-generated method stub
      return null;
   }
   
}
