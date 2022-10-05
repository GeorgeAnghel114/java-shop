package com.codecool.shop.dao;

import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;

import java.util.List;

public interface CartDao {
    void addToCart(LineItem itemToAdd);
    void removeItem(LineItem itemToRemove);
    void decreaseQuantity(LineItem item);
    void clearCart();
    int sizeOfCart();
    List<LineItem> getAll();
}
