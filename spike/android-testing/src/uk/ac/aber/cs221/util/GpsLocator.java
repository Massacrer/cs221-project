package uk.ac.aber.cs221.util;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

public class GpsLocator extends Service implements LocationListener  {
	
	private final Context mContext;
	
	boolean isGPSEnabled = false;
	
	boolean canGetLocation = false;
	
	Location location;
	double longitude;
	double latitude;
	
	private static final long minDistanceChange = 10; //10 meters
	private static final long minTimeUpdate = 60000; //1 minute
	
	protected LocationManager locationManager;
	
	public GpsLocator(Context context) {
		this.mContext = context;
		getLocation();
	}
	
	public Location getLocation() {
		try {
			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);
			
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
			
			if (!isGPSEnabled) {
				//no network provider for GPS
			} else{
				this.canGetLocation = true;
				
				if(isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								minTimeUpdate,
								minDistanceChange,
								this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return location;
	}
	
	public boolean canGetLocation() {
		return this.canGetLocation;
	}
	
	public void showSettingsAlert(){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
		
		alertDialog.setTitle("GPS settings");
		alertDialog.setMessage("GPS is tuned off. Please turn it on in settings");
		
		//when settings is pressed
		alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				mContext.startActivity(intent);
			}
		});
		
		//when cancel is pressed
		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		
		alertDialog.show();
	}
	
	public void stopGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GpsLocator.this);
		}
	}
	
	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
		}
		return latitude;
	}
	
	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
		}
		return longitude;
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
