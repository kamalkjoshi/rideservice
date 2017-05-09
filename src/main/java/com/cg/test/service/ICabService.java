package com.cg.test.service;

import java.util.Collection;

import com.cg.test.common.RideType;
import com.cg.test.entity.Cab;
import com.cg.test.entity.Location;
import com.cg.test.vo.CabInfo;

public interface ICabService {

	public int register(Cab cab);

	public void updateCurrentLocation(int id, Location location);
	
	public void updateTripStatus(int id, boolean onTrip);
	
	public Cab find(int id);
	
	public Collection<Cab> findWaitingCabs();
	
	public Collection<Cab> findAll();
	
	public Collection<CabInfo> findAvailableCabs(RideType type,Location source, double radius, int limit);

}
