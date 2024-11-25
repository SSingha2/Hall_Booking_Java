package com.HallBooking.HallBookingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HallBooking.HallBookingSystem.dto.ReservationDTO;
import com.HallBooking.HallBookingSystem.dto.ReviewDTO;
import com.HallBooking.HallBookingSystem.services.client.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/halls")
	public ResponseEntity<?> getAllHalls(){
		return ResponseEntity.ok(clientService.getAllHalls());	
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<?> searchHallByName(@PathVariable String name){
		return ResponseEntity.ok(clientService.searchHallByName(name));
	}
	
	@PostMapping("/book-hall")
	public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO){
		boolean success = clientService.bookService(reservationDTO);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/hall/{hallId}")
	public ResponseEntity<?> getHallDetailsByHallId(@PathVariable Long hallId){
		return ResponseEntity.ok(clientService.getHallDeatilsById(hallId));
	}
	
	@GetMapping("/my-bookings/{userId}")
	public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(clientService.getAllBookingsByUserId(userId));
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDTO){
		Boolean success = clientService.giveReview(reviewDTO);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
