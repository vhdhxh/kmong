package com.longlive.kmong.service;

import com.longlive.kmong.DAO.Order;
import com.longlive.kmong.DTO.PaymentsRequestDto;
import com.longlive.kmong.DTO.UserOrderDto;
import com.longlive.kmong.DTO.mySalesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final Order order;



    public void setOrder(PaymentsRequestDto paymentsRequestDto) {
        order.setOrder(paymentsRequestDto);
    }

    public List<UserOrderDto> getOrder(Long userId) {
        return order.getOrder(userId);
    }

    public void updateStatus(Long orderId) {
        order.updateStatus(orderId);
    }

    public String getboardId(String orderId) {
        return order.getboardId(orderId);
    }

    public void updateStatus2(Long orderId) {
        order.updateStatus2(orderId);
    }

    public List<mySalesDto> mySales(Long userId) {
        return order.mySales(userId);
    }
}
