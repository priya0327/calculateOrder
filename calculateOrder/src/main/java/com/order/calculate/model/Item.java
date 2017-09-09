package com.order.calculate.model;

/**
 * This Class represents an Item. Contains a price and a description value of Item.
 * 
 * @author [priya]
 */
public class Item
{

   private String description;
   private float price;

   /**
    * Constructor for Item This Constructor responsible to initialize description,price.
    * 
    * @param description
    *           description of Item
    * @param price
    *           price of Item
    */
   public Item(String description, float price)
   {
      this.description = description;
      this.price = price;
   }

   /**
    * Get Item Description value
    * 
    * @return description of Item
    */
   public String getDescription()
   {
      if(description==null)
      {
         description="";
      }
      return description;
   }

   /**
    * Get Item price value
    * 
    * @return price of Item
    */
   public float getPrice()
   {
      return price;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      return "Item [description=" + description + ", price=" + price + "]";
   }
   
}
