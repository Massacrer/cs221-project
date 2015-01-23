package uk.ac.aber.cs221.storage;

import java.util.Date;
import android.location.Location;

public class Recording {
   private int id = 0;
   private String name, description;
   private Location loc;
   private Date date;
   private String userName, userEmail, userNumber;
   
   // package-private constructor
   Recording() {
   }
}
