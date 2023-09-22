package org.binarfud;

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

public class BinarFudApplication {
    public static void main(String[] args) {
        ProductRepository productRepository = new ProductRepositoryImpl();
        OrderRepository orderRepository = new OrderRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(orderRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        DisplayUtil displayUtil = new DisplayUtil();

        BinarFudController binarFudController = new BinarFudController(productService, orderService, displayUtil);
        binarFudController.init();
        binarFudController.index();
    }
}
