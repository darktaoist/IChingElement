package kr.co.taoists.android.util.gps;

import kr.co.taoists.android.util.dto.GpsDTO;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.app.AlertDialog;

public class GPSManager {
	private LocationManager locationManager;	
	private Context context;
	private String provider;
	
	
	private GpsLocationListener listener = null;
	
	public GPSManager(Context act){
		this.context = act;
		loadGps();
	}
	
//	public void testLocation() {
//	
//	}

	public boolean enableGps(){
		try{
//			   ContentResolver res = context.getContentResolver();
//			   boolean gpsProvider=Settings.Secure.isLocationProviderEnabled(res, LocationManager.GPS_PROVIDER);

			locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(provider, 3000, 0, listener);
			
			if(!locationManager.isProviderEnabled(provider) && locationManager.getLastKnownLocation(provider) != null)
				return true;
			return false;
		} catch (Exception e){
			return false;
		}
		
	}
	public void testGps111(){
		   LocationManager locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
		   Location lastLocation = locationManager.getLastKnownLocation("gps");

		   if (lastLocation == null) {
		    locationManager.requestLocationUpdates("gps", 60000, 1.0f, listener);
		    Log.w("@@SmsVerifier", "lastLocation is null");
		   } else {
			   Log.e("@@LLLLLLL",""+lastLocation.getLatitude());
			   Log.e("@@OOOOOOOOOO",""+lastLocation.getLongitude());
			   Log.w("@@SmsVerifier", "lastLocation is not null :" + lastLocation.toString());
		   }
		
	}

	
	public GpsDTO getGpsDto() {
        
		GpsDTO dto = new GpsDTO();
        Location loc = getLocation();
        
        if( loc == null ) {
			Log.w( this.getClass().getName(), "location is null" );
	        Log.w(this.getClass().getName(), "get Location From GPS Fail !!!!!");

	    	
		} else {
			dto.setLatitude(loc.getLatitude());
			dto.setLongitude(loc.getLongitude());
			dto.setAltitude(loc.getAltitude());
		}
        return dto;
	}
	
	public void testLocation() {
		//loadGps();
        Location loc = getLocation();
        
        if( loc == null ) {
			Log.w( this.getClass().getName(), "location is null" );
	        Log.w(this.getClass().getName(), "get Location From GPS Fail !!!!!");

	    	
		} else {
	        Log.w(this.getClass().getName(), "loc.getLatitude()>"+loc.getLatitude());
	        Log.w(this.getClass().getName(), "loc.getLongitude()>"+loc.getLongitude());
	        Log.w(this.getClass().getName(), "loc.getAltitude();>"+loc.getAltitude());
	        
//	        AlertDialog.Builder adb = new AlertDialog.Builder(context);
//			adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//				}
//			});
//			String ttt = "loc.getLatitude()>"+loc.getLatitude();
//			ttt+="\n"+"loc.getLongitude()>"+loc.getLongitude();
//			ttt+="\n"+"loc.getAltitude();>"+loc.getAltitude();
//			
//	    	adb.setTitle("Info Success");
//	    	adb.setMessage(ttt);
//	    	adb.show();
		}
        
	}
	
	private Location getLocation() {
		
		Location location = locationManager.getLastKnownLocation( provider );
		if ( location == null ) {
			Log.w(this.getClass().getName(), "get Location From GPS Fail !!!!!");
			location = locationManager.getLastKnownLocation( LocationManager.NETWORK_PROVIDER );
		}
		return location;
	}


	
///check....
/*
http://www.google.com/codesearch/p?hl=ko#ohAXAHj6Njg/src/com/android/settings/widget/SettingsAppWidgetProvider.java&q=android%20setting%20gps&d=4
 * 
private void toggleGps(Context context) {
        ContentResolver resolver = context.getContentResolver();
        boolean enabled = getGpsState(context);
        Settings.Secure.setLocationProviderEnabled(resolver, LocationManager.GPS_PROVIDER,
                !enabled);
    }

 * 
 <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"></uses-permission>
 <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"></uses-permission>
 <uses-permission android:name="android.permission.INSTALL_LOCATION_PROVIDER"></uses-permission>
 <uses-permission android:name="android.permission.ACCESS_LOCATION_EXTRA_COMMANDS"></uses-permission>
 <uses-permission android:name="android.permission.WRITE_SECURE_SETTINGS"></uses-permission>
 <uses-permission android:name="android.permission.WRITE_SETTINGS"></uses-permission>
 * */	
    /**
     * Gets the state of GPS location.
     *
     * @param context
     * @return true if enabled.
     */
    private static boolean getGpsState(Context context) {
        ContentResolver resolver = context.getContentResolver();
        return false;//Settings.Secure.isLocationProviderEnabled(resolver, LocationManager.GPS_PROVIDER);
    }

   /**
     * Toggles the state of GPS.
     *
     * @param context
     */
    private void toggleGps(Context context) {
        ContentResolver resolver = context.getContentResolver();
        boolean enabled = getGpsState(context);
     // Settings.Secure.setLocationProviderEnabled(resolver, LocationManager.GPS_PROVIDER,!enabled);
    }	
	
	 public void loadGps() {
	    	locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	    	
	    	Criteria criteria = new Criteria();
	    	criteria.setAccuracy(Criteria.ACCURACY_FINE);		// 정확도
	    	criteria.setPowerRequirement(Criteria.POWER_LOW);	// 전원 소비량
	    	criteria.setAltitudeRequired(false);				// 고도, 높이 값을 얻어 올지를 결정
	    	criteria.setBearingRequired(false);
	    	criteria.setSpeedRequired(false);					//속도
	    	criteria.setCostAllowed(true);						//위치 정보를 얻어 오는데 들어가는 금전적 비용
	    	try{
	    		Log.e(this.getClass().getName() , "Starting to get provider" );
	    		provider = locationManager.getBestProvider(criteria, true);
	    		Log.e(this.getClass().getName() , "Get provider Success" );
	    		listener = new GpsLocationListener();
//		    	locationManager.requestLocationUpdates(provider, 1000, 5, listener);
		    	locationManager.requestLocationUpdates(provider, 1000, 0, listener);
		    	
	    	} catch (Exception e) {
	    		Log.w(this.getClass().getName() , "Get provider Fail" + e.getMessage());
	    		e.printStackTrace();
	    	}
	    }
	 
	    private class GpsLocationListener implements LocationListener {

	    	public void onLocationChanged(Location location) {
	    		Log.w(this.getClass().getName() , "onLocationChanged" );
			}

			public void onProviderDisabled(String provider) {
				Log.w(this.getClass().getName(), "onProviderDisabled" );
			}

			public void onProviderEnabled(String provider) {
				Log.w(this.getClass().getName(), "onProviderEnabled" );
			}

			public void onStatusChanged(String provider, int status, Bundle extras) {
				Log.w(this.getClass().getName(), "onStatusChanged" );
			}    	
	    }
}
