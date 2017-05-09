package com.cg.test.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

import com.cg.test.entity.Cab;
import com.cg.test.entity.Rider;
import com.cg.test.entity.Trip;

/**
 * In memory data store for ride information
 *
 */
public final class DataStore {

	private static Map<Integer, Cab> cabs = new ConcurrentHashMap<>();

	private static Map<Integer, Rider> riders = new ConcurrentHashMap<>();

	private static Map<Integer, Trip> trips = new ConcurrentHashMap<>();

	public static Cab findCabById(int id) {
		return cabs.get(id);
	}

	public static void addCab(Cab cab) {
		cabs.put(cab.getId(), cab);
	}

	// Update fields e.g current lat,long or car model
	public static void updateCab(Cab cab) {
		// Update validated cab object.
		cabs.put(cab.getId(), cab);
	}

	public static Collection<Cab> listCabs() {
		// Return a copy
		return cabs.values().stream().collect(toList());
	}

	public static Collection<Cab> listCabs(Predicate<Cab> predicate,
			int limit) {
		// Return a copy
		return cabs.values().stream().filter(predicate).limit(limit)
				.collect(toList());
	}

	public static Rider findRiderById(int id) {
		return riders.get(id);
	}

	public static void addRider(Rider rider) {
		riders.put(rider.getId(), rider);
	}

	public static Collection<Rider> listRiders() {
		// Return a copy
		return riders.values().stream().collect(toList());
	}

	public static Trip getTripById(int id) {
		return trips.get(id);
	}

	public static void addTrip(Trip trip) {
		trips.put(trip.getId(), trip);
	}

	public static void updateTrip(Trip trip) {
		trips.put(trip.getId(), trip);
	}

	public static Collection<Trip> listTrips() {
		// Return a copy
		return trips.values().stream().collect(toList());
	}

	public static Collection<Trip> listTrips(Predicate<Trip> predicate) {
		// Return a copy
		return trips.values().stream().filter(predicate).collect(toList());
	}
}
