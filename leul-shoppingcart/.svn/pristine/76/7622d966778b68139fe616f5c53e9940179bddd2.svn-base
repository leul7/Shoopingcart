/*
 * TCSS 305 – Winter 2018 Assignment 2.
 */
package model;

import java.util.Objects;

/**
 * Creates an item order class.
 * 
 * @author Leul Amare
 * @version 01/24/2018
 *
 */

public final class ItemOrder {
    
    // instance fields 
    
    /**
     * The product item.
     */
    private final Item myItem;
    
    /**
     * The product item.
     */
    private final int myQuantity;
    
    /**
     * {@inheritDoc}
     * Constructor that takes a name and a price as arguments.
     * 
     * @param theItem The product item to assign to this order.
     * @param theQuantity The Quantity to assign to this order
     */

    public ItemOrder(final Item theItem, final int theQuantity) {
        
        if (theQuantity < 0) {
            throw new IllegalArgumentException("theQuantity can't be < 0");
            
        }
        myItem = Objects.requireNonNull(theItem, "myItem can't be a null");
        myQuantity = theQuantity;
    }

    /**
     * 
     * @return a reference to the Item in this ItemOrder.
     */
    public Item getItem() {
        
        return myItem;
    }
    
    /**
     * 
     * @return the quantity for this ItemOrder.
     */
    public int getQuantity() {
        
        return myQuantity;
    }

    /**
     * {@inheritDoc}
     * 
     * Returns a string representation of this ItemOrder.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(22);
        
        sb.append("Item: ");
        sb.append(myItem);
        sb.append(", quantity is: ");
        sb.append(myQuantity);

        return sb.toString();
    }

}

