package com.order.calculate.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents Order which contains in orderLines List. 
 * @author [priya]
 *
 */
public class Order {
   
   Logger log = LoggerFactory.getLogger(Order.class);

	private List<OrderLine> orderLinesList;
	
	/**
	 * Constructor for Order.
	 * This Constructor responsible to initialize orderLines
	 */
	public Order() {	
		this.orderLinesList = new ArrayList<OrderLine>();
	}

	/**
	 * Add new OrderLine to Order's list.
	 * @param orderLine {@link OrderLine}
	 * @throws Exception {@link IllegalArgumentException} if 'orderLine' value is Null.
	 */
	public void add(OrderLine orderLine) throws Exception {
		if (orderLine == null) {
		   log.error("Order is NULL");
			throw new IllegalArgumentException("Order is NULL");
		}		
		orderLinesList.add(orderLine);
	}

	/**
	 * This method responsible for getting number of {@link OrderLine} in current Order.
	 * @return number of {@link OrderLine}
	 */
	public int size() {
		return orderLinesList.size();
	}

	/**
	 * Get {@link OrderLine} based on index from orderLinesList
	 * @param index particular index position in orderLinesList.
	 * @return {@link OrderLine}
	 */
	public OrderLine getOrderLine(int index) {
		return orderLinesList.get(index);
	}

	/**
	 * Clear orderLinesList contain objects.
	 */
	public void clear() {
		this.orderLinesList.clear();
	}

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Order [orderLinesList=" + orderLinesList + "]";
   }
	
}
