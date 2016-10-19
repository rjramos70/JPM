/**
 * 
 */
package com.gbce.tests;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.models.Stock;

/**
 * Class responsible to all Stock tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class StockTest extends Stock {
	/**
	 * Create an instance of Stock
	 */
	Stock stock = new Stock(125.12, "GIN", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	/**
	 * Check if the Stock parameters are null
	 */
	@Test
	public void stockConstrutorEmpty() {
		/**
		 * Create a temporary empty instance of Stock
		 */
		Stock s = new Stock();
		/**
		 * Check every getter
		 */
		Assert.assertFalse(s.getPrice() > 0);
		Assert.assertFalse(s.getSymbol() != null);
		Assert.assertFalse(s.getType() != null);
		Assert.assertFalse(s.getLastDividend() > 0);
		Assert.assertFalse(s.getFixedDividend() > 0);
		Assert.assertFalse(s.getParValue() > 0);
		Assert.assertFalse(s.getDividendYield() > 0);
		Assert.assertFalse(s.getPeRatio() > 0);
		
	}

	
	
	/**
	 * Check if the Stock parameters are not null
	 */
	@Test
	public void stockContrutorNotEmpty() {
		/**
		 * Create a temporary instance of Stock
		 */
		Stock stock = new Stock(125.12, "GIN", "Preferred", 8.0, 2, 100, 157.85, 130.42);
		/**
		 * Set the Volume Weighted base on a value
		 */
		stock.setVolumeWeightedBasedInPast5Minutes(5.98765);
		/**
		 * Test every getter
		 */
		Assert.assertNotNull(stock.getPrice());
		Assert.assertNotNull(stock.getSymbol());
		Assert.assertNotNull(stock.getType());
		Assert.assertNotNull(stock.getLastDividend());
		Assert.assertNotNull(stock.getFixedDividend());
		Assert.assertNotNull(stock.getParValue());
		Assert.assertNotNull(stock.getDividendYield());
		Assert.assertNotNull(stock.getPeRatio());
		Assert.assertNotNull(stock.getVolumeWeightedBasedInPast5Minutes());
	}
}

