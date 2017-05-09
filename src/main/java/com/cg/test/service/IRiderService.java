package com.cg.test.service;

import java.util.Collection;

import com.cg.test.entity.Rider;
import com.cg.test.entity.Trip;

public interface IRiderService {

	public int register(Rider rider);
	
	public Collection<Trip> getTripsForRider(int id);
	
	public Collection<Rider> findAll();
}
