package com.awalker;

import org.joda.time.DateTime;

import java.util.ArrayList;

/**
 * @author Alexander Walker on 03/12/2015.
 */
public class StockDataRow {

    private String stockSymbolData;
    private String typeData;
    private int lastDividendData;
    private float fixedDividendData;
    private int parValueData;
    private double tickerPrice;
    private int sharesAvailable;
    private ArrayList<Trade> tradeRecord;

    public StockDataRow() {
        tradeRecord = new ArrayList<>();
        setTickerPrice(20.0);
        setSharesAvailable(1000);
    }

    public String getStockSymbolData() {
        return stockSymbolData;
    }

    public String getTypeData() {
        return typeData;
    }

    public int getLastDividendData() {
        return lastDividendData;
    }

    public float getFixedDividendData() {
        return fixedDividendData;
    }

    public int getParValueData() {
        return parValueData;
    }

    public void setStockSymbolData(String stockSymbolData) {
        this.stockSymbolData = stockSymbolData;
    }

    public void setTypeData(String typeData) {
        this.typeData = typeData;
    }

    public void setLastDividendData(int lastDividendData) {
        this.lastDividendData = lastDividendData;
    }

    public void setFixedDividendData(float fixedDividendData) {
        this.fixedDividendData = fixedDividendData;
    }

    public void setParValueData(int parValueData) {
        this.parValueData = parValueData;
    }

    public double getTickerPrice() {
        return tickerPrice;
    }

    public void setTickerPrice(double tickerPrice) {
        this.tickerPrice = tickerPrice;
    }

    public double calculateDividendYield() {
        double yield = 0.0;
        switch (typeData){
            case "Common":
                yield = getDividend() / getTickerPrice();
                break;
            case "Preferred":
                yield = getDividend() / getTickerPrice();
                break;
        }
        
        return yield;
    }

    public double getDividend(){
        double dividend = 0.0;
        switch (typeData){
            case "Common":
                dividend = lastDividendData;
                break;
            case "Preferred":
                dividend = fixedDividendData*parValueData;
                break;
        }
        return dividend;
    }

    public double calculatePERatio() {
        //protect diving by 0
        double dividend = getDividend();
        if (dividend > 0) {
            return tickerPrice / dividend;
        }
        else{
            return 0;
        }
    }

    public void recordTrade(Trade trade) {
        getTradeRecord().add(trade);
        if (trade.isWasABuyTrade()){
            //purchase stock
            setSharesAvailable(getSharesAvailable() - trade.getQuantity());
        }
        else{
            //sell stock
            setSharesAvailable(getSharesAvailable() + trade.getQuantity());
        }
    }

    /**
     * only use last 15 min trades for calculating price
     * @return the calculated stock trade price
     */
    public double calculateStockPrice() {
        double stockPrice;
        double sumOfTrades = 0;
        int quantity = 0;
        for (Trade trade : getTradeRecord()) {
            if (trade.getTimeStamp().isAfter(DateTime.now().minusMinutes(15))) {
                sumOfTrades += trade.getPrice() * trade.getQuantity();
                quantity += trade.getQuantity();
            }
        }

        if (quantity > 0)
            stockPrice = sumOfTrades / quantity;
        else
            stockPrice = 0;

        return stockPrice;
    }


    public int getSharesAvailable() {
        return sharesAvailable;
    }

    public void setSharesAvailable(int sharesAvailable) {
        this.sharesAvailable = sharesAvailable;
    }

    public ArrayList<Trade> getTradeRecord() {
        return tradeRecord;
    }
}
