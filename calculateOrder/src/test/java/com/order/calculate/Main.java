
package com.order.calculate;

import java.util.LinkedHashMap;
import java.util.Map;

import com.order.calculate.impl.CalculatorImpl;
import com.order.calculate.model.Item;
import com.order.calculate.model.Order;
import com.order.calculate.model.OrderLine;

/**
 * Please Use CalculatorTest.java to test application.
 * This class is alternative way to test Happy path.
 * 
 * @author priya
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {

		Map<String, Order> orderMap = new LinkedHashMap<String, Order>();

		Order order = new Order();		

		order.add(new OrderLine(new Item("book", (float) 12.49), 1));
		order.add(new OrderLine(new Item("music CD", (float) 14.99), 1));
		order.add(new OrderLine(new Item("chocolate bar", (float) 0.85), 1));

		orderMap.put("Order 1", order);

		//Create new Order
		order=new Order();

		order.add(new OrderLine(new Item("imported box of chocolate", 10), 1));
		order.add(new OrderLine(new Item("imported bottle of perfume", (float) 47.50), 1));

		orderMap.put("Order 2", order);

		//Create new Order
		order=new Order();

		order.add(new OrderLine(new Item("Imported bottle of perfume", (float) 27.99), 1));
		order.add(new OrderLine(new Item("bottle of perfume", (float) 18.99), 1));
		order.add(new OrderLine(new Item("packet of headache pills", (float) 9.75), 1));
		order.add(new OrderLine(new Item("box of importd chocolates", (float) 11.25), 1));

		orderMap.put("Order 3", order);

		new CalculatorImpl().calculate(orderMap);

	}
}

