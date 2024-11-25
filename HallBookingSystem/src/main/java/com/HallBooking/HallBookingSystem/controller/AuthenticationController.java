package com.HallBooking.HallBookingSystem.controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.HallBooking.HallBookingSystem.dto.AuthenticationRequest;
import com.HallBooking.HallBookingSystem.dto.SignUpDTO;
import com.HallBooking.HallBookingSystem.dto.UserDto;
import com.HallBooking.HallBookingSystem.entity.User;
import com.HallBooking.HallBookingSystem.repository.UserRepository;
import com.HallBooking.HallBookingSystem.services.authentication.AuthService;
import com.HallBooking.HallBookingSystem.services.jwt.UserDetailsServiceImplementation;
import com.HallBooking.HallBookingSystem.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserDetailsServiceImplementation userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public static final String TOKEN_PREFIX = "Bearer ";
	
	public static final String HEADER_STRING = "Authorization";
	
	@PostMapping("/client/sign-up")
	public ResponseEntity<?> signupClient(@RequestBody SignUpDTO signupDTO){
		if(authService.presentByEmail(signupDTO.getEmail())) {
			return new ResponseEntity<>("User already exists with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto createdUser = authService.signupClient(signupDTO);
		
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	}
	
	@PostMapping("/company/sign-up")
	public ResponseEntity<?> signupCompany(@RequestBody SignUpDTO signupDTO){
		if(authService.presentByEmail(signupDTO.getEmail())) {
			return new ResponseEntity<>("Company already exists with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDto createdUser = authService.signupCompany(signupDTO);
		
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
	}
	
	
	@PostMapping({"/authenticate"})
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		}catch(BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
		
		response.getWriter().write(new JSONObject()
				.put("userId", user.getId())
				.put("role",user.getRole())
				.toString()
				);
		
		response.addHeader("Access-Control-Expose-Headers","Authorization");
		response.addHeader("Access-Control-Allow-Headers","Authorization,"+" X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);
	}
}
