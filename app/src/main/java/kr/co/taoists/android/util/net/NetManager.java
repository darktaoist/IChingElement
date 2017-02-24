package kr.co.taoists.android.util.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

public class NetManager {
	public static boolean enableWifi(Context context){
		try{
	        WifiManager wManager; 
	        wManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	        WifiInfo wInfo = wManager.getConnectionInfo();      
	Log.i("wManager.isWifiEnabled() == true && wInfo.getSSID()=",wManager.isWifiEnabled() +":"+ wInfo.getSSID());
	    	if ( wManager.isWifiEnabled() == true && wInfo.getSSID() != null){   		
	    		return true;
			}else{
		    	return false;
			}	
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean enableNet(Context context){
		try{
			ConnectivityManager netContect = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if(netContect.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected() || 
					netContect.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected())
				return true;
			else
				return false;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}	
}
