package com.HallBooking.HallBookingSystem.services.client;

import java.util.List;

import com.HallBooking.HallBookingSystem.dto.HallDTO;
import com.HallBooking.HallBookingSystem.dto.HallDetailsForClientDTO;
import com.HallBooking.HallBookingSystem.dto.ReservationDTO;
import com.HallBooking.HallBookingSystem.dto.ReviewDTO;

public interface ClientService {

	 List<HallDTO> getAllHalls();
	 
	 List<HallDTO> searchHallByName(String name);
	 
	 boolean bookService(ReservationDTO reservationDTO);
	 
	 HallDetailsForClientDTO getHallDeatilsById(Long hallid);
	 
	 List<ReservationDTO> getAllBookingsByUserId(Long userId);
	 
	 Boolean giveReview(ReviewDTO reviewDTO);
}
