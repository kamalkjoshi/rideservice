package com.cg.test;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.test.common.RideType;
import com.cg.test.entity.Location;
import com.cg.test.entity.Trip;
import com.cg.test.exception.InvalidRequestException;
import com.cg.test.service.ITripService;

@RestController
@RequestMapping("/api/trips")
public class TripApiController {

	@Autowired
	private ITripService tripService;

	// TODO- Need to use RequestBody for handling too many params
	@RequestMapping(value = "/start", method = RequestMethod.POST)
	public int startTrip(@RequestParam("cabId") int cabId,
			@RequestParam(name = "riderId") int riderId,
			@RequestParam("fromLat") double fromLat,
			@RequestParam("fromLong") double fromLong,
			@RequestParam("type") RideType type,
			@RequestParam(name = "toLat", defaultValue="-100.0") double toLat,
			@RequestParam(name = "toLong",defaultValue="-200.0") double toLong) {

		Trip trip = new Trip();
		//TODO: validations  can  be added to check if cab/rider is already part of an active trip
		trip.setCabId(cabId);
		trip.setRiderId(riderId);
		trip.setRideType(type);
		Location from = Location.from(fromLat, fromLong);
		if (from == null) {
			throw new InvalidRequestException("Invalid lat/long values");
		}
		trip.setSource(from);
		trip.setDestination(Location.from(toLat, toLong));

		int tripId = tripService.startTrip(trip);

		return tripId;
	}

	@RequestMapping(value = "/{id}/complete", method = RequestMethod.POST)
	public void location(@PathVariable("id") int id,
			@RequestParam(name = "toLat", required = false) double toLat,
			@RequestParam(name = "toLong", required = false) double toLong) {
		Location destination = Location.from(toLat, toLong);
		tripService.completeTrip(id, destination);
	}

	@RequestMapping(value = "/{id}/cancel", method = RequestMethod.POST)
	public void cancel(@PathVariable("id") int id) {
		tripService.cancelTrip(id);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Collection<Trip> listTrips() {
		return tripService.findAll();
	}
	
}
