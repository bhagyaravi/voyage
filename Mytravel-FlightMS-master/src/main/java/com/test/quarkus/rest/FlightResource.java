package com.test.quarkus.rest;

import java.util.Date;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.quarkus.model.Flight;
import com.test.quarkus.model.FlightBooking;


@Path("/flight")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FlightResource {

	@GET
	@Path("/searchFlight")
	public Response searchFlights(@QueryParam("fromcity") String fromcity, @QueryParam("tocity") String tocity,	@QueryParam("flighttime") Date flighttime) {
		System.out.println("From City is :" + fromcity);
		System.out.println("To City is :" + tocity);
		System.out.println("Flight time is :" + flighttime);
		return Response.ok(Flight.list("fromCity = ?1 and toCity = ?2 and flightTime =?3 and seatsFilled < totalSeats", fromcity, tocity, flighttime)).build();
	}

	@POST
	@Transactional
	@Path("/addNewFlight")
	public Response addNewFlight(Flight flight) {
		flight.persist();
		return Response.ok(flight).status(201).build();
	}

	@POST
	@Transactional
	@Path("/bookFlight")
	public Response bookFlight(FlightBooking flightBooking) {
		flightBooking.persist();
		return Response.ok(flightBooking).status(201).build();
	}
	
	@Get
	@Path("/sampleflight")
	public Response sampleFlight(){
		Flight flight = new Flight();
		flight.setFlightBrand("Air India");
		flight.setFlightTime("2020-07-18 15:30:00");
		flight.setFromCity("Banaglore");
		flight.setToCity("Mumbai");
		flight.totalSeats(40);
		flight.setSeatsFilled(0);
		return Response.ok(flight).status(200).build();
	}

}
