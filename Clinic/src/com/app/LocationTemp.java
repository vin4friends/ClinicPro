package com.app;

import java.math.BigInteger;

public class LocationTemp {

	private BigInteger locId;
	private double distance;
	public LocationTemp() {
		// TODO Auto-generated constructor stub
	}

	public LocationTemp(BigInteger locId,double distance) {
		// TODO Auto-generated constructor stub
		this.setLocId(locId);
		this.setDistance(distance);
	}

	

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public void setLocId(BigInteger locId) {
		this.locId = locId;
	}

	public BigInteger getLocId() {
		return locId;
	}

	
}
