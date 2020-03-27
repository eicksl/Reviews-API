package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with {@link Product} entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    public ProductsController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a {@link Product} entity.
     *
     * @param product The {@link Product} to create.
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Product> createProduct(@RequestBody final Product product) {
        productRepository.save(product);
        return productRepository.findById(product.getId());
    }

    /**
     * Finds a {@link Product} by id.
     *
     * @param id The id of the product.
     * @return The product if found, or a 404 not found.
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) {
        return ResponseEntity.of(productRepository.findById(id));
    }

    /**
     * Lists all products.
     *
     * @return The list of products.
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Product> listProducts() {
        return productRepository.findAll();
    }
}
