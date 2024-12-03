package it.intesys.orderservice.service;

import it.intesys.orderservice.client.api.DefaultApi;
import it.intesys.orderservice.dto.OrderDTO;
import it.intesys.orderservice.mapper.OrderMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static it.intesys.orderservice.entity.OrderStatus.SHIPPED;
import static it.intesys.orderservice.entity.OrderStatus.SHIPPING;

@Service
@Slf4j
@AllArgsConstructor
public class OrderConsumer {

    private final OrderService orderService;
    private final DefaultApi shippingApi;
    private final OrderMapper orderMapper;

    @KafkaListener(topics = "order.created")
    public void consumeOrderCreatedEvent(OrderDTO orderDTO) {

        log.info("Order created event received for order {}", orderDTO.id());
        shippingApi.v1ApiShippingPost(orderMapper.toShippingClientDTO(orderDTO));
        // Removed useless consumer service and update order status
        orderService.updateStatus(orderDTO.id(), SHIPPING.getValue());
    }

    @KafkaListener(topics = "order.shipped")
    public void consumeOrderShippedEvent(Long orderId) {

        orderService.updateStatus(orderId, SHIPPED.getValue());
    }
}
