package uk.ac.aber.cs221.storage;

import java.util.Date;
import android.location.Location;

public class Recording {
   public final long id;
   public String name, description;
   public Location loc;
   public Date date;
   public String userName, userEmail, userNumber;
   
   // package-private constructor
   Recording(long id) {
      this.id = id;
   }
}
