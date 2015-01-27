package uk.ac.aber.cs221.util;

import uk.ac.aber.cs221.test.R;
import uk.ac.aber.cs221.test.RecordingActivity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class RecordingActivityCursorListAdapter extends CursorAdapter {
   
   public RecordingActivityCursorListAdapter(Context context, Cursor c,
         boolean autoRequery) {
      super(context, c, autoRequery);
   }
   
   @Override
   public void bindView(final View view, final Context context, Cursor cursor) {
      TextView name = (TextView) view.findViewById(R.id.rec_sp_Name);
      TextView timeSeen = (TextView) view.findViewById(R.id.rec_sp_TimeSeen);
      ImageView image1 = (ImageView) view.findViewById(R.id.imageView1);
      ImageView image2 = (ImageView) view.findViewById(R.id.imageView2);
      
      String imageFile1Name = cursor.getString(cursor.getColumnIndex("image_1"));
      String imageFile2Name = cursor.getString(cursor.getColumnIndex("image_2"));
      
      Bitmap bitmap1 = BitmapFactory.decodeFile(imageFile1Name);
      Bitmap bitmap2 = BitmapFactory.decodeFile(imageFile2Name);
      
      name.setText(cursor.getString(cursor.getColumnIndex("name")));
      timeSeen.setText(cursor.getString(cursor.getColumnIndex("date")));
      image1.setImageBitmap(bitmap1);
      image2.setImageBitmap(bitmap2);
      // TODO: load image, resize, assign to image1 here
      
      view.setTag((Long) cursor.getLong(cursor.getColumnIndexOrThrow("_id")));
      view.setOnClickListener(new OnClickListener() {
         @Override
         public void onClick(View v) {
            ((RecordingActivity) context).rowSelectCallback(view);
         }
      });
   }
   
   @Override
   public View newView(Context context, Cursor cursor, ViewGroup parent) {
      return LayoutInflater.from(context).inflate(R.layout.rec_species, parent,
            false);
   }
}