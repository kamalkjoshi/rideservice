package com.cg.test.service;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.test.entity.Rider;
import com.cg.test.entity.Trip;

@Service("riderService")
public class RiderService implements IRiderService {
	
	@Autowired
	private ITripService tripService;

	private AtomicInteger sequence = new AtomicInteger();
	
	@Override
	public int register(Rider rider) {
		Objects.requireNonNull(rider, "Rider instance can not be empty");
		rider.setId(sequence.incrementAndGet());
		DataStore.addRider(rider);
		return rider.getId();
	}
	
	public Collection<Trip> getTripsForRider(int id){
		return tripService.findTripsForRider(id);
	}

	@Override
	public Collection<Rider> findAll() {
		return DataStore.listRiders();
	}

}
