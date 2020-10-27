package com.dipper.springboot.webflux.app;

import com.dipper.springboot.webflux.app.models.dao.ProductoDao;
import com.dipper.springboot.webflux.app.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

    @Autowired
    private ProductoDao productoDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mongoTemplate.dropCollection("productos").subscribe();
        Flux.just(new Producto("Medical Dental Instruments", 204.31)
                , new Producto("Telecommunications Equipment", 139.81)
                , new Producto("Investment Managers", 249.51)
                , new Producto("Finance: Consumer Services", 156.61)
                , new Producto("Business Services", 209.62)
                , new Producto("Savings Institutions", 202.97)
                , new Producto("Marine Transportation", 241.51)
        ).flatMap(producto -> productoDao.save(producto)).subscribe(producto -> LOGGER.info("Insert: " + producto.getId() + " "
                + producto.getNombre()));
    }
}
