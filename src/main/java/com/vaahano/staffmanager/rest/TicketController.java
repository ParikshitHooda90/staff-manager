package com.vaahano.staffmanager.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaahano.staffmanager.bean.ticket.TicketPassPurchasedRequest;
import com.vaahano.staffmanager.bean.ticket.TicketPurchasedRequest;

@RestController("/staff/ticket")
public class TicketController {
	
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	@PostMapping("/pass/purchased")
	public ResponseEntity<Void> handleTicketPassPurchased(TicketPassPurchasedRequest request){
		logger.info("Recieved Ticket pass purchased request: {}", request); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/purchased")
	public ResponseEntity<Void> handleTicketPurchased(TicketPurchasedRequest request){
		logger.info("Recieved Ticket purchased request: {}", request);
		// identify which conductor is for busId.
		// send notification to that session.
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
