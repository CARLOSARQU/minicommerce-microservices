package com.tienda.product_service.repository;

import com.tienda.product_service.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // Aquí podrías agregar métodos mágicos como:
    // List<Product> findByName(String name);
}