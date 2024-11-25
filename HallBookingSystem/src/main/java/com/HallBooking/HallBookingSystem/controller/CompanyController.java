package com.HallBooking.HallBookingSystem.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HallBooking.HallBookingSystem.dto.HallDTO;
import com.HallBooking.HallBookingSystem.dto.ReservationDTO;
import com.HallBooking.HallBookingSystem.services.company.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/hall/{userId}")
	public ResponseEntity<?> postHall(@PathVariable Long userId, @ModelAttribute HallDTO hallDTO) throws IOException{
		boolean success = companyService.postHall(userId, hallDTO);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/halls/{userId}")
	public ResponseEntity<?> getAllHallsByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(companyService.getAllHalls(userId));
	}
	
	@GetMapping("/hall/{hallId}")
	public ResponseEntity<?> getHallById(@PathVariable Long hallId){
		HallDTO hallDTO = companyService.getHallById(hallId);
		if(hallDTO != null) {
			return ResponseEntity.ok(hallDTO);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@PutMapping("/hall/{hallId}")
	public ResponseEntity<?> updateHall(@PathVariable Long hallId,@ModelAttribute HallDTO hallDTO) throws IOException{
		boolean success = companyService.updateHall(hallId, hallDTO);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@DeleteMapping("/hall/{hallId}")
	public ResponseEntity<?> deleteHall(@PathVariable Long hallId){
		boolean success = companyService.deleteHall(hallId);
		if(success) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@GetMapping("/bookings/{companyId}")
	public ResponseEntity<List<ReservationDTO>> getAllHallBookings(@PathVariable Long companyId){
		return ResponseEntity.ok(companyService.getAllHallBookings(companyId));
	}
	
	@GetMapping("/booking/{bookingId}/{status}")
	public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId, @PathVariable String status){
		boolean success = companyService.changeBookingStatus(bookingId, status);
		if(success)
			return ResponseEntity.ok().build();
		
		return ResponseEntity.notFound().build();
	}
	
}
