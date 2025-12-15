package com.tienda.product_service.listener;

import com.tienda.product_service.dto.OrderEvent;
import com.tienda.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventListener {
    private final ProductRepository productRepository;
    @RabbitListener(queues = "pedido-creado-queue")
    public void handleOrderCreated(OrderEvent event) {
        System.out.println("Evento de pedido recibido en el servicio de productos: " + event.getSkuCode());
        var products = productRepository.findAll();

        for (var product : products) {
            if (product.getName().equals(event.getSkuCode())) {
                product.setStock(product.getStock()-event.getQuantity());
                productRepository.save(product);
                System.out.println("Stock actualizado para el producto " + product.getName() + ": " + product.getStock());
                break;
            }
        }
    }
}
