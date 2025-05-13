package com.tns.fooddeliverysystem.application;

import java.util.Map;
import java.util.Scanner;

import com.tns.fooddeliverysystem.entities.Customer;
import com.tns.fooddeliverysystem.entities.DeliveryPerson;
import com.tns.fooddeliverysystem.entities.FoodItem;
import com.tns.fooddeliverysystem.entities.Order;
import com.tns.fooddeliverysystem.entities.Restaurant;
import com.tns.fooddeliverysystem.services.CustomerService;
import com.tns.fooddeliverysystem.services.FoodService;
import com.tns.fooddeliverysystem.services.OrderService;

public class FoodDeliverySystem {
	private static FoodService foodService = new FoodService();
	private static OrderService orderService = new OrderService();
	private static CustomerService customerService = new CustomerService();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int customerChoice, adminChoice;

		while (true) {
			System.out.println("\n1. Admin Menu");
			System.out.println("2. Customer Menu");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1: // Admin Module
				do {
					System.out.println("\nAdmin Menu:");
					System.out.println("1. Add Restaurant");
					System.out.println("2. Add Food Item to Restaurant");
					System.out.println("3. Remove Food Item from Restaurant");
					System.out.println("4. View Restaurants and Menus");
					System.out.println("5. View Orders");
					System.out.println("6. Add Delivery Person");
					System.out.println("7. Assign Delivery Person to Order");
					System.out.println("8. Exit");
					System.out.print("Choose an option: ");
					adminChoice = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					switch (adminChoice) {
					case 1:
						addRestaurant(scanner);
						break;
					case 2:
						addFoodItemToRestaurant(scanner);
						break;
					case 3:
						removeFoodItemFromRestaurant(scanner);
						break;
					case 4:
						viewRestaurantsAndMenus();
						break;
					case 5:
						viewOrders();
						break;
					case 6:
						addDeliveryPerson(scanner);
						break;
					case 7:
						assignDeliveryPersonToOrder(scanner);
						break;
					case 8:
						System.out.println("Exiting Admin Module");
						break;
					default:
						System.out.println("Invalid choice! Please try again.");
					}
				} while (adminChoice != 8);
				break;

			case 2: // Customer Module
				do {
					System.out.println("\nCustomer Menu:");
					System.out.println("1. Add Customer");
					System.out.println("2. View Food Items");
					System.out.println("3. Add Food to Cart");
					System.out.println("4. View Cart");
					System.out.println("5. Place Order");
					System.out.println("6. View Orders");
					System.out.println("7. Exit");
					System.out.print("Choose an option: ");
					customerChoice = scanner.nextInt();
					scanner.nextLine(); // Consume newline

					switch (customerChoice) {
					case 1:
						createCustomer(scanner);
						break;
					case 2:
						viewRestaurantsAndMenus();
						break;
					case 3:
						addToCart(scanner);
						break;
					case 4:
						viewCart(scanner);
						break;
					case 5:
						placeOrder(scanner);
						break;
					case 6:
						viewOrders();
						break;
					case 7:
						System.out.println("Exiting Customer Module");
						break;
					default:
						System.out.println("Invalid choice! Please try again.");
					}
				} while (customerChoice != 7);
				break;

			case 3:
				System.out.println("Thanks for using Food Delivery System!!!");
				return;

			default:
				System.out.println("Invalid choice! Please try again.");
			}
		}
	}

	private static void addRestaurant(Scanner scanner) {
		System.out.print("Enter Restaurant ID: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Restaurant Name: ");
		String name = scanner.nextLine();

		Restaurant restaurant = new Restaurant(id, name);
		foodService.addRestaurant(restaurant);
		System.out.println("Restaurant added successfully!");
	}

	private static void addFoodItemToRestaurant(Scanner scanner) {
		System.out.print("Enter Restaurant ID: ");
		int restaurantId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Food Item ID: ");
		int foodItemId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Food Item Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Food Item Price: ");
		double price = scanner.nextDouble();
		scanner.nextLine(); // Consume newline

		FoodItem foodItem = new FoodItem(foodItemId, name, price);
		foodService.addFoodItemToRestaurant(restaurantId, foodItem);
		System.out.println("Food Item added successfully!");
	}

	private static void removeFoodItemFromRestaurant(Scanner scanner) {
		System.out.print("Enter Restaurant ID: ");
		int restaurantId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Food Item ID to remove: ");
		int foodItemId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		foodService.removeFoodItemFromRestaurant(restaurantId, foodItemId);
		System.out.println("Food item removed successfully!");
	}

	private static void viewOrders() {
		System.out.println("Orders:");
		for (Order order : orderService.getAllOrders()) {
			System.out.println(order);
		}
	}

	private static void addDeliveryPerson(Scanner scanner) {
		System.out.print("Enter Delivery Person ID: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Delivery Person Name: ");
		String name = scanner.nextLine();
		System.out.print("Enter Contact No: ");
		long contactNo = scanner.nextLong();
		scanner.nextLine(); // Consume newline

		DeliveryPerson deliveryPerson = new DeliveryPerson(id, name, contactNo);
		orderService.addDeliveryPerson(deliveryPerson);
		System.out.println("Delivery person added successfully!");
	}

	private static void assignDeliveryPersonToOrder(Scanner scanner) {
		System.out.print("Enter Order ID: ");
		int orderId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Delivery Person ID to assign: ");
		int deliveryPersonId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		orderService.assignDeliveryPersonToOrder(orderId, deliveryPersonId);
		System.out.println("Delivery person assigned to order successfully!");
	}

	private static void viewRestaurantsAndMenus() {
		System.out.println("\nRestaurants and Menus:");
		for (Restaurant restaurant : foodService.getAllRestaurants()) {
			System.out.println("Restaurant ID: " + restaurant.getId() + ", Name: " + restaurant.getName());
			Map<Integer, FoodItem> menu = foodService.getMenu(restaurant.getId());
			if (menu != null && !menu.isEmpty()) {
				System.out.println("  Menu:");
				for (FoodItem foodItem : menu.values()) {
					System.out.println("    ID: " + foodItem.getId() + ", Name: " + foodItem.getName() + ", Price: Rs. "
							+ foodItem.getPrice());
				}
			} else {
				System.out.println("  No menu items yet.");
			}
			System.out.println("--------------------");
		}
	}

	private static void createCustomer(Scanner scanner) {
		System.out.print("Enter Customer ID: ");
		int userId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Username: ");
		String username = scanner.nextLine();
		System.out.print("Enter Contact No: ");
		long contactNo = scanner.nextLong();
		scanner.nextLine(); // Consume newline

		Customer customer = new Customer(userId, username, contactNo);
		customerService.addCustomer(customer);
		System.out.println("Customer created successfully!");
	}

	private static void addToCart(Scanner scanner) {
		System.out.print("Enter Customer ID: ");
		int customerId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			System.out.println("Customer not found!");
			return;
		}

		System.out.print("Enter Restaurant ID: ");
		int restaurantId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		System.out.print("Enter Food Item ID to add to cart: ");
		int foodItemId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		FoodItem foodItem = null;
		for (Restaurant restaurant : foodService.getAllRestaurants()) {
			if (restaurant.getId() == restaurantId) {
				foodItem = foodService.getFoodItem(restaurantId, foodItemId);
				break;
			}
		}

		if (foodItem == null) {
			System.out.println("Food item not found in the restaurant!");
			return;
		}

		System.out.print("Enter Quantity: ");
		int quantity = scanner.nextInt();
		scanner.nextLine(); // Consume newline

		customerService.getCart(customer).addItem(foodItem, quantity);
		System.out.println("Food item added to cart!");
	}

	private static void viewCart(Scanner scanner) {
		System.out.print("Enter Customer ID: ");
		int customerId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			System.out.println("Customer not found!");
			return;
		}

		System.out.println("Cart:");
		Map<FoodItem, Integer> items = customerService.getCart(customer).getItems();
		if (items.isEmpty()) {
			System.out.println("Your cart is empty.");
		} else {
			double totalCost = 0.0;
			for (Map.Entry<FoodItem, Integer> entry : items.entrySet()) {
				FoodItem foodItem = entry.getKey();
				int quantity = entry.getValue();
				double cost = foodItem.getPrice() * quantity;
				System.out.println(
						"Food Item: " + foodItem.getName() + ", Quantity: " + quantity + ", Cost: Rs. " + cost);
				totalCost += cost;
			}
			System.out.println("Total Cost: Rs. " + totalCost);
		}
	}

	private static void placeOrder(Scanner scanner) {
		System.out.print("Enter Customer ID: ");
		int customerId = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		Customer customer = customerService.getCustomer(customerId);
		if (customer == null) {
			System.out.println("Customer not found!");
			return;
		}

		Order order = new Order(orderService.getAllOrders().size() + 1, customer);
		Map<FoodItem, Integer> cartItems = customerService.getCart(customer).getItems();

		if (cartItems.isEmpty()) {
			System.out.println("Your cart is empty. Add items to your cart before placing an order.");
			return;
		}

		for (Map.Entry<FoodItem, Integer> entry : cartItems.entrySet()) {
			order.addItem(entry.getKey(), entry.getValue());
		}

		orderService.placeOrder(order);
		customerService.clearCart(customer);
		System.out.println("Order placed successfully! Your Order ID is: " + order.getOrderId());
	}
}