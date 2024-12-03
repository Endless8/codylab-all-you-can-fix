package it.intesys.shippingservice.mapper;


import it.intesys.shippingservice.dto.ShippingDTO;
import it.intesys.shippingservice.entity.Shipping;
import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

@Mapper(componentModel = "spring")
public interface ShippingMapper {

    Shipping toEntity(ShippingDTO shippingDTO);

    it.intesys.shippingservice.client.model.ShippingDTO toShippingProviderClientDTO(Shipping shipping);

    ShippingDTO toShippingDTO(it.intesys.shippingservice.api.model.ShippingDTO shippingDTO); // Add correct mapping

    // Dto id null value handling
    default Long map(JsonNullable<Long> value) {
        return value != null && value.isPresent() ? value.get() : null;
    }
}
