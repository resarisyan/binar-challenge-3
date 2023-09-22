package org.binarfud.service;

import org.binarfud.model.Product;

import java.util.HashMap;

public interface ProductService {
    void initProduct();
    HashMap<Integer, Product> getAllProducts();
    Product getProductById(int id);
}
