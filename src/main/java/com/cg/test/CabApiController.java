package com.cg.test;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.test.common.RideType;
import com.cg.test.entity.Cab;
import com.cg.test.entity.Location;
import com.cg.test.exception.InvalidRequestException;
import com.cg.test.exception.NoCabAvailableException;
import com.cg.test.service.ICabService;
import com.cg.test.vo.CabInfo;

@RestController
@RequestMapping("/api/cabs")
public class CabApiController {

	@Autowired
	private ICabService cabService;

	/**
	 * Find drivers for a given location
	 * 
	 * @param latitude
	 *            - mandatory parameter
	 * @param longitude
	 *            - mandatory parameter
	 * @param radius
	 *            in kilometers,optional parameter
	 * @param limit
	 *            no. of results,optional parameter
	 */
	@RequestMapping(value = "/nearby", method = RequestMethod.POST)
	public Collection<CabInfo> nearBy(@RequestParam("lat") double latitude,
			@RequestParam("long") double longitude,
			@RequestParam("type") RideType type,
			@RequestParam(name = "radius", defaultValue = "5") double radius,
			@RequestParam(name = "limit", defaultValue = "10") int limit) {

		// This can also utilize user specific context.
		Location source = Location.from(latitude, longitude);
		Collection<CabInfo> waitingCabs = cabService.findAvailableCabs(type,
				source, radius, limit);
		if (waitingCabs.isEmpty()) {
			throw new NoCabAvailableException("All cabs are busy");
		}
		Collection<CabInfo> cabs = waitingCabs.stream()
				.sorted(Comparator.comparing(CabInfo::getDistance))
				.collect(Collectors.toList());
		// Find a relevant match.
		return cabs;
	}

	/**
	 * API for cab location update
	 * 
	 * @param id
	 *            - cab id
	 * @param latitude
	 * @param longitude
	 * @param accuracy
	 *            - Minimum accuracy required for UPDATE
	 * @return
	 */
	@RequestMapping(value = "/{id}/location", method = RequestMethod.POST)
	public String location(@PathVariable("id") int id,
			@RequestParam("lat") double latitude,
			@RequestParam("long") double longitude,
			@RequestParam(name = "accuracy") double accuracy) {
		if (!isAccurateEnough(accuracy)) {
			return "Not accurate enough to update location";
		}
		if (!Location.isValid(latitude, longitude)) {
			throw new InvalidRequestException("Invalid lat/long values");
		}
		cabService.updateCurrentLocation(id,
				Location.from(latitude, longitude));
		return "Cab location updated Successfully.";
	}

	static final double ACCURACY_THRESOLD = 0.5d;

	private static boolean isAccurateEnough(double accuracy) {
		return accuracy <= 1.0d && accuracy >= ACCURACY_THRESOLD;
	}

	/**
	 * Cab registeration API
	 * 
	 * @param cab
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int registerCab(@RequestBody Cab cab) {
		return cabService.register(cab);
	}

	/*
	 * List of available cabs. Primarily used for debugging purpose.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Collection<Cab> listCabs() {
		return cabService.findAll();
	}
}
