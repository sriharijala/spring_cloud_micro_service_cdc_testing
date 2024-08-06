package com.saggu.cdc;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Service
public class OrderService {

    static Map<String, Order> orders = Map.of(
            "1", new Order("1", "Sony TV", 500.00, 1),
            "2", new Order("2", "Samsung TV", 480.00, 1),
            "3", new Order("3", "Washing Machine", 1500.0, 1)
    );

    public Order getOrder(String orderId) {
       return orders.get(orderId);
    }

    @Data
    @AllArgsConstructor
    static class Order {
    	
    	 private String orderId;
         private String itemName;
         private double price;
         private int units;
 		public Order(String orderId, String itemName, double price, int units) {
 			super();
 			this.orderId = orderId;
 			this.itemName = itemName;
 			this.price = price;
 			this.units = units;
 		}
 		public String getOrderId() {
 			return orderId;
 		}
 		public void setOrderId(String orderId) {
 			this.orderId = orderId;
 		}
 		public String getItemName() {
 			return itemName;
 		}
 		public void setItemName(String itemName) {
 			this.itemName = itemName;
 		}
 		public double getPrice() {
 			return price;
 		}
 		public void setPrice(double price) {
 			this.price = price;
 		}
 		public int getUnits() {
 			return units;
 		}
 		public void setUnits(int units) {
 			this.units = units;
 		}
         
		
		
    }
}
