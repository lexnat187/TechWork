package com.awalker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * @author Alexander Walker on 02/12/2015.
 */
public class CSVHandler {

    private static final int STOCK_SYMBOL = 0;
    private static final int TYPE = 1;
    private static final int LAST_DIVIDEND = 2;
    private static final int FIXED_DIVIDEND = 3;
    private static final int PAR_VALUE = 4;

    public static StockData parseCSVInput(File inputFile) throws FileNotFoundException {
        StockData stockData = new StockData();

        FileReader fileReader = new FileReader(inputFile);

        CSVParser parser;
        try {
            parser = new CSVParser(fileReader, CSVFormat.RFC4180);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        int rowCount = 0;

        Map<String, Integer> header = parser.getHeaderMap();


        for (CSVRecord csvRecord : parser) {
            // test row and store variable
            int iHeaderSize = csvRecord.size();
            boolean bIsReferenceData = false;
            if (rowCount == 0) {
                //header row
            } else {
                int currentIndex = 0;
                StockDataRow row =  new StockDataRow();

                for (String value : csvRecord) {
                    if (value == null || value.length() == 0) {
                        currentIndex++;
                        continue;
                    }

                    if (currentIndex < iHeaderSize) {
                        switch (currentIndex){
                            case STOCK_SYMBOL:
                                row.setStockSymbolData(value);
                                break;
                            case TYPE:
                                row.setTypeData(value);
                                break;
                            case LAST_DIVIDEND:
                                try{
                                    int lastDividend = Integer.parseInt(value);
                                    row.setLastDividendData(lastDividend);
                                } catch (NumberFormatException e) {
                                    // do nothing and pass the original value through
                                }
                                break;
                            case FIXED_DIVIDEND:
                                try{
                                    float fixedDividend = Float.parseFloat(value);
                                    row.setFixedDividendData(fixedDividend);
                                } catch (NumberFormatException e) {
                                    // do nothing and pass the original value through
                                }
                                break;
                            case PAR_VALUE:
                                try{
                                    int parValue = Integer.parseInt(value);
                                    row.setParValueData(parValue);
                                } catch (NumberFormatException e) {
                                    // do nothing and pass the original value through
                                }
                                break;
                        }
                    }
                    currentIndex++;
                }
                stockData.addDataRow(row);
            }
            rowCount++;
        }
        return stockData;
    }
}
