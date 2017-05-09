package com.cg.test.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cg.test.common.RideType;
import com.cg.test.entity.Cab;
import com.cg.test.entity.Location;
import com.cg.test.exception.CabNotFoundException;
import com.cg.test.vo.CabInfo;

@Service("driverservice")
public class CabService implements ICabService {

	private AtomicInteger sequence = new AtomicInteger();

	@Override
	public int register(Cab cab) {
		Objects.requireNonNull(cab, "Cab instance can not be empty");
		cab.setId(sequence.incrementAndGet());
		DataStore.addCab(cab);
		return cab.getId();
	}

	@Override
	public void updateCurrentLocation(int id, Location location) {
		Cab cab = find(id);
		cab.setCurrent(location);
		DataStore.updateCab(cab);
	}

	@Override
	public Cab find(int id) {
		Cab cab = DataStore.findCabById(id);
		if (null == cab) {
			throw new CabNotFoundException("No cab is found with id " + id);
		}
		return cab;
	}

	public void updateTripStatus(int id, boolean onTrip) {
		Cab cab = find(id);
		cab.setOnTrip(onTrip);
		DataStore.updateCab(cab);
	}

	@Override
	public Collection<Cab> findAll() {
		return DataStore.listCabs();
	}

	@Override
	public Collection<Cab> findWaitingCabs() {
		return DataStore.listCabs((cab)->!cab.isOnTrip(),10);
	}

	@Override
	public Collection<CabInfo> findAvailableCabs(RideType type, Location source,
			double radius, int limit) {
		// Check for available cabs which can support a requested ride type and
		// within a threshold distance
		Predicate<Cab> predicate = (cab) -> {
			return !cab.isOnTrip() && cab.getModel().canSupport(type);
		};
		List<CabInfo> cabs = DataStore.listCabs(predicate, limit).stream()
				.map(c -> CabInfo.from(c, source))
				.filter(ci -> ci.getDistance() <= radius)
				.collect(Collectors.toList());
		return cabs;
	}

}
