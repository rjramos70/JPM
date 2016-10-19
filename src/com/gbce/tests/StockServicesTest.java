package com.gbce.tests;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.services.impl.StockServicesImpl;
import com.gbce.utils.GbceUtils;

/**
 * Class responsible to all StockServices tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class StockServicesTest {
	/**
	 * Create a instance of StockService
	 */
	StockServicesImpl stockService = new StockServicesImpl();
	/**
	 * Create two instance of Stock classes
	 */
	Stock stock = new Stock(125.12, "GIN", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	Stock stockTest = new Stock(125.12, "TEST", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	/**
	 * Create a instance of Trade class
	 */
	Trade tradeTest = new Trade(GbceUtils.getTimeStamp(), stockTest, 350, "SELL", 95);
	/**
	 * Create a trade list
	 */
	List<Trade> tmpList = new ArrayList<Trade>();
	
	
	/**
	 * test GetInstance
	 */
	@Test
	public void testGetInstance() {
		Assert.assertTrue(stockService != null);
	}

	/**
	 * test GetRandomicStockPrice
	 */
	@Test
	public void testGetRandomicStockPrice() {
		Assert.assertTrue(stockService.getRandomicStockPrice() != 0);
	}

	/**
	 * test CalculateGBCEReturningBigDecimal
	 */
	@Test
	public void testCalculateGBCEReturningBigDecimal() {
		/**
		 * Set a new Volume Weighted based in past five minutes
		 */
		tradeTest.getStock().setVolumeWeightedBasedInPast5Minutes(111.25);
		/**
		 * Add trades to the list
		 */
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		/**
		 * Calculate GBCE base on a Trade list and return a BigDecimal value 
		 */
		BigDecimal tmp = stockService.calculateGBCEReturningBigDecimal(tmpList);
		/**
		 * Check if value is different from zero
		 */
		Assert.assertNotEquals(tmp, new BigDecimal(0));
	}

	/**
	 * test CalculateGBCEReturningDouble
	 */
	@Test
	public void testCalculateGBCEReturningDouble() {
		/**
		 * Add trades to the list
		 */
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		/**
		 * Calculate GBCE base on a Trade list and return a Double value 
		 */
		double tmp = stockService.calculateGBCEReturningDouble(tmpList);
		/**
		 * Check if value is different from zero
		 */
		Assert.assertNotEquals(tmp, 0);

	}

	/**
	 * test square root
	 */
	@Test
	public void testSqrt() {
		/**
		 * Calculate square root from a BigDecimal with value nine
		 */
		@SuppressWarnings("static-access")
		BigDecimal nine = stockService.sqrt(new BigDecimal(9));
		/**
		 * Create a BigDecimal with value three
		 */
		BigDecimal three = new BigDecimal(3);
		/**
		 * Check if the square root of nine is three
		 */
		Assert.assertTrue(nine.intValue() == three.intValue());
	}

	/**
	 * test GetTradeList
	 */
	@Test
	public void testGetTradeList() {
		/**
		 * Create a temporary trade list
		 */
		List<Trade> tmpList = stockService.getTradeList();
		/**
		 * Add one trade to the list
		 */
		tmpList.add(tradeTest);
		/**
		 * Check if list has any register
		 */
		Assert.assertTrue(tmpList.size() > 0);
	}

	/**
	 * test CalculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice
	 */
	@Test
	public void testCalculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice() {
		/**
		 * Add trades to the list
		 */
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		double tmpValue = stockService.calculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice(tmpList, stock, 175.35);
		
		/**
		 * tmpValue return zero because all trades on the list has no 
		 * timeStamp older the 5 minutes from the current timeStamp.
		 */
		Assert.assertTrue(tmpValue == 0);
	}

	/**
	 * test CalBuySellStock
	 */
	@Test
	public void testCalBuySellStock() {
		/**
		 * Return a Map base on a stock parameter
		 */
		@SuppressWarnings("static-access")
		Map<String, Integer> tmpMap = stockService.calBuySellStock(stock);
		/**
		 * Check if this map has any register
		 */
		Assert.assertTrue(tmpMap.size() > 0);
	}

	/**
	 * test AddStock
	 */
	@Test
	public void testAddStock() {
		/**
		 * Check the stock list size before
		 */
		int sizeBefore = stockService.getStockList().size();
		/**
		 * Add two stocks to the list
		 */
		stockService.addStock(stockTest);
		stockService.addStock(stock);
		/**
		 * Check the stock list size after
		 */
		int sizeAfter = stockService.getStockList().size();
		/**
		 * Compare the stock list size before and after 
		 */
		Assert.assertTrue(sizeBefore < sizeAfter);
	}

	/**
	 * test GetStock
	 */
	@Test
	public void testGetStock() {
		/**
		 * Get stock from stock list base on "JOE" symbol
		 */
		Stock tmpStock = stockService.getStock("JOE");
		/**
		 * Check if the Par Value value is not empty
		 */
		Assert.assertTrue(tmpStock.getParValue() > 0);
	}

	/**
	 * test CalculateDividendYield
	 */
	@Test
	public void testCalculateDividendYield() {
		/**
		 * Calculate dividend yield base on an instance of stock and a stock price
		 */
		double tmpValue = stockService.calculateDividendYield(stock, 130.33);
		/**
		 * Check if dividend yield value is bigger than zero.
		 */
		Assert.assertTrue(tmpValue > 0);
	}
	
	/**
	 * test Calculate P/E Ratio
	 */
	@Test
	public void testCalculatePERatio() {
		/**
		 * Calculate P/E Ratio base on an instance of stock and a stock price
		 */
		double tmpValue = stockService.calculatePERatio(stock, 130.33);
		/**
		 * Check if P/E Ratio value is bigger than zero.
		 */
		Assert.assertTrue(tmpValue > 0);
	}

}
