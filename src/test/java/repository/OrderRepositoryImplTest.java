package repository;

import org.binarfud.repository.OrderRepository;
import org.binarfud.repository.OrderRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.binarfud.model.Order;
import org.binarfud.model.Product;

class OrderRepositoryImplTest {
    private OrderRepository orderRepository;
    private Product product;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl();
        product = new Product(1, "Nasi Goreng", 15000.0);
    }


    @Test
    void addOrderTest() {
        Order order = Order.builder().product(product).qty(3).total(3 * product.getPrice()).build();
        orderRepository.addOrder(order);
        assertTrue(orderRepository.getAllOrders().containsValue(order));
    }

    @Test
    void testGetOrderById() {
        Order order = Order.builder().product(product).qty(3).total(3 * product.getPrice()).build();
        orderRepository.addOrder(order);
        assertEquals(order, orderRepository.getOrderById(order.getId()).orElse(null));
    }

    @Test
    void testFindOrderByProduct() {
        Order order = Order.builder().product(product).qty(3).total(3 * product.getPrice()).build();
        orderRepository.addOrder(order);
        assertEquals(order, orderRepository.findOrderByProduct(product).orElse(null));
    }

    @Test
    void testFindOrderByNonExistentProduct() {
        assertFalse(orderRepository.findOrderByProduct(product).isPresent());
    }
}