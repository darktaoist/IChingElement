package kr.co.taoists.android.util.dto;

import kr.co.taoists.cmnn.dto.BaseDTO;

public class GpsDTO extends BaseDTO{

	private double latitude  = 0d;
	private double longitude = 0d;
	private double altitude  = 0d;
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getAltitude() {
		return altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}

	
	

}
