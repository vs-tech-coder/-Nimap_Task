package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing Product-related operations.
 * Provides API endpoints for CRUD operations on products.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Injecting the ProductService to handle the business logic
    @Autowired
    private ProductService productService;

    /**
     * Endpoint to get all products with pagination.
     * 
     * @param page The page number (zero-based index).
     * @param size The number of products per page.
     * @return A paginated list of products.
     */
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam int page, @RequestParam int size) {
        // Delegating the request to ProductService to get paginated products
        return productService.getAllProducts(page, size);
    }

    /**
     * Endpoint to get a product by its ID, including category details.
     * 
     * @param id The ID of the product to fetch.
     * @return The product with the associated category details.
     */
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        // Delegating the request to ProductService to get the product by ID
        return productService.getProductById(id);
    }

    /**
     * Endpoint to create a new product.
     * 
     * @param product The product details to be created.
     * @return The created product.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // Delegating the request to ProductService to create the new product
        return productService.createProduct(product);
    }

    /**
     * Endpoint to update an existing product.
     * 
     * @param id The ID of the product to update.
     * @param product The updated product details.
     * @return The updated product.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // Delegating the request to ProductService to update the product by ID
        return productService.updateProduct(id, product);
    }

    /**
     * Endpoint to delete a product by its ID.
     * 
     * @param id The ID of the product to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // Delegating the request to ProductService to delete the product by ID
        productService.deleteProduct(id);
    }
}
