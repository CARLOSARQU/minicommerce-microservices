package com.tienda.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@FeignClient(name = "product-service")
public interface ProductClient {

    // Copiamos la firma del método del controlador de productos
    @GetMapping("/products")
    List<Object> getAllProducts(); 
    // Usamos List<Object> por flojera para no duplicar el DTO del producto aquí por ahora
}