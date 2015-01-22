package uk.ac.aber.cs221.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;

public class Util {
   /**
    * Set up the {@link android.app.ActionBar}, if the API is available.
    */
   @TargetApi(Build.VERSION_CODES.HONEYCOMB)
   public static void setupActionBar(Activity activity) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
         // Show the Up button in the action bar.
         activity.getActionBar().setDisplayHomeAsUpEnabled(true);
      }
   }
}
