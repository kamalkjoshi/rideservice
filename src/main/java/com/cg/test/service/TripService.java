package com.cg.test.service;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.test.common.RideType;
import com.cg.test.common.RideUtils;
import com.cg.test.entity.Cab;
import com.cg.test.entity.Location;
import com.cg.test.entity.Trip;
import com.cg.test.entity.Trip.Status;

@Service("tripservice")
public class TripService implements ITripService {

	private AtomicInteger sequence = new AtomicInteger();

	@Autowired
	private ICabService cabService;

	@Override
	public int startTrip(Trip trip) {
		Objects.requireNonNull(trip, "Trip instance can not be empty");
		trip.setId(sequence.incrementAndGet());
		DataStore.addTrip(trip);
		updateCabStatus(trip);
		return trip.getId();
	}

	// This can be implemented as event based mechanism as well.
	void updateCabStatus(Trip trip) {
		// Update cab's on trip status as well.
		Cab cab = cabService.find(trip.getCabId());
		cab.setOnTrip(trip.getStatus() == Status.STARTED);
		if (trip.getStatus() == Status.COMPLETED) {
			cab.setCurrent(trip.getDestination());
		}
		DataStore.updateCab(cab);
	}

	static double calculateFare(Trip trip) {
		RideType type = trip.getRideType();
		double distance = trip.getDistance();
		return distance * type.getRatePerKM();
	}

	@Override
	public void completeTrip(int id, Location destination) {
		Trip trip = DataStore.getTripById(id);
		// Set trip fields. cancellation charges
		trip.setStatus(Status.COMPLETED);
		if (Location.isValid(destination)) {
			trip.setDestination(destination);
		}
		trip.setDistance(RideUtils.distanceBetween(trip.getSource(),
				trip.getDestination()));
		trip.setFare(calculateFare(trip));
		DataStore.updateTrip(trip);
		updateCabStatus(trip);
	}

	@Override
	public void cancelTrip(int id) {
		Trip trip = DataStore.getTripById(id);
		// Set trip fields. cancellation charges
		trip.setStatus(Status.CANCELLED);
		DataStore.updateTrip(trip);
		updateCabStatus(trip);
	}

	@Override
	public Collection<Trip> findAll() {
		return DataStore.listTrips();		
	}

	@Override
	public Collection<Trip> findTripsForRider(int riderId) {
		return DataStore.listTrips(t -> t.getRiderId() == riderId);
	}

	@Override
	public Collection<Trip> findTripsForCab(int cabId) {
		return DataStore.listTrips(t -> t.getCabId() == cabId);
	}
	
}
