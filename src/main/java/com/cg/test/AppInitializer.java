package com.cg.test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cg.test.entity.Cab;
import com.cg.test.entity.Rider;
import com.cg.test.service.ICabService;
import com.cg.test.service.IRiderService;

/**
 * App initialization code for seeding data for cabs and riders.
 */
@Component
public class AppInitializer implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

	@Value("classpath:riders.json")
	private Resource riderJson;
	
	@Value("classpath:cabs.json")
	private Resource cabJson;
	
	@Autowired
	private IRiderService riderService;
	
	@Autowired
	private ICabService cabService;

	@Override
	public void run(String... args) throws Exception {
		logger.info("=============== Starting Ride Application ===============");
		
		logger.info("Loading riders data.");
		String data = new String(
				Files.readAllBytes(Paths.get(riderJson.getURI())),
				Charset.forName("UTF-8"));
		Rider[] riders = JsonUtils.readJson(data, Rider[].class);
		// Collection<Rider> riders =
		// JsonUtils.readAsCollection(data,Rider.class,Collection.class) ;
		Arrays.stream(riders).forEach((rider) -> riderService.register(rider));
		logger.info("Loaded {} riders",riders.length);
		
		logger.info("Loading cabs data.");
		data = new String(
				Files.readAllBytes(Paths.get(cabJson.getURI())),
				Charset.forName("UTF-8"));
		Cab[] cabs = JsonUtils.readJson(data, Cab[].class);

		Arrays.stream(cabs).forEach((cab) -> cabService.register(cab));
		logger.info("Loaded {} cabs",cabs.length);
		
		logger.info("=============== Startup Completed ===============");
	}

}
