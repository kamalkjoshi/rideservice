package com.cg.test.vo;

import com.cg.test.common.RideUtils;
import com.cg.test.entity.Cab;
import com.cg.test.entity.Location;

public class CabInfo {

	private String model;

	private String driver;

	private Location current;

	private boolean onTrip = false;

	private double distance;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public Location getCurrent() {
		return current;
	}

	public void setCurrent(Location current) {
		this.current = current;
	}

	public boolean isOnTrip() {
		return onTrip;
	}

	public void setOnTrip(boolean onTrip) {
		this.onTrip = onTrip;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public static CabInfo from(Cab cab, Location source){
		CabInfo cabInfo = new CabInfo();
		cabInfo.setModel(cab.getModel().name());
		cabInfo.setDriver(cab.getDriver());
		cabInfo.setCurrent(cab.getCurrent());
		//This should be waiting.
		cabInfo.setOnTrip(cab.isOnTrip());
		cabInfo.setDistance(RideUtils.distanceBetween(source, cabInfo.getCurrent()));
		return cabInfo;
	}

}
