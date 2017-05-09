package com.cg.test.entity;

/*
 * Rider details
 */
public class Rider {

	private int id;

	private String name;

	private Location current;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getCurrent() {
		return current;
	}

	public void setCurrent(Location current) {
		this.current = current;
	}

	// List of past trips This can be loaded lazily from Trip data
	// List of last locations can also be maintained or derived from past trips.

}
