package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.ReviewDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Review {
    public void insertReview(ReviewDto reviewDto);

}
