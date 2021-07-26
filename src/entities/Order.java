package entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entities.enun.OrderStatus;

public class Order {
	
		private static SimpleDateFormat birthFormat = new SimpleDateFormat("dd/MM/yyyy");
		private static SimpleDateFormat momentFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		private Date moment;
		private OrderStatus status;
		
		private Client client;
		private List<OrderItem> items = new ArrayList<OrderItem>();
		
		public Order() {			
		}
		
		public Order(Date moment, OrderStatus status, Client client) {
			this.moment = moment;
			this.status = status;
			this.client = client;
		}

		public Date getMoment() {
			return moment;
		}

		public void setMoment(Date moment) {
			this.moment = moment;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

		public Client getClient() {
			return client;
		}

		public void setClient(Client client) {
			this.client = client;
		}

		public List<OrderItem> getItems() {
			return items;
		}
		
		public void addItem(OrderItem item) {
			items.add(item);
		}
		
		public void removeItem(OrderItem item) {
			items.remove(item);
		}
		
		public Double total() {
			Double sum = 0.0;
			for (OrderItem x : items) {
				sum += x.subTotal();
			}
			return sum;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("Order moment: ");
			sb.append(momentFormat.format(moment) + "\n");
			sb.append("Order status: ");
			sb.append(status + "\n");
			sb.append("Client: ");
			sb.append(client.getName() + "(");
			sb.append(birthFormat.format(client.getBirthDate()) + ") - ");
			sb.append(client.getEmail() + "\n");
			sb.append("Order items: \n");
			for (OrderItem x : items) {
				sb.append(x.getProduct().getName() + ", $");
				sb.append(String.format("%.2f", x.getPrice()) + ", Quantity: ");
				sb.append(x.getQuantity() + ", Subtotal: $");
				sb.append(String.format("%.2f\n", x.subTotal()));
			}
			sb.append("Total Price: $");
			Double total = total();
			sb.append(String.format("%.2f\n", total));
			return sb.toString();
		}
}
