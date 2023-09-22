package controller;

import org.binarfud.controller.BinarFudController;
import org.binarfud.repository.OrderRepository;
import org.binarfud.repository.OrderRepositoryImpl;
import org.binarfud.repository.ProductRepository;
import org.binarfud.repository.ProductRepositoryImpl;
import org.binarfud.service.OrderService;
import org.binarfud.service.OrderServiceImpl;
import org.binarfud.service.ProductService;
import org.binarfud.service.ProductServiceImpl;
import org.binarfud.util.common.DisplayUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BinarFudControllerTest {
    private InputStream inputStream = System.in;
    private  PrintStream printStream = System.out;
    private BinarFudController binarFudController;

    @BeforeEach
    void init() {
        ProductRepository productRepository = new ProductRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        DisplayUtil displayUtil = new DisplayUtil();

        binarFudController = new BinarFudController(productService, orderService, displayUtil);
        binarFudController.init();
    }

    @Test
    void testIndexExit() {
        String input = "1\n3\ny";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        assertDoesNotThrow(() -> binarFudController.index());
    }
}
