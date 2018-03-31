/*
 * TCSS 305 – Winter 2018 Assignment 2
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A shopping cart class stores information about the customer's overall
 * purchase.
 * 
 * @author Leul Amare
 * @version 01/24/2018
 *
 */
public class ShoppingCart {

    // constants

    /**
     * A 10 percentage value for the discount item.
     */
    private static final BigDecimal MEMEBERSHIP_DISCOUNT_RATE = new BigDecimal("0.10");

    // instance fields

    /** The shopping cart. */
    private final Map<Item, Integer> myShoppingCart;

    /** The membership status. */
    private boolean myMembership;

    // constructors

    /**
     * constructor that creates an empty shopping cart.
     */
    public ShoppingCart() {
        myShoppingCart = new HashMap<Item, Integer>();
        myMembership = false;

    }

    /**
     * Adds an order to the shopping cart, replacing any previous order for an
     * equivalent item with the new order.
     * 
     * @param theOrder The ItemOrder parameter top add to shopping cart.
     */
    public void add(final ItemOrder theOrder) {
        Objects.requireNonNull(theOrder, " theOrder can't be null");
        myShoppingCart.put(theOrder.getItem(), theOrder.getQuantity());

    }

    /**
     * Sets whether or not the customer for this shopping cart has a store
     * membership.
     * 
     * @param theMembership True means the customer has membership, False means the
     * customer don't have a membership.
     */

    public void setMembership(final boolean theMembership) {
        myMembership = theMembership;

    }

    /**
     * Calculates the shopping cart total cost.
     * 
     * @return Returns the total cost of this order as BigDecimal.
     */

    public BigDecimal calculateTotal() {

        BigDecimal orderTotal = BigDecimal.ZERO;

        for (final Item i : myShoppingCart.keySet()) {
            if (i.isBulk()) {

                final int lump = myShoppingCart.get(i) / i.getBulkQuantity();
                final BigDecimal bulkTotal = 
                                i.getBulkPrice().multiply(new BigDecimal(lump));

                final int leftOver = myShoppingCart.get(i) % i.getBulkQuantity();
                final BigDecimal leftOverTotal =
                                i.getPrice().multiply(new BigDecimal(leftOver));
                orderTotal = orderTotal.add(bulkTotal.add(leftOverTotal));
            } else {
                final BigDecimal subTotal =
                      i.getPrice().multiply(new BigDecimal(myShoppingCart.get(i)));

                orderTotal = orderTotal.add(subTotal);
            }

        }

        // if the customers has a membership, they can get a discounted rate.

        if (myMembership && orderTotal.compareTo(new BigDecimal("20")) > 0) {

            final BigDecimal discount = MEMEBERSHIP_DISCOUNT_RATE.multiply(orderTotal);
            orderTotal = orderTotal.subtract(discount);

        }

        return orderTotal.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Removes all orders from the cart.
     */
    public void clear() {

        myShoppingCart.clear();

    }

    /**
     * {@inheritDoc}
     * 
     * A string representation of this ShoppingCart.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(myShoppingCart);

        return sb.toString();
    }

}
