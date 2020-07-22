package com.test.quarkus.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class FlightBooking extends PanacheEntityBase {
    @Id
    @GeneratedValue
    UUID id; // this is the flightBookingId
    
    UUID tripId;
    UUID flightId;
    long flightAmount;
    String flightClass;
    String flightBookingStatus;

    public UUID getFlightBookingId(){
        return this.id;
    }

    public UUID getTripId() {
		return this.tripId;
	}

    public void setTripId(UUID tripId) {
		this.tripId = tripId;
	}

    public UUID getFlightId() {
        return this.flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public long getFlightAmount() {
        return this.flightAmount;
    }

    public void setFlightAmount(long flightAmount) {
        this.flightAmount = flightAmount;
    }

    public String getFlightClass() {
        return this.flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }
   public String getFlightBookingStatus() {
        return this.flightBookingStatus;
    }

    public void setFlightBookingStatus(String flightBookingStatus) {
        this.flightBookingStatus = flightBookingStatus;
    }
}
