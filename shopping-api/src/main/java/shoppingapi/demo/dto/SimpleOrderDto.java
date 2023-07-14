package shoppingapi.demo.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import shoppingapi.demo.domain.Address;
import shoppingapi.demo.domain.Order;
import shoppingapi.demo.domain.OrderStatus;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class SimpleOrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public SimpleOrderDto(Order order){
        this.orderId = order.getId();
        this.name = order.getMember().getName();
        this.orderDate = order.getOrderDate();
        this.orderStatus = order.getStatus();
        this.address = order.getDelivery().getAddress();
    }
    public SimpleOrderDto(Long orderId, String name, LocalDateTime orderDate
    , OrderStatus orderStatus, Address address){
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}
