package com.jpa.relation.controller;

import com.jpa.relation.persistence.entity.Product;
import com.jpa.relation.persistence.repository.ProductRepository;
import com.jpa.relation.persistence.specification.SearchProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase controladora que maneja las solicitudes relacionadas con los productos.
 *
 * Esta clase se encarga de exponer endpoints REST para buscar y obtener información de los productos.
 */
@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene una lista paginada de productos filtrados por diferentes criterios.
     *
     * @param name El nombre del producto a buscar (opcional).
     * @param minPrice El precio mínimo del producto a buscar (opcional).
     * @param maxPrice El precio máximo del producto a buscar (opcional).
     * @param category La categoría del producto a buscar (opcional).
     * @return Lista paginada y ordenada de {@link Product} que representan los productos encontrados.
     */
    @GetMapping("/v1")
    public Page<Product> findAll(@PageableDefault(page = 0, size = 5) Pageable pageable,
                                 @RequestParam(required = false) String name,
                                 @RequestParam(required = false) BigDecimal minPrice,
                                 @RequestParam(required = false) BigDecimal maxPrice,
                                 @RequestParam(required = false) String category) {
        System.out.println("products/v1");

        SearchProductSpecification specification = new SearchProductSpecification(name, minPrice, maxPrice, category);
        return productRepository.findAll(specification, pageable);
    }
//    @GetMapping("/v2")
//    public List<Product> findAll(@RequestParam(required = false) String name,
//                                 @RequestParam(required = false) BigDecimal minPrice,
//                                 @RequestParam(required = false) BigDecimal maxPrice,
//                                 @RequestParam(required = false) String category,
//                                 @RequestParam(required = false, defaultValue = "0") int page,
//                                 @RequestParam(required = false, defaultValue = "10") int size,
//                                 @RequestParam(required = false, defaultValue = "name") List<String> sort,
//                                 @RequestParam(required = false, defaultValue = "asc") String sortDirection) {
//        // Convertir la dirección del ordenamiento a Sort.Direction
//        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
//
//        // Parsear y construir el objeto Sort desde la lista de propiedades y la dirección del ordenamiento.
//        Sort parsedSort = Sort.by(direction, sort.toArray(new String[0]));
//
//        // Crear un objeto Pageable para realizar la paginación y ordenamiento de los resultados
//        Pageable pageable = PageRequest.of(page, size, parsedSort);
//
//        // Crear una instancia de la especificación de búsqueda con los criterios proporcionados
//        SearchProductSpecification specification = new SearchProductSpecification(name, minPrice, maxPrice, category);
//
//        // Realizar la consulta a través del repositorio utilizando la especificación y el pageable
//        Page<Product> productPage = productRepository.findAll(specification, pageable);
//
//        // Obtener la lista de productos de la página actual y devolverla como resultado
//        return productPage.getContent();
//    }
}
