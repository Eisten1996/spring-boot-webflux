package com.dipper.springboot.webflux.app.controllers;

import com.dipper.springboot.webflux.app.models.dao.ProductoDao;
import com.dipper.springboot.webflux.app.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Flux;

/**
 * @author Dipper
 * @project spring-boot-webflux
 * @created 27/10/2020 - 15:40
 */
@Controller
public class ProductoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoDao productoDao;

    @GetMapping({"/listar", "/"})
    public String listar(Model model) {
        Flux<Producto> productos = productoDao.findAll().map(producto -> {
            producto.setNombre(producto.getNombre().toUpperCase());
            return producto;
        });
        productos.subscribe(prod -> LOGGER.info(prod.getNombre()));
        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "Listado de productos");
        return "listar";
    }
}
