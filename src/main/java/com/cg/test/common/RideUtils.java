package com.cg.test.common;

import com.cg.test.entity.Location;

public class RideUtils {

	public static double distanceBetween(Location loc1, Location loc2) {
		return haversineDistanceInKm(loc1.getLatitude(), loc1.getLongitude(),
				loc2.getLatitude(), loc2.getLongitude());
	}

	/**
	 **
	 * http://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
	 *
	 * http://www.movable-type.co.uk/scripts/latlong.html
	 */
	public static double haversineDistanceInKm(double latitude1,
			double longitude1, double latitude2, double longitude2) {
		final int R = 6371;
		Double latDistance = degreeToRadian(latitude2 - latitude1);
		Double longDistance = degreeToRadian(longitude2 - longitude1);
		Double A = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(degreeToRadian(latitude1))
						* Math.cos(degreeToRadian(latitude2))
						* Math.sin(longDistance / 2)
						* Math.sin(longDistance / 2);
		Double C = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1 - A));
		Double distance = R * C;
		return distance;
	}

	private static Double degreeToRadian(Double value) {
		return value * Math.PI / 180;
	}

}
