package uk.ac.aber.cs221.storage;

import java.util.Date;
import android.location.Location;

/**
 * @author was4
 * 
 *         Simple class to represent an individual recording. Instances are used
 *         internally in the activities to keep track of relevant attributes
 */
public class Recording {
   public final long id;
   public String name, description;
   public Location loc;
   public Date date;
   public String userName, userEmail, userNumber;
   
   // package-private constructor to enforce use of RecordingStorage
   Recording(long id) {
      this.id = id;
   }
}
