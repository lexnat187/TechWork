package com.awalker;

/**
 * @author Alexander Walker on 02/12/2015.
 */
public class SuperSimpleStocks {

    /**
     * Requirements

     1. Provide working source code that will :-

     a. For a given stock,

     i. calculate the dividend yield

     ii. calculate the P/E Ratio

     iii. record a trade, with timestamp, quantity of shares, buy or sell indicator and price

     iv. Calculate Stock Price based on trades recorded in past 15 minutes

     b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks

     Constraints & Notes

     1. Written in one of these languages:

     · Java, C#, C++, Python

     2. No database or GUI is required, all data need only be held in memory

     3. Formulas and data provided are simplified representations for the purpose of this exercise

     * @param inputPath


     */
    public SuperSimpleStocks(String inputPath){


    }

    public static void main(String[] args){
        String inputPath = "";
        if (args.length == 1){
            inputPath = args[0];
        }
        new SuperSimpleStocks(inputPath);
    }
}
