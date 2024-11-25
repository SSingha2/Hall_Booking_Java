package com.HallBooking.HallBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HallBooking.HallBookingSystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findFirstByEmail(String email) ;
}
