package com.awalker;

import java.util.ArrayList;

/**
 * @author Alexander Walker on 02/12/2015.
 */
public class StockData {

    private ArrayList<StockDataRow> dataRows;

    public StockData(){
        dataRows = new ArrayList<>();
    }

    /**
     * //Stock Symbol,Type,Last Dividend,Fixed Dividend,Par Value
     * GIN,Preferred,8,2%,100
     */
    public void addDataRow(StockDataRow row) {
        getDataRows().add(row);
    }


    public ArrayList<StockDataRow> getDataRows() {
        return dataRows;
    }
}
