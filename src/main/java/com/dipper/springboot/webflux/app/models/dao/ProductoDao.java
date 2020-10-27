package com.dipper.springboot.webflux.app.models.dao;

import com.dipper.springboot.webflux.app.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author Dipper
 * @project spring-boot-webflux
 * @created 26/10/2020 - 11:51
 */
public interface ProductoDao extends ReactiveMongoRepository<Producto, String> {
}
