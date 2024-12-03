package it.intesys.orderservice.mapper;

import it.intesys.orderservice.dto.OrderDTO;
import it.intesys.orderservice.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);

    @Mapping(source = "id", target = "orderId")
    @Mapping(target = "id", ignore = true)
    it.intesys.orderservice.client.model.ShippingDTO toShippingClientDTO(OrderDTO order); // Fix correct mapping
}
