package com.test.quarkus.rest;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.smallrye.reactive.messaging.annotations.Merge;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import io.smallrye.reactive.messaging.annotations.Merge;
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import com.test.quarkus.model.Trip;
import java.util.UUID;
import io.smallrye.reactive.messaging.annotations.Blocking;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.eclipse.microprofile.context.ManagedExecutor;
import org.eclipse.microprofile.context.ThreadContext;
import javax.transaction.UserTransaction;
import javax.inject.Inject;

@ApplicationScoped
public class TripMessageProcessor {
	@Inject UserTransaction transaction;
	
    @Incoming("paymentstatus")
    @Merge
    public  void process(String paymentString) {
	    	try{
		System.out.println("Payment status string is :" + paymentString);
		JSONObject jo = (JSONObject)new JSONParser().parse(paymentString);
			String tripId = (String)jo.get("tripId");
	    	System.out.println("Trip Id is " + tripId);
	    	UUID tripUuid = UUID.fromString(tripId);
	        /*Trip trip = Trip.find("id=?1",  tripUuid).firstResult();
			System.out.println("Trip Details are :" + trip.getTripId());
		Jsonb jsonb = JsonbBuilder.create();
		String result = jsonb.toJson(trip);
		System.out.println("Trip Details are :" + result);*/
			
	    	String paymentstatus = (String)jo.get("status");
		System.out.println("Payment Status is " + paymentstatus);
			ManagedExecutor executor = ManagedExecutor.builder() .maxAsync(5) .propagated(ThreadContext.CDI, ThreadContext.TRANSACTION) .build();
		 ThreadContext threadContext = ThreadContext.builder() .propagated(ThreadContext.CDI, ThreadContext.TRANSACTION) .build();
		
    
		 executor.runAsync(threadContext.contextualRunnable(()->{
        try {
		
		transaction.begin();
		 Trip.update("tripStatus=?1 where id=?2",  paymentstatus, tripUuid);
		 transaction.commit();
		 
	   } catch(Exception e) {
            System.out.println("Something wrong happened !!!");
		e.printStackTrace();
        }
	
     }));
	    	
		}catch(org.json.simple.parser.ParseException e){
		e.printStackTrace();
		}
	}
}
