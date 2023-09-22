package org.binarfud.service;

import lombok.RequiredArgsConstructor;
import org.binarfud.model.Order;
import org.binarfud.model.Product;
import org.binarfud.repository.OrderRepository;

import java.util.HashMap;

@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public void addOrder(Product product, int quantity) {
        if (quantity > 0) {
            Order existingOrder = orderRepository.findOrderByProduct(product).orElse(null);
            if (existingOrder != null) {
                int newQuantity = existingOrder.getQty() + quantity;
                double newTotal = product.getPrice() * newQuantity;
                existingOrder.setQty(newQuantity);
                existingOrder.setTotal(newTotal);
            } else {
                double total = product.getPrice() * quantity;
                orderRepository.addOrder(Order.builder().product(product).qty(quantity).total(total).build());
            }
        }
    }


    @Override
    public HashMap<Integer, Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Double getTotalPriceOrders() {
        return orderRepository.getAllOrders().values().stream()
                .mapToDouble(Order::getTotal)
                .sum();
    }

    @Override
    public Order getOrdertById(int id) {
        return orderRepository.getOrderById(id).orElse(null);
    }

    @Override
    public Integer getTotalQtyOrders() {
        return orderRepository.getAllOrders().values().stream()
                .mapToInt(Order::getQty)
                .sum();
    }
}
