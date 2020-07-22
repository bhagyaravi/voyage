package com.test.quarkus.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Flight extends PanacheEntityBase {
	@Id
	@GeneratedValue
	UUID id;
	
	String flightBrand;
	Date flightTime;
	String fromCity;
	String toCity;
	int totalSeats;
	int seatsFilled;

	public UUID getId() {
		return this.id;
	}

	public String getFlightBrand() {
		return this.flightBrand;
	}

	public void setFlightBrand(String flightBrand) {
		this.flightBrand = flightBrand;
	}

	public Date getFlightTime() {
		return this.flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	public String getFromCity() {
		return this.fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return this.toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public int getTotalSeats() {
		return this.totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getSeatsFilled() {
		return this.seatsFilled;
	}

	public void setSeatsFilled(int seatsFilled) {
		this.seatsFilled = seatsFilled;
	}

}
