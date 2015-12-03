package com.awalker;

import org.joda.time.DateTime;

/**
 * @author Alexander Walker on 03/12/2015.
 */
public class Trade {

    private DateTime timeStamp;
    private int quantity;
    private boolean wasABuyTrade;
    private double price;

    public Trade(DateTime timeStamp, int quantity, boolean wasABuyTrade, double price){
        this.timeStamp = timeStamp;
        this.quantity = quantity;
        this.wasABuyTrade = wasABuyTrade;
        this.price = price;
    }

    public DateTime getTimeStamp() {
        return timeStamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isWasABuyTrade() {
        return wasABuyTrade;
    }

    public double getPrice() {
        return price;
    }
}
