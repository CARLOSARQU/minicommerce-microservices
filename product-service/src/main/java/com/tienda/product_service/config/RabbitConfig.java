package com.tienda.product_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue orderQueue() {
        return new Queue("pedido-creado-queue", true); // true = durable (no se borra si Rabbit se apaga)
    }

    // 2. Esto es VITAL para que entienda el JSON que le envía Order Service
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}