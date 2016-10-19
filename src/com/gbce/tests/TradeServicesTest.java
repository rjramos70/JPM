package com.gbce.tests;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.services.impl.TradeServicesImpl;
import com.gbce.utils.GbceUtils;

/**
 * Class responsible to all TradeServices tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class TradeServicesTest {
	/**
	 * Create an instance of TradeServices class
	 */
	TradeServicesImpl tradeServices = new TradeServicesImpl();
	/**
	 * Create an instance of Stock class
	 */
	Stock stock = new Stock(125.12, "GIN", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	/**
	 * Create an instance of Trade class
	 */
	Trade trade = new Trade(GbceUtils.getTimeStamp(), stock, 100, "BUY", 70);
	
	/**
	 * test GetInstance of TradeServices class
	 */
	@Test
	public void testGetInstance() {
		/**
		 * Check if the instance is null
		 */
		Assert.assertTrue(tradeServices != null);
	}

	/**
	 * test Add trade to the TradeList
	 */
	@Test
	public void testAddToTradeList() {
		/**
		 * Get the trade list size before
		 */
		int sizeBefore = tradeServices.getTradeList().size();
		/**
		 * Add a trade to the trade list
		 */
		tradeServices.addToTradeList(trade);
		/**
		 * Get the trade list size after
		 */
		int sizeAfter = tradeServices.getTradeList().size();
		/**
		 * Check if size before is smaller than size after
		 */
		Assert.assertTrue(sizeBefore < sizeAfter);

	}

	/**
	 * test GetTrades base on a stock and interval time
	 */
	@Test
	public void testGetTrades() {
		/**
		 * Because the trade list is empty, the method does not return
		 * any stock and the temporary list has no register
		 */
		Assert.assertTrue(tradeServices.getTrades(stock, 5).size() == 0);
	}

	/**
	 * test GetTradeList
	 */
	@Test
	public void testGetTradeList() {
		/**
		 * Because the trade list is empty, the method does not return
		 * any stock and the temporary list has no register.
		 * Check if the trade list has no register
		 */
		Assert.assertTrue(tradeServices.getTradeList().size() == 0);
	}

	/**
	 * test GetRandomicTrdeQuantity
	 */
	@Test
	public void testGetRandomicTradeQuantity() {
		/**
		 * Check if the random trade quantity method returns value more than zero.
		 */
		Assert.assertTrue(tradeServices.getRandomicTradeQuantity() > 0);
	}

}
