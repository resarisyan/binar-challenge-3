package org.binarfud.repository;

import org.binarfud.model.Product;

import java.util.HashMap;
import java.util.Optional;

public interface ProductRepository {
    HashMap<Integer, Product> getAllProducts();
    Optional<Product> getProductById(int id);
    void initProduct();
}
