package com.cg.test.entity;

import com.cg.test.common.RideType;

public class Trip {

	public static enum Status {
		STARTED, COMPLETED, CANCELLED
	}
	private int id;

	private int riderId;

	private int cabId;

	private Location source;

	private Location destination;

	private RideType rideType;
	
	private Status status = Status.STARTED;

	private double fare;

	private double distance;
	
	private double duration;

	// private String customerFeedback;
	//
	// private String driverFeedback;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRiderId() {
		return riderId;
	}

	public void setRiderId(int riderId) {
		this.riderId = riderId;
	}

	public int getCabId() {
		return cabId;
	}

	public void setCabId(int cabId) {
		this.cabId = cabId;
	}

	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}

	public Location getDestination() {
		return destination;
	}

	public void setDestination(Location destination) {
		this.destination = destination;
	}

	public RideType getRideType() {
		return rideType;
	}

	public void setRideType(RideType rideType) {
		this.rideType = rideType;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

}
