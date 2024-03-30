package com.food.dao;

import java.util.List;
import com.food.Restaurant;

public interface RestaurentDAO {
    void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(int restaurantId);
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
    List<Restaurant> getAllRestaurants();
}
