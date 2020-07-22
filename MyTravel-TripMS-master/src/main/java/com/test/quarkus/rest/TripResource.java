package com.test.quarkus.rest;

import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.quarkus.model.GroupMember;
import com.test.quarkus.model.MemberSeatMapping;
import com.test.quarkus.model.Trip;


@Path("/trip")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripResource {

	@POST
	@Transactional
	@Path("/bookTrip")
	public Response bookTrip(Trip trip) {
		trip.persist();
		return Response.ok(trip).status(201).build();
	}

	@POST
	@Transactional
	@Path("/addGroupMember")
	public Response addGroupMember(ArrayList<GroupMember> memberList) {
		for(GroupMember g: memberList) {
			g.persist();
		}
		return Response.ok(memberList).status(201).build();
	}
	
	@POST
	@Transactional
	@Path("/addMemberSeatMapping")
	public Response addMemberSeatMapping(ArrayList<MemberSeatMapping> msmList) {
		for(MemberSeatMapping m: msmList) {
			m.persist();
		}
		return Response.ok(msmList).status(201).build();
	}
}
