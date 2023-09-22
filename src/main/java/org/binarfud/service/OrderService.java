package org.binarfud.service;

import org.binarfud.model.Order;
import org.binarfud.model.Product;

import java.util.HashMap;

public interface OrderService {
    void addOrder(Product product, int qty);

    HashMap<Integer, Order> getAllOrders();

    Double getTotalPriceOrders();

    Integer getTotalQtyOrders();

    Order getOrdertById(int id);
}
