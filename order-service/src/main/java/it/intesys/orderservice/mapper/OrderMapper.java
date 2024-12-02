package it.intesys.orderservice.mapper;

import it.intesys.orderservice.entity.Order;
import it.intesys.orderservice.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);

    @Mapping(source = "id", target = "orderId")
    it.intesys.orderservice.client.model.ShippingDTO toShippingClientDTO(OrderDTO order);
}
