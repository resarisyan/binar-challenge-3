package service;

import org.binarfud.model.Product;
import org.binarfud.repository.ProductRepository;
import org.binarfud.repository.ProductRepositoryImpl;
import org.binarfud.service.ProductService;
import org.binarfud.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ProductServiceImplTest {
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(new ProductRepositoryImpl());
        productService.initProduct();
    }

    @Test
    void testGetProductById() {
        productService.initProduct();
        Product product = productService.getProductById(1);
        assertEquals("Nasi Goreng", product.getName());

        assertNull(productService.getProductById(6));
    }

    @Test
    void testGetAllProducts() {
        HashMap<Integer, Product> products = productService.getAllProducts();
        assertEquals(5, products.size());
    }
}
