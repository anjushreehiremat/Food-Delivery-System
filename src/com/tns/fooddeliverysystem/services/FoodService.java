package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class FoodService {
    private List<Restaurant> restaurants = new ArrayList<>();

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public List<FoodItem> getAllFoodItems() {
        List<FoodItem> allFoodItems = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            allFoodItems.addAll(restaurant.getMenu());
        }
        return allFoodItems;
    }

    public void addFoodItemToRestaurant(int restaurantId, FoodItem foodItem) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                restaurant.addFoodItem(foodItem);
                return;
            }
        }
    }

    public void removeFoodItemFromRestaurant(int restaurantId, int foodItemId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                restaurant.removeFoodItem(foodItemId);
                return;
            }
        }
    }

    public Map<Integer, FoodItem> getMenu(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == restaurantId) {
                List<FoodItem> foodItems = restaurant.getMenu();
                Map<Integer, FoodItem> menuMap = new HashMap<>();
                for (FoodItem foodItem : foodItems) {
                    menuMap.put(foodItem.getId(), foodItem);
                }
                return menuMap;
            }
        }
        return null;
    }

    public FoodItem getFoodItem(int restaurantId, int foodItemId) {
        Map<Integer, FoodItem> menu = getMenu(restaurantId);
        if (menu != null && menu.containsKey(foodItemId)) {
            return menu.get(foodItemId);
        }
        return null;
    }
}