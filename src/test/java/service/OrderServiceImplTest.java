package service;

import org.binarfud.model.Order;
import org.binarfud.model.Product;
import org.binarfud.repository.OrderRepository;
import org.binarfud.repository.OrderRepositoryImpl;
import org.binarfud.service.OrderService;
import org.binarfud.service.OrderServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    private OrderRepository orderRepository;
    private OrderService orderService;
    private Product product;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepositoryImpl();
        orderService = new OrderServiceImpl(orderRepository);
        product = new Product(1, "Nasi Goreng", 15000.0);
    }

    @AfterEach
    void tearDown() {
        orderRepository.getAllOrders().clear();
    }

    @Test
    void testAddOrderWithZeroQuantity() {
        orderService.addOrder(product, 0);

        Order order = orderRepository.findOrderByProduct(product).orElse(null);
        assertNull(order);
    }

    @Test
    void testAddOrderWithNegativeQuantity() {
        orderService.addOrder(product, -2);

        Order order = orderRepository.findOrderByProduct(product).orElse(null);
        assertNull(order);
    }

    @Test
    void testAddOrderExistingProduct() {
        orderService.addOrder(product, 2);
        orderService.addOrder(product, 3);

        Order order = orderRepository.findOrderByProduct(product).orElse(null);
        assertNotNull(order);
        assertEquals(5, order.getQty());
        assertEquals(75000.0, order.getTotal(), 0.01);
    }

    @Test
    void testAddOrderWithNonExistentProduct() {
        orderService.addOrder(product, 5);

        Order order = orderRepository.findOrderByProduct(product).orElse(null);
        assertNotNull(order);
        assertEquals(product, order.getProduct());
        assertEquals(5, order.getQty());
        assertEquals(75000.0, order.getTotal(), 0.01);
    }

    @Test
    void testGetAllOrders() {
        Product product1 = new Product(1, "Nasi Goreng", 15000.0);
        Product product2 = new Product(2, "Mie Goreng", 13000.0);

        orderService.addOrder(product1, 2);
        orderService.addOrder(product2, 3);

        HashMap<Integer, Order> allOrders = orderService.getAllOrders();
        assertNotNull(allOrders);
        assertEquals(2, allOrders.size());
    }

    @Test
    void testGetTotalQtyOrdersWithEmptyRepository() {
        int totalQty = orderService.getTotalQtyOrders();
        assertEquals(0, totalQty);
    }
}

