package com.test.quarkus.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import java.text.DateFormat;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import javax.inject.Inject;

import com.test.quarkus.model.Payment;

@Path("/payment")
public class PaymentResource {
	@Inject @Channel("paymentstatus") Emitter<String> paymentEmiter;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response hello() {
    	Payment payment = new Payment();
		payment.setAmount(4000);
		payment.setPaymentType("card");
		payment.setCardnumber("1234-1234-1234-1234");
		payment.setExpiryDate(new Date("11/08/2020"));
		/*Calendar calobj = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		if (df.format(payment.getExpiryDate()).compareTo(df.format(calobj.getTime())) > 0)
			return Response.status(500).entity("Card has expired").build();
		payment.persist();*/
		Jsonb jsonb = JsonbBuilder.create();
		String result = jsonb.toJson(payment);
		return Response.ok(201).entity(result).build();
        //return "hello";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/makepayment")
    @Transactional
    public Response makepayment(Payment payment) {
    	Jsonb jsonb = JsonbBuilder.create();
	    Calendar calobj = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
		
	    if (df.format(payment.getExpiryDate()).compareTo(df.format(calobj.getTime())) > 0){
		paymentEmiter.send("Payment Failed");
		    return Response.status(500).entity("Card has expired").build();
	    }
		payment.setStatus("Payment Successful");
		payment.persist();
	    	String result = jsonb.toJson(payment);
	    	System.out.println("Message being sent to Kafka is :" + result);
	    	paymentEmiter.send(result);
		return Response.ok(payment).status(201).build();
    }
}
