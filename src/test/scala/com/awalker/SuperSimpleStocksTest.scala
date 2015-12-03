package com.awalker


import org.scalatest._

/**
  * @author Alexander Walker on 03/12/2015.
  */
class SuperSimpleStocksTest extends FeatureSpec with GivenWhenThen with Matchers with BeforeAndAfterEach with DiagrammedAssertions {

  feature("[SSStock.10.1] CSV file can be parsed and output generated") {
    scenario("execute default test file from resources folder") {
      try {
        new SuperSimpleStocks("")
      }
      catch{
        case e: Exception =>
          e.printStackTrace()
          fail()
      }
    }

    scenario("execute test file from specified path") {
      pending
      try {
        val path = "C:/test/stockdata.csv"
        new SuperSimpleStocks(path)
      }
      catch{
        case e: Exception =>
          e.printStackTrace()
          fail()
      }
    }

  }
}
