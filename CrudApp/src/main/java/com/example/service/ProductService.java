package com.example.service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service for managing Product-related operations.
 * Contains the business logic for CRUD operations on products.
 */
@Service
public class ProductService {

    // Injecting the ProductRepository to handle database operations
    @Autowired
    private ProductRepository productRepository;

    /**
     * Fetches all products with pagination.
     * 
     * @param page The page number (zero-based index).
     * @param size The number of products per page.
     * @return A paginated list of products.
     */
    public Page<Product> getAllProducts(int page, int size) {
        // Using PageRequest to handle pagination logic and fetching the products
        return productRepository.findAll(PageRequest.of(page, size));
    }

    /**
     * Fetches a product by its ID and populates its associated category.
     * 
     * @param id The ID of the product to fetch.
     * @return The product with its category details.
     */
    public Product getProductById(Long id) {
        // Fetching the product by ID from the repository
        Optional<Product> product = productRepository.findById(id);
        
        // If the product is found, return it; otherwise, throw an exception
        if (product.isPresent()) {
            return product.get();  // The category will be automatically populated
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    /**
     * Creates a new product.
     * 
     * @param product The product details to be created.
     * @return The newly created product.
     */
    public Product createProduct(Product product) {
        // Saving the product to the database using the repository
        return productRepository.save(product);
    }

    /**
     * Updates an existing product.
     * 
     * @param id The ID of the product to update.
     * @param product The updated product details.
     * @return The updated product.
     */
    public Product updateProduct(Long id, Product product) {
        // Check if the product exists before updating it
        Optional<Product> existingProduct = productRepository.findById(id);
        
        if (existingProduct.isPresent()) {
            // Set the ID of the updated product and save it
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Long id) {
        // Check if the product exists before deleting it
        Optional<Product> existingProduct = productRepository.findById(id);
        
        if (existingProduct.isPresent()) {
            // Deleting the product from the database
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
