package com.order.calculate.Interface;

import java.util.Map;

import com.order.calculate.model.Order;

/**
 * @author [priya]
 *
 */
public interface Calculator {
	
	
	/**
	 * Receives a collection of orders. For each order, iterates on the order
	 * lines and calculate the total price which is the item's
	 * 
	 * <pre>
	 * (price + taxes) * quantity
	 * </pre>
	 * 
	 * For each order, print the total Sales Tax paid and Total price without
	 * taxes for this order.
	 * 
	 * @param orderMap
	 *            Collection of Orders.
	 * @return return grand total of complete Order price with tax.
	 */
	public double calculate(Map<String, Order> orderMap);

	/**
	 * Format the provided value to nearest(up side) 2 decimal value.
	 * 
	 * @param value
	 *            target value to be formated.
	 * @return formated value as result.
	 */
	public double format(double value);

}
