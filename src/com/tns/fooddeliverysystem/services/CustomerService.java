package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.Cart;
import com.tns.fooddeliverysystem.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    // retrieve Customer by ID
    public Customer getCustomer(int userId) {
        return customerList.stream()
                           .filter(customer -> customer.getUserId() == userId)
                           .findFirst()
                           .orElse(null);
    }

    public List<Customer> getCustomers() {
        return customerList;
    }

    public Cart getCart(Customer customer) {
        return customer.getCart();
    }

    public void clearCart(Customer customer) {
        customer.getCart().getItems().clear();
    }
}