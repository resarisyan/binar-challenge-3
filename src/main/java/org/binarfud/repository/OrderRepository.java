package org.binarfud.repository;

import org.binarfud.model.Order;
import org.binarfud.model.Product;

import java.util.HashMap;
import java.util.Optional;

public interface OrderRepository {
    void addOrder(Order order);

    Optional<Order> getOrderById(int id);

    HashMap<Integer, Order> getAllOrders();

    Optional<Order> findOrderByProduct(Product product);
}
