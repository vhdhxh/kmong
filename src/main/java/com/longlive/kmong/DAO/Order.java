package com.longlive.kmong.DAO;

import com.longlive.kmong.DTO.PaymentsRequestDto;
import com.longlive.kmong.DTO.UserOrderDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Order {
     void setOrder(PaymentsRequestDto paymentsRequestDto);

     List<UserOrderDto> getOrder(Long userId);

     void updateStatus(Long orderId);

    String getboardId(String orderId);

    void updateStatus2(Long orderId);
}
