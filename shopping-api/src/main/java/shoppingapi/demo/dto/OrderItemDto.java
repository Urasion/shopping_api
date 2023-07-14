package shoppingapi.demo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import shoppingapi.demo.domain.OrderItem;

@Data
@RequiredArgsConstructor
public class OrderItemDto {
    private String name;
    private int orderPrice;
    private int count;
    public OrderItemDto(OrderItem orderItem){
        this.name = orderItem.getItem().getName();
        this.orderPrice = orderItem.getOrderPrice();
        this.count = orderItem.getCount();

    }

}
