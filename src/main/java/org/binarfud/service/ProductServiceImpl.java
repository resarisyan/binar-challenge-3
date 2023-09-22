package org.binarfud.service;

import lombok.RequiredArgsConstructor;
import org.binarfud.model.Product;
import org.binarfud.repository.ProductRepository;

import java.util.HashMap;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public void initProduct() {
        productRepository.initProduct();
    }

    public HashMap<Integer, Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product getProductById(int id) {
        return productRepository.getProductById(id).orElse(null);
    }
}
