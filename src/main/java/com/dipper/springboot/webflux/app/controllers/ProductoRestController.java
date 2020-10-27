package com.dipper.springboot.webflux.app.controllers;

import com.dipper.springboot.webflux.app.models.dao.ProductoDao;
import com.dipper.springboot.webflux.app.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Dipper
 * @project spring-boot-webflux
 * @created 27/10/2020 - 17:05
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoDao productoDao;

    @GetMapping()
    public Flux<Producto> index() {
        Flux<Producto> productos = productoDao.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        }).doOnNext(producto -> LOGGER.info(producto.getNombre()));

        return productos;
    }

    @GetMapping("/{id}")
    public Mono<Producto> show(@PathVariable String id) {
//        Mono<Producto> producto = productoDao.findById(id);
        Flux<Producto> productos = productoDao.findAll();
        Mono<Producto> producto = productos.filter(p -> p.getId().equals(id))
                .next()
                .doOnNext(prod -> LOGGER.info(prod.getNombre()));
        return producto;
    }
}
