package uk.ac.aber.cs221.storage;

import java.util.Date;

import android.location.Location;

public class Species {
   public long id, recordingId;
   public int abundance;
   public Date date;
   public String name, comment, imageFile1, imageFile2;
   public Location loc;
   
   // package-private constructor
   Species(long id) {
      this.id = id;
   };
}
