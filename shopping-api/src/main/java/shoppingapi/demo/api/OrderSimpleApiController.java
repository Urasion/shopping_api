package shoppingapi.demo.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shoppingapi.demo.dto.SimpleOrderDto;
import shoppingapi.demo.domain.Order;
import shoppingapi.demo.domain.OrderSearch;
import shoppingapi.demo.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * order
 * order -> member  /to one
 * order -> Delivery /to one
 */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }
    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> orderV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        List<SimpleOrderDto> result = orders.stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());
        return result;

    }
    /**
     * 성능면에서는 조금 모자라지만 flexible 함
     */
    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> orderV3(){
        List<SimpleOrderDto> resultList = orderRepository.findAllWithMemberDelivery().stream()
                .map(order -> new SimpleOrderDto(order))
                .collect(Collectors.toList());
        return resultList;
    }

    /**
     * 성능면에서는 우위지만 flexible 하지 못함
     */
    @GetMapping("/api/v4/simple-orders")
    public List<SimpleOrderDto> orderV4(){
        List<SimpleOrderDto> orderDtos = orderRepository.findOrderDtos();
        return orderDtos;
    }
}
