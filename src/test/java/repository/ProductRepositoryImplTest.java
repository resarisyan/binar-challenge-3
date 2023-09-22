package repository;

import org.binarfud.repository.ProductRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductRepositoryImplTest {
    private ProductRepositoryImpl productRepository;

    @BeforeEach
    public void setUp() {
        productRepository = new ProductRepositoryImpl();
        productRepository.initProduct();
    }

    @Test
    public void testGetProductById() {
        assertTrue(productRepository.getProductById(1).isPresent());
        assertEquals("Nasi Goreng", productRepository.getProductById(1).get().getName());

        assertFalse(productRepository.getProductById(10).isPresent());
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(5, productRepository.getAllProducts().size());
    }
}
