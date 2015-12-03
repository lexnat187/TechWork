package com.awalker;

import org.joda.time.DateTime;

import java.io.*;
import java.net.URL;

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
     * @param inputPath path to a csv specifying the input data
     */
    public SuperSimpleStocks(String inputPath){
        StockData stockData = processInput(inputPath);

        if (stockData != null){
            double stockPricesSum = 0.0;
            for (StockDataRow row : stockData.getDataRows()){
                boolean buy = true;
                int amount = 300;
                double price = 20.0;

                System.out.println("Stock: " + row.getStockSymbolData());
                System.out.println("Dividend yield: " + row.calculateDividendYield());
                System.out.println("P/E ratio: " + row.calculatePERatio());

                if (buy){
                    if (row.getSharesAvailable() > amount){
                        Trade newTrade = new Trade(DateTime.now(), amount, true, price);
                        row.recordTrade(newTrade);
                    }
                }
                else{
                    Trade newTrade = new Trade(DateTime.now(), amount, true, price);
                    row.recordTrade(newTrade);
                }

                double rowStockPrice = row.calculateStockPrice();
                stockPricesSum += rowStockPrice;
                System.out.println("Stock price: " + rowStockPrice);
            }

            double GBCEALLShareIndex = Math.pow(stockPricesSum, 1.0 / stockData.getDataRows().size());
            System.out.println("GBCE All Share Index: " + GBCEALLShareIndex);
        }
    }

    private StockData processInput(String inputPath) {
        File inputFile = null;
        if (inputPath.isEmpty()){
            URL url = this.getClass().getClassLoader().getResource("com.awalker.data/sampledata.csv");

            try {
                assert url != null;
                if (url.toString().startsWith("jar:")) {
                        inputFile = copyResourceOutofJARtoTEMP("sampledata", ".csv");
                } else {
                    inputFile = new File(url.getFile());
                }
                assert inputFile != null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            inputFile = new File(inputPath);
        }
        assert inputFile != null;

        // parse input file
        try {
           return CSVHandler.parseCSVInput(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File copyResourceOutofJARtoTEMP(String sFileName, String sFileExtension) throws IOException {
        InputStream input = getClass().getResourceAsStream("/com/awalker/data/" + sFileName + sFileExtension);
        File file = File.createTempFile(sFileName, sFileExtension);
        File actualFile = new File(file.getParent() + "/" + sFileName + sFileExtension);

        OutputStream out = new FileOutputStream(actualFile);
        int read;
        byte[] bytes = new byte[1024];

        while ((read = input.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        file.deleteOnExit();
        actualFile.deleteOnExit();
        return actualFile;
    }

    public static void main(String[] args){
        String inputPath = "";
        if (args.length == 1){
            inputPath = args[0];
        }
        new SuperSimpleStocks(inputPath);
    }
}
