package it.intesys.shippingservice.api;

import io.swagger.v3.oas.annotations.Operation;
import it.intesys.shippingservice.api.model.ShippingDTO;
import it.intesys.shippingservice.mapper.ShippingMapper;
import it.intesys.shippingservice.service.ShippingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ShippingController implements V1Api {

    private final ShippingService shippingService;
    private final ShippingMapper shippingMapper;

    @Override
    public ResponseEntity<Long> v1ApiShippingPost(@Valid @RequestBody(required = false) ShippingDTO body) {
        var shippingDTO = shippingMapper.toShippingDTO(body);
        log.info("Shipping request received for order %s".formatted(shippingDTO.id()));
        return ResponseEntity.ok(shippingService.save(shippingDTO));
    }

}
