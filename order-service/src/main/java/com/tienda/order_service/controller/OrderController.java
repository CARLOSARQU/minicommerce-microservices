package com.tienda.order_service.controller;

import com.tienda.order_service.client.ProductClient;
import com.tienda.order_service.dto.OrderEvent;
import com.tienda.order_service.model.Order;
import com.tienda.order_service.repository.OrderRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate; // <--- ¿Tienes este import?
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final RabbitTemplate rabbitTemplate; 

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order order) {
        
        // 1. Llamada a productos
        productClient.getAllProducts();

        // 2. Guardar pedido
        order.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        // 3. Enviar a RabbitMQ
        if (order.getOrderLineItemsList() != null && !order.getOrderLineItemsList().isEmpty()) {
            var item = order.getOrderLineItemsList().get(0);
            OrderEvent event = new OrderEvent(order.getOrderNumber(), item.getSkuCode(), item.getQuantity());
            
            // Si rabbitTemplate es null, aquí explota con Error 500
            rabbitTemplate.convertAndSend("pedido-creado-queue", event);
            System.out.println("🐇 Mensaje enviado: " + event);
        }

        return "Pedido realizado con éxito";
    }
}