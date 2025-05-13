package com.tns.fooddeliverysystem.services;

import com.tns.fooddeliverysystem.entities.DeliveryPerson;
import com.tns.fooddeliverysystem.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Order> orders = new ArrayList<>();
    private List<DeliveryPerson> deliveryPersons = new ArrayList<>();

    public void placeOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void addDeliveryPerson(DeliveryPerson deliveryPerson) {
        deliveryPersons.add(deliveryPerson);
    }

    public List<DeliveryPerson> getAllDeliveryPersons() {
        return deliveryPersons;
    }

    public void assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                for (DeliveryPerson deliveryPerson : deliveryPersons) {
                    if (deliveryPerson.getId() == deliveryPersonId) {
                        order.setDeliveryPerson(deliveryPerson);
                        return;
                    }
                }
            }
        }
    }

    public DeliveryPerson getDeliveryPerson(int deliveryPersonId) {
        for (DeliveryPerson deliveryPerson : deliveryPersons) {
            if (deliveryPerson.getId() == deliveryPersonId) {
                return deliveryPerson;
            }
        }
        return null;
    }
}