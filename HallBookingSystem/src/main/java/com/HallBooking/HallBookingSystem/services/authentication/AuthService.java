package com.HallBooking.HallBookingSystem.services.authentication;

import com.HallBooking.HallBookingSystem.dto.SignUpDTO;
import com.HallBooking.HallBookingSystem.dto.UserDto;

public interface AuthService {

	UserDto signupClient(SignUpDTO signupRequestDTO);
	Boolean presentByEmail(String email);
	
	UserDto signupCompany(SignUpDTO signupRequestDTO);
	
}
