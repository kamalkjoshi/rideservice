package com.cg.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	@RequestMapping(name = "/info", method = RequestMethod.GET)
	public String info() {
		return "Welcome  to ride application!";
	}
}
