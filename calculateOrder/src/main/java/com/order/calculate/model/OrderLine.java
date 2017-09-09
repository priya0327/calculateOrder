package com.order.calculate.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents an orderLine which contains the {@link Item} and the quantity.
 * 
 * @author [priya]
 */
public class OrderLine
{

   private Logger logger = LoggerFactory.getLogger(Order.class);

   private Item item;
   private int quantity;

   /**
    * @param item
    *           {@link}
    * @param quantity
    *           Quantity of the items
    * @throws Exception
    *            Exception if <code>item</code> is null
    */
   public OrderLine(Item item, int quantity) throws Exception
   {
      if (item == null)
      {
         logger.error("ERROR - Item is NULL");
         throw new Exception("Item is NULL");
      }
      if (quantity <=0)
      {
         logger.warn("WARNING-Item's quantity is not valid(value must be greater then zero)");
      }else
      {  //set quantity if it is positive other wise default value i.e zero.
         this.quantity = quantity;
      }
      this.item = item;
      
   }

   /**
    * Get Item for current in OrderLine
    * @return {@link Item}
    */
   public Item getItem()
   {
      return item;
   }

   /**
    * Get quantity of Items in current OrderLine
    * @return quantity of Items in current OrderLine
    */
   public int getQuantity()
   {
      return quantity;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "OrderLine [quantity=" + quantity + ", item=" + item + "]";
   }

}
