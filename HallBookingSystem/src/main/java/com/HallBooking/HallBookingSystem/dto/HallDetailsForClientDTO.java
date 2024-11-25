package com.HallBooking.HallBookingSystem.dto;

import java.util.List;

public class HallDetailsForClientDTO {

	
	private HallDTO hallDto;

	private List<ReviewDTO> reviewDTOList;
	
	public HallDTO getHallDto() {
		return hallDto;
	}

	public void setHallDto(HallDTO hallDto) {
		this.hallDto = hallDto;
	}

	public List<ReviewDTO> getReviewDTOList() {
		return reviewDTOList;
	}

	public void setReviewDTOList(List<ReviewDTO> reviewDTOList) {
		this.reviewDTOList = reviewDTOList;
	}
}
