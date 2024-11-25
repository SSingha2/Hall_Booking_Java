package com.HallBooking.HallBookingSystem.services.company;

import java.io.IOException;
import java.util.List;

import com.HallBooking.HallBookingSystem.dto.HallDTO;
import com.HallBooking.HallBookingSystem.dto.ReservationDTO;

public interface CompanyService {

	boolean postHall(Long userId, HallDTO hallDTO) throws IOException;
	
	List<HallDTO> getAllHalls(Long userId);
	
	HallDTO getHallById(Long hallId);
	
	boolean updateHall(Long hallId, HallDTO hallDTO) throws IOException;
	
	public boolean deleteHall(Long hallId);
	
	List<ReservationDTO> getAllHallBookings(Long companyId);
	
	boolean changeBookingStatus(Long bookingId, String status);
}
