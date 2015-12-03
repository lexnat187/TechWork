package com.awalker

import java.io.File

import org.scalatest._

/**
  * @author Alexander Walker on 03/12/2015.
  */
class CSVHandlerTest extends FeatureSpec with GivenWhenThen with Matchers with BeforeAndAfterEach with DiagrammedAssertions {

  private def loadResourceAsURL(resName: String) = {
    ClassLoader.getSystemResource(resName)
  }

  feature("[CSVHandler.10.1] CSV file can be parsed") {
    scenario("expected output matches generated output") {
      val inputFile= new File(loadResourceAsURL("com.awalker.data/sampledata.csv").getFile)
      try {
        val stockData = CSVHandler.parseCSVInput(inputFile)
        assert (stockData != null)
        assert (stockData.getDataRows.get(0).getStockSymbolData == "TEA")
      }
      catch {
        case e: Exception =>
          e.printStackTrace()
          fail()
      }
    }
  }

}
