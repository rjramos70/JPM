package com.gbce.tests;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.utils.GbceUtils;

/**
 * Class responsible to all Trade tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class TradeTest {
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
	 * test Trade instance
	 */
	@Test
	public void testTrade() {
		/**
		 * Check if the trade instance is null
		 */
		Assert.assertTrue(trade != null);
	}

	/**
	 * test GetTimeStamp
	 */
	@Test
	public void testGetTimeStamp() {
		/**
		 * Check if getTimeStamp method return value more than zero.
		 */
		Assert.assertTrue(trade.getTimeStamp().getTime() > 0);
	}

	/**
	 * test GetStock from the trade
	 */
	@Test
	public void testGetStock() {
		/**
		 * Check if stock symbol inside the trade is equal to "Test"
		 */
		Assert.assertTrue(tradeTest.getStock().getSymbol().equalsIgnoreCase("Test"));
	}

	/**
	 * test SetStock
	 */
	@Test
	public void testSetStock() {
		/**
		 * Set trade stock
		 */
		trade.setStock(stockTest);
		/**
		 * Check if stock symbol inside the trade is equal to "Test"
		 */
		Assert.assertTrue(trade.getStock().getSymbol().equalsIgnoreCase("Test"));
	}

	/**
	 * test GetQuantity
	 */
	@Test
	public void testGetQuantity() {
		/**
		 * Check if the trade quantity is bigger than zero.
		 */
		Assert.assertTrue(trade.getQuantity() > 0);
	}

	/**
	 * test SetQuantity
	 */
	@Test
	public void testSetQuantity() {
		/**
		 * Set the trade quantity to one thousand
		 */
		trade.setQuantity(1000);
		/**
		 * Check if trade quantity is equal to one thousand.
		 */
		Assert.assertTrue(trade.getQuantity() == 1000);
	}

	/**
	 * test GetBuyOrSell
	 */
	@Test
	public void testGetBuyOrSell() {
		/**
		 * Check if getBuyOrSell method return null or empty.
		 */
		Assert.assertTrue(trade.getBuyOrSell() != null || trade.getBuyOrSell() != "");
	}

	/**
	 * test SetBuyOrSell
	 */
	@Test
	public void testSetBuyOrSell() {
		/**
		 * Get the current type
		 */
		String str = trade.getBuyOrSell();
		/**
		 * IF type equals to BUY, set SELL
		 */
		if (str.equalsIgnoreCase("BUY")) {
			trade.setBuyOrSell("SELL");
		}else{
			/**
			 * Set BUY
			 */
			trade.setBuyOrSell("BUY");
		}
	/**
	 * Check if current type is equal to type changed	
	 */
	Assert.assertFalse(trade.getBuyOrSell().equalsIgnoreCase(str));
	}

	/**
	 * test GetPrice
	 */
	@Test
	public void testGetPrice() {
		/**
		 * Check if trade price is bigger than zero.
		 */
		Assert.assertTrue(trade.getPrice() > 0);
	}

	/**
	 * test SetPrice
	 */
	@Test
	public void testSetPrice() {
		/**
		 * Get the trade price
		 */
		double tmpPrice = trade.getPrice();
		/**
		 * Set new trade price to one thousand
		 */
		trade.setPrice(1000);
		/**
		 * Check if the price before is less than the current price
		 */
		Assert.assertTrue(tmpPrice < trade.getPrice());
	}

	/**
	 * test ToString
	 */
	@Test
	public void testToString() {
		/**
		 * Check if the toString method return a string filled 
		 */
		Assert.assertTrue(trade.toString().length() > 0);
	}

	/**
	 * test custom String conversion StrQuantyPriceAndTimeStamp
	 */
	@Test
	public void testStrQuantyPriceAndTimeStamp() {
		/**
		 * Check if the toString method return a string filled 
		 */
		Assert.assertTrue(trade.strQuantyPriceAndTimeStamp().length() > 0);
	}

	/**
	 * test CompareTo
	 */
	@Test
	public void testCompareTo() {
		/**
		 * Compare two different trades, if equals, return 1 (one) else 0 (zero)
		 */
		int tmpValue = trade.compareTo(tradeTest);
		Assert.assertTrue(tmpValue == 0);
	}

}
