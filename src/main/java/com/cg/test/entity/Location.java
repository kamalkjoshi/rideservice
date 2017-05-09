package com.cg.test.entity;

public class Location {

	private double latitude;

	private double longitude;

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

// Should add support for a grid like identifier
	/**
	 * Latitude : max/min +90 to -90
	 * Longitude : max/min +180 to -180
	 * @param latitude
	 * @param longitude
	 * @return
	 */
	public static boolean isValid(double latitude, double longitude) {
		if (latitude > 90 || latitude < -90) {
			return false;
		}
		if (longitude > 180 || longitude < -180) {
			return false;
		}
		return true;
	}

	public static boolean isValid(Location location) {
		return location != null
				&& isValid(location.getLatitude(), location.getLatitude());
	}
	
	public static Location from(double latitude, double longitude) {
		if (isValid(latitude, longitude)) {
			Location location = new Location();
			location.setLatitude(latitude);
			location.setLongitude(longitude);
			return location;
		}
		return null;
	}
}
