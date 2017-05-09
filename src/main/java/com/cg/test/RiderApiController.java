package com.cg.test;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.test.entity.Rider;
import com.cg.test.service.IRiderService;

@RestController
@RequestMapping("/api/riders")
public class RiderApiController {

	@Autowired
	private IRiderService riderService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public int registerRider(@RequestBody Rider rider) {
		return riderService.register(rider);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public Collection<Rider> listRiders() {
		return riderService.findAll();
	}

}
