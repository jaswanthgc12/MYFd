package com.food;

import java.util.HashMap;
import java.util.Map;


public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addCartItem(CartItem newItem) {
        int itemId = newItem.getItemId();
        if (items.containsKey(itemId)) {
            CartItem existingItem = items.get(itemId);
            existingItem.setQuantity(existingItem.getQuantity() + newItem.getQuantity());
        } else {
        	items.put(itemId, newItem);
        }
    }

    public void updateCart(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                items.remove(itemId);
            } else {
                CartItem item = items.get(itemId);
                if (item != null) {
                    item.setQuantity(quantity);
                } else {
                    System.out.println("Error: CartItem with itemId " + itemId + " not found.");
                }
            }
        } else {
            System.out.println("Error: CartItem with itemId " + itemId + " not found.");
        }
    }


    public void removeCart(int itemId) {
        if (items.containsKey(itemId)) {
            items.remove(itemId);
        }
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }
    public void clear() {
    	items.clear();
    }
}
