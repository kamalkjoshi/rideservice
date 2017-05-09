package com.cg.test.common;

import static com.cg.test.common.RideType.GO;
import static com.cg.test.common.RideType.UBERX;
import static com.cg.test.common.RideType.UBERXL;

/**
 * Base repository of available care models.
 */
public enum CarModel {

	//@formatter:off
	SWIFT("maruti-swift", GO), 
	
	LIVA("toyota-liva", GO),
	
	FIGO("ford-figo", GO),
	
	DEZIRE("maruti-dezire", UBERX),
	
	AMAZE("honda-amaze", UBERX),
	
	ETIOS("toyota-etios", UBERX),
	
	XUV("mahindra-xuv", UBERXL),
	
	INNOVA("toyota-innova", UBERXL);
	
	//@formatter:on

	private String model;

	private RideType rideType;

	private CarModel(String model, RideType rideType) {
		this.model = model;
		this.rideType = rideType;
	}

	public String getModel() {
		return this.model;
	}

	public static CarModel from(String value){
		CarModel[] models = CarModel.values();
		for(CarModel model : models){
			if(model.getModel().equals(value)){
				return model;
			}
		}
		return  null;
	}
	
	public boolean canSupport(RideType requested) {
		if (GO == requested) {
			return rideType == GO || rideType == UBERX || rideType == UBERXL;
		} else if (UBERX == requested) {
			return rideType == UBERX || rideType == UBERXL;
		} else if (UBERXL == requested) {
			return rideType == UBERXL;
		}
		return false;
	}

}
