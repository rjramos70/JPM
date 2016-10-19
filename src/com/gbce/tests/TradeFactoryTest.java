package com.gbce.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.factories.impl.TradeFactoryImpl;
import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.utils.GbceUtils;

/**
 * Class responsible to all TradeFactory tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class TradeFactoryTest {
	/**
	 * Create a instance of TradeFactoryImpl
	 */
	TradeFactoryImpl tradeFactory = new TradeFactoryImpl();
	/**
	 * Create two instances of Stock
	 */
	Stock stock = new Stock(125.12, "GIN", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	Stock stockTest = new Stock(125.12, "TEST", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	/**
	 * Create two instances of Trade
	 */
	Trade trade = new Trade(GbceUtils.getTimeStamp(), stock, 100, "BUY", 70);
	Trade tradeTest = new Trade(GbceUtils.getTimeStamp(), stockTest, 350, "SELL", 95);

	/**
	 * test GetInstance of TradeFactoryImpl
	 */
	@Test
	public void testGetInstance() {
		/**
		 * Check if the instance is null
		 */
		Assert.assertTrue(tradeFactory != null);
	}

	/**
	 * test GetTradeList
	 */
	@Test
	public void testGetTradeList() {
		/**
		 * Add a new trade to the list
		 */
		tradeFactory.getTradeList().add(trade);
		/**
		 * Check if trade list has any register
		 */
		Assert.assertTrue(tradeFactory.getTradeList().size() > 0);
	}

	/**
	 * test SetTradeList
	 */
	@Test
	public void testSetTradeList() {
		/**
		 * Create a temporary trade list
		 */
		List<Trade> tmpList = new ArrayList<Trade>();
		/**
		 * Add for trade in the list
		 */
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		tmpList.add(tradeTest);
		/**
		 * Set the trade list including this temporary trade list
		 */
		tradeFactory.setTradeList(tmpList);
		/**
		 * Check if trade list has registers
		 */
		Assert.assertTrue(tradeFactory.getTradeList().size() > 0);
	}

	/**
	 * test AddToTradeList
	 */
	@Test
	public void testAddToTradeList() {
		/**
		 * Get the trade list size before
		 */
		int sizeBefore = tradeFactory.getTradeList().size();
		/**
		 * Add a trade to the trade list
		 */
		tradeFactory.getTradeList().add(tradeTest);
		/**
		 * Get the trade list size after
		 */
		int sizeAfter = tradeFactory.getTradeList().size();
		/**
		 * Check if size before is smaller than size after
		 */
		Assert.assertTrue(sizeBefore < sizeAfter);
	}

	/**
	 * test GetTradeListByStock
	 */
	@Test
	public void testGetTradeListByStock() {
		/**
		 * Get a temporary list of trade base on a specific stock
		 */
		List<Trade> tmpList = tradeFactory.getTradeListByStock(stock);
		/**
		 * Because the trade list is empty, the method does not return
		 * any stock and the temporary list has no register
		 */
		Assert.assertTrue(tmpList.size() == 0);

	}

	/**
	 * test GetTradeList base on a stock and time
	 */
	@Test
	public void testGetTradeListByStockAndTime() {
		/**
		 * Because the trade list is empty, that is no trades in the past five minutes from now.
		 */
		Assert.assertTrue(tradeFactory.getTradeListByStockAndTime(stock, 5).size() == 0);
	}

}
