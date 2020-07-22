package com.test.quarkus.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class GroupMember extends PanacheEntityBase {
    @Id
    @GeneratedValue
    UUID id;

    UUID tripId; 
    String name;
    int age;
    String sex;
    String contact;
    String address;

    public UUID getGroupMemberId() {
        return this.id;
    }

    public UUID getTripId() {
        return this.tripId;
    }

    public void setTripId(UUID tripId) {
        this.tripId = tripId; 
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}