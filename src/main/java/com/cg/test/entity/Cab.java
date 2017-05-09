package com.cg.test.entity;

import com.cg.test.common.CarModel;

/**
 * A cab will have driver, car type and location. 
 */
public class Cab {

	private int id;

	// A model can be used in multiple ride types. Typically a high end model can be used in lower far ride types.
	private CarModel model;

	private String driver;

	private Location current;
	
	private boolean onTrip = false;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarModel getModel() {
		return model;
	}

	public void setModel(CarModel model) {
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

	public void setCurrent(Location currentLocation) {
		this.current = currentLocation;
	}
	
	public boolean isOnTrip() {
		return onTrip;
	}

	public void setOnTrip(boolean onTrip) {
		this.onTrip = onTrip;
	}


	public static boolean update(Cab to, Cab from) {
		if (from != null) {		
			// Update Location
			if(from.getCurrent()!=null){
				to.setCurrent(from.getCurrent());
				return true;
			}
		}
		return false;
	}
}
