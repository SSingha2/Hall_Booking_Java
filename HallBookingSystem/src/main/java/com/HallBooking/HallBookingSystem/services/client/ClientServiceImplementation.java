package com.HallBooking.HallBookingSystem.services.client;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HallBooking.HallBookingSystem.dto.HallDTO;
import com.HallBooking.HallBookingSystem.dto.HallDetailsForClientDTO;
import com.HallBooking.HallBookingSystem.dto.ReservationDTO;
import com.HallBooking.HallBookingSystem.dto.ReviewDTO;
import com.HallBooking.HallBookingSystem.entity.Hall;
import com.HallBooking.HallBookingSystem.entity.Reservation;
import com.HallBooking.HallBookingSystem.entity.Review;
import com.HallBooking.HallBookingSystem.entity.User;
import com.HallBooking.HallBookingSystem.enums.ReservationStatus;
import com.HallBooking.HallBookingSystem.enums.ReviewStatus;
import com.HallBooking.HallBookingSystem.repository.HallRepository;
import com.HallBooking.HallBookingSystem.repository.ReservationRepository;
import com.HallBooking.HallBookingSystem.repository.ReviewRepository;
import com.HallBooking.HallBookingSystem.repository.UserRepository;

@Service
public class ClientServiceImplementation implements ClientService {

	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<HallDTO> getAllHalls(){
		return hallRepository.findAll().stream().map(Hall::getHallDto).collect(Collectors.toList());
	}
	
	public List<HallDTO> searchHallByName(String name){
		return hallRepository.findAllByHallNameContaining(name).stream().map(Hall::getHallDto).collect(Collectors.toList());
	}
	
	public HallDetailsForClientDTO getHallDeatilsById(Long hallId) {
		Optional<Hall> optionalHall = hallRepository.findById(hallId);
		HallDetailsForClientDTO hallDetailsForClientDTO = new HallDetailsForClientDTO();
		
		if(optionalHall.isPresent()) {
			hallDetailsForClientDTO.setHallDto(optionalHall.get().getHallDto());
			
			List<Review> reviewList = reviewRepository.findAllByHallId(hallId);
			hallDetailsForClientDTO.setReviewDTOList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
		}
		
		return hallDetailsForClientDTO;
	}
	
	public boolean bookService(ReservationDTO reservationDTO) {
		Optional<Hall> optionalHall= hallRepository.findById(reservationDTO.getHallId());
		Optional<User> optionalUser= userRepository.findById(reservationDTO.getUserId());
		
		if(optionalHall.isPresent() && optionalUser.isPresent()) {
			Reservation reservation = new Reservation();
			
			reservation.setBookDate(reservationDTO.getBookDate());
			reservation.setReservationStatus(ReservationStatus.PENDING);
			reservation.setUser(optionalUser.get());
			
			reservation.setHall(optionalHall.get());
			reservation.setCompany(optionalHall.get().getUser());
			reservation.setReviewStatus(ReviewStatus.FALSE);
			
			reservationRepository.save(reservation);
			return true;
		}
		return false;
	}
	
	public List<ReservationDTO> getAllBookingsByUserId(Long userId){
		return reservationRepository.findAllByUserId(userId).stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	
	public Boolean giveReview(ReviewDTO reviewDTO) {
		Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());
		Optional<Reservation> optionalBooking = reservationRepository.findById(reviewDTO.getBookId());
		
		if(optionalBooking.isPresent() && optionalUser.isPresent()) {
			Review review = new Review();
			review.setReviewDate(new Date());
			review.setReview(reviewDTO.getReview());
			review.setRating(reviewDTO.getRating());
			review.setUser(optionalUser.get());
			review.setHall(optionalBooking.get().getHall());
			
			reviewRepository.save(review);
			
			Reservation booking = optionalBooking.get();
			booking.setReviewStatus(ReviewStatus.TRUE);
			
			reservationRepository.save(booking);
			
			return true;
		}
		return false;
	}
}