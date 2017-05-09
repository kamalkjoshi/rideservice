package com.cg.test.common;

public enum RideType {

	POOL(6.0), GO(8.0), UBERX(10.0), UBERXL(12.5);

	private double ratePerKM;

	private RideType(double ratePerKM) {
		this.ratePerKM = ratePerKM;
	}

	public double getRatePerKM() {
		return this.ratePerKM;
	}

}
