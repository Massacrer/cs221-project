package uk.ac.aber.cs221.storage;

import java.util.Date;

import android.location.Location;

/**
 * @author was4
 * 
 *         Simple class to represent an individual species. Instances are used
 *         internally in the activities to keep track of relevant attributes
 */
public class Species {
   public long id, recordingId;
   public int abundance;
   public Date date;
   public String name, comment, imageFile1, imageFile2;
   public Location loc;
   
   // package-private constructor to enforce use of SpeciesStorage
   Species(long id) {
      this.id = id;
   };
}
