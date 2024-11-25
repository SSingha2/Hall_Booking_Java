package com.HallBooking.HallBookingSystem.services.company;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HallBooking.HallBookingSystem.dto.HallDTO;
import com.HallBooking.HallBookingSystem.dto.ReservationDTO;
import com.HallBooking.HallBookingSystem.entity.Hall;
import com.HallBooking.HallBookingSystem.entity.Reservation;
import com.HallBooking.HallBookingSystem.entity.User;
import com.HallBooking.HallBookingSystem.enums.ReservationStatus;
import com.HallBooking.HallBookingSystem.repository.HallRepository;
import com.HallBooking.HallBookingSystem.repository.ReservationRepository;
import com.HallBooking.HallBookingSystem.repository.UserRepository;

@Service
public class CompanyServiceImplementation implements CompanyService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	public boolean postHall(Long userId, HallDTO hallDTO) throws IOException {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			Hall hall = new Hall();
			hall.setHallName(hallDTO.getHallName());
			hall.setDescription(hallDTO.getDescription());
			hall.setImg(hallDTO.getImg().getBytes());
			hall.setPrice(hallDTO.getPrice());
			hall.setUser(optionalUser.get());
			
			hallRepository.save(hall);
			return true;
		}
		return false;
	}
	
	public List<HallDTO> getAllHalls(Long userId){
		return hallRepository.findAllByUserId(userId).stream().map(Hall::getHallDto).collect(Collectors.toList());
	}
	
	public HallDTO getHallById(Long hallId) {
		Optional<Hall> optionalHall = hallRepository.findById(hallId);
		
		if(optionalHall.isPresent()) {
			return optionalHall.get().getHallDto();
		}
		return null;
	}
	
	public boolean updateHall(Long hallId, HallDTO hallDTO) throws IOException {
		Optional<Hall> optionalHall = hallRepository.findById(hallId);
		if(optionalHall.isPresent()) {
			Hall hall = optionalHall.get();
			
			hall.setHallName(hallDTO.getHallName());
			hall.setDescription(hallDTO.getDescription());
			hall.setPrice(hallDTO.getPrice());
			
			if(hallDTO.getImg() != null) {
				hall.setImg(hallDTO.getImg().getBytes());
			}
			
			hallRepository.save(hall);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteHall(Long hallId) {
		Optional<Hall> optionalHall = hallRepository.findById(hallId);
		if(optionalHall.isPresent()) {
			hallRepository.delete(optionalHall.get());
			return true;
		}else {
			return false;
		}
	}
	
	public List<ReservationDTO> getAllHallBookings(Long companyId){
		return reservationRepository.findAllByCompanyId(companyId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	
	public boolean changeBookingStatus(Long bookingId, String status) {
		Optional<Reservation> optionalReservation = reservationRepository.findById(bookingId);
		if(optionalReservation.isPresent()) {
			Reservation existingReservation = optionalReservation.get();
			if(Objects.equals(status, "Approve")) {
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			}else
			{
				existingReservation.setReservationStatus(ReservationStatus.REJECTED);
			}
			
			reservationRepository.save(existingReservation);
			return true;
		}
		return false;
	}
	
}
