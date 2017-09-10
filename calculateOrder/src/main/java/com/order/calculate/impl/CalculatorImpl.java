package com.order.calculate.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.order.calculate.Interface.Calculator;
import com.order.calculate.model.Item;
import com.order.calculate.model.Order;
import com.order.calculate.model.OrderLine;

/**
 * One of the Implementation of  Calculator contract.
 * Usage : calculating total price of all Orders.
 * 
 * @author [priya]
 *
 */
public class CalculatorImpl implements Calculator{
   
   Logger logger = LoggerFactory.getLogger(CalculatorImpl.class);
    
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
   public double calculate(Map<String, Order> orderMap)
   {
      double grandTotal = 0;
      double grandTotalTax = 0;
      double currOrderTotalTax = 0;
      double currOrderTotalPrice = 0;
      double currItemTax = 0;
      double currItemTotalPrice = 0;

      // Iterate through the orders
      for (Map.Entry<String, Order> entry : orderMap.entrySet())
      {
         logger.info("Order: " + entry.getKey());
         Order orderObj = entry.getValue();

         // Iterate through the items in the order
         for (int i = 0; i < orderObj.size(); i++)
         {
            OrderLine orderLineObj = orderObj.getOrderLine(i);
            Item currItemObj = orderLineObj.getItem();            
            int currQuantityVal = orderLineObj.getQuantity();
            double currItemPrice = currItemObj.getPrice();
            String currItemDesc = currItemObj.getDescription();

            // Calculate the taxes  
            if (currItemObj.getDescription().toLowerCase().contains("import"))
            {
               currItemTax = currItemPrice * 0.15; // Extra 5% tax on imported items            
               logger.trace("Imported Item :" + currItemDesc + " Tax:" + format(currItemTax));             
            }
            else
            {
               currItemTax = currItemPrice * 0.10;
               logger.trace("Non-imported Item :" + currItemDesc + " Tax:" + format(currItemTax));
            }

            // Calculate the total price
            currItemTotalPrice = currItemPrice + currItemTax;
            // Print out the one item total price        
            logger.info(currItemDesc + ": " + format(currItemTotalPrice));

            //Total Order tax based on Quantity 
            currOrderTotalTax += currItemTax * currQuantityVal;
            logger.trace("Item(Quantity :" + currQuantityVal + ") Tax:" + format(currOrderTotalTax));
          //Total Order price based on Quantity 
            currOrderTotalPrice += currItemPrice * currQuantityVal;//with out tax
            logger.trace("Item(Quantity :" + currQuantityVal + ") Tax:" + format(currOrderTotalPrice));
         }

         // Print out the total taxes
         logger.info("Complete OrderLine Sales Tax:" + format(currOrderTotalTax));

        
         grandTotal += currOrderTotalPrice;//with out tax
         grandTotalTax += currOrderTotalTax;
      }
      
      // Print out the total amount with out tax        
      logger.info("Complete OrderLine Items Total Price(with out tax) ##: " + format(currOrderTotalPrice));
      
      //Total Order tax 
      logger.trace("All Orders Sales Tax:" + format(grandTotalTax));      
      //with tax
      logger.trace("Sum of orders (with tax): " + format(grandTotal + grandTotalTax));
      //with out tax
      logger.trace("Sum of orders(with out tax): " + format(grandTotal));
      
      return format(currOrderTotalPrice);

   }
   
   /**
    * Format the provided value to nearest(up side) 2 decimal value.
    * @param value target value to be formated.
    * @return formated value as result.
    */
   public double format(double value) {      
      return Math.ceil(value*100)/100;
   }
  
}


