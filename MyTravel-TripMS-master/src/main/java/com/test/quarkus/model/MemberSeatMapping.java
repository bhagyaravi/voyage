package com.test.quarkus.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class MemberSeatMapping extends PanacheEntityBase {
    @Id
    @GeneratedValue
    UUID id;

    UUID groupMemberId;
    UUID flightId;//change to flightBookingId
    int seatNumber;

    public UUID getMemberSeatMappingId(){
        return this.id;
    }

    public UUID getGroupMemberId() {
        return this.groupMemberId;
    }

    public void setGroupMemberId(UUID groupMemberId) {
        this.groupMemberId = groupMemberId;
    }

    public UUID getFlightId() {
        return this.flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public int getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

}