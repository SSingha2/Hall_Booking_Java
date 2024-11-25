package com.HallBooking.HallBookingSystem.services.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.HallBooking.HallBookingSystem.dto.SignUpDTO;
import com.HallBooking.HallBookingSystem.dto.UserDto;
import com.HallBooking.HallBookingSystem.entity.User;
import com.HallBooking.HallBookingSystem.enums.UserRole;
import com.HallBooking.HallBookingSystem.repository.UserRepository;

@Service
public class AuthServiceImplementation implements AuthService{
	
	@Autowired
	private UserRepository userRepository;

	
	public UserDto signupClient(SignUpDTO signupRequestDTO) {
		User user = new User();
		
		user.setName(signupRequestDTO.getName());
		user.setLastname(signupRequestDTO.getLastname());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		
		user.setRole(UserRole.CLIENT);
		
		return userRepository.save(user).getDto();
	}
	
	public Boolean presentByEmail(String email) {
		return userRepository.findFirstByEmail(email) != null ;
	}
	
	public UserDto signupCompany(SignUpDTO signupRequestDTO) {
		User user = new User();
		
		user.setName(signupRequestDTO.getName());
		user.setEmail(signupRequestDTO.getEmail());
		user.setPhone(signupRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		
		user.setRole(UserRole.COMPANY);
		
		return userRepository.save(user).getDto();
	}
}
