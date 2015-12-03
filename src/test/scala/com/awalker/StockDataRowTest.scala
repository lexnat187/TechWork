package com.awalker

import org.joda.time.DateTime
import org.scalatest._

/**
  * @author Alexander Walker on 03/12/2015.
  */
class StockDataRowTest extends FeatureSpec with GivenWhenThen with Matchers with BeforeAndAfterEach with DiagrammedAssertions {

  //var stockRow: StockDataRow

  def getStockRow = {
    //JOE,Common,13,,250
    val row = new StockDataRow
    row.setStockSymbolData("JOE")
    row.setTypeData("Common")
    row.setLastDividendData(13)
    row.setParValueData(250)
    row
  }

  feature("[StockDataRow.10.1] test CalculateDividendYield") {
    scenario("expected output matches generated output") {
      val row = getStockRow
      assert (row.calculateDividendYield() == 0.65)
    }
  }

  feature("[StockDataRow.20.1] test CalculatePERatio") {
    scenario("expected output matches generated output") {
      val row = getStockRow

      assert (row.calculatePERatio() == 1.5384615384615385)
    }
  }

  feature("[StockDataRow.30.1] test RecordTrade") {
    scenario("expected output matches generated output") {
      val row = getStockRow
      val now = DateTime.now
      val trade = new Trade(now, 10, true, 15.5)
      row.recordTrade(trade)
      assert (row.getTradeRecord.size() == 1)
      assert (row.getTradeRecord.get(0).getTimeStamp == now)
    }
  }

  feature("[StockDataRow.40.1] test CalculateStockPrice") {
    scenario("expected output matches generated output") {
      val row = getStockRow
      val now = DateTime.now
      val trade = new Trade(now, 10, true, 15.5)
      row.recordTrade(trade)
      assert (row.calculateStockPrice() == 15.5)
    }
  }

}
