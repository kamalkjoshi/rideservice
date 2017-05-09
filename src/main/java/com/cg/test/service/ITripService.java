package com.cg.test.service;

import java.util.Collection;

import com.cg.test.entity.Location;
import com.cg.test.entity.Trip;

public interface ITripService {

	public int startTrip(Trip trip);
	
	public void completeTrip(int id, Location destination);
	
	public void cancelTrip(int id);

	public Collection<Trip> findAll();
	
	public Collection<Trip> findTripsForRider(int riderId);
	
	public Collection<Trip> findTripsForCab(int cabId);
}
