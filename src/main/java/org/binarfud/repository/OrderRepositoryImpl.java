package org.binarfud.repository;

import org.binarfud.model.Order;
import org.binarfud.model.Product;

import java.util.HashMap;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    private final HashMap<Integer, Order> orders = new HashMap<>();
    private int nextOrderId = 1;

    @Override
    public void addOrder(Order order) {
        order.setId(nextOrderId++);
        orders.put(order.getId(), order);
    }

    @Override
    public HashMap<Integer, Order> getAllOrders() {
        return orders;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public Optional<Order> findOrderByProduct(Product product) {
        int productId = product.getId();
        return Optional.ofNullable(orders.get(productId));
    }
}
