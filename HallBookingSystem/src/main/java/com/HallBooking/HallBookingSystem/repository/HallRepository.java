package com.HallBooking.HallBookingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HallBooking.HallBookingSystem.entity.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>{
	List<Hall> findAllByUserId(Long userId);
	
	List<Hall> findAllByHallNameContaining(String name);
}
