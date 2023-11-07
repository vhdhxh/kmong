package com.longlive.kmong.controller;

import com.longlive.kmong.DTO.ItemDto;
import com.longlive.kmong.DTO.PaymentsRequestDto;
import com.longlive.kmong.service.BoardService;
import com.longlive.kmong.service.OrderService;
import com.longlive.kmong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    private final BoardService boardService;
    private final OrderService orderService;

    @GetMapping("/{id}")
    public ItemDto getOrder(@PathVariable("id") String ItemId) {
        ItemDto itemDto = boardService.getOrder(ItemId);
        return itemDto;
    }

    @PostMapping("/paySuccess")
    public void paySuccess(PaymentsRequestDto paymentsRequestDto) {
        orderService.setOrder(paymentsRequestDto);
    }
}

