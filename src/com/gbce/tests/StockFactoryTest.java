package com.gbce.tests;

import org.junit.Assert;
import org.junit.Test;

import com.gbce.factories.impl.StockFactoryImpl;
import com.gbce.models.Stock;

/**
 * Class responsible to all Stock Factory tests
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class StockFactoryTest {
	/**
	 * Create a instance of StockFactoryImpl
	 */
	StockFactoryImpl stockFactory = new StockFactoryImpl();
	/**
	 * Create a stock instance
	 */
	Stock stockTest = new Stock(125.12, "TEST", "Preferred", 8.0, 2, 100, 157.85, 130.42);
	
	/**
	 * test StockFactoryImpl
	 */
	@Test
	public void testStockFactoryImpl() {
		Assert.assertTrue(stockFactory != null);
	}
	
	/**
	 * test LoadStockList
	 */
	@Test
	public void testLoadStockList() {
		Assert.assertTrue(stockFactory.getStockList().size() > 0);
	}
	
	/**
	 * test GetSystemParameter
	 */
	@Test
	public void testGetSystemParameter() {
		Assert.assertTrue(stockFactory.getSystemParameter() != null);
	}
	
	/**
	 * test GetStockList
	 */
	@Test
	public void testGetStockList() {
		Assert.assertTrue(stockFactory.getStockList().size() > 0);
	}
	
	/**
	 * test AddStock
	 */
	@Test
	public void testAddStock() {
		int sizeBefore = stockFactory.getStockList().size();
		stockFactory.addStock(stockTest);
		int sizeAfter = stockFactory.getStockList().size();
		Assert.assertTrue(sizeBefore < sizeAfter);
	}

	/**
	 * test GetStock
	 */
	@Test
	public void testGetStock() {
		stockFactory.loadStockList();
		Assert.assertTrue(stockFactory.getStock("GIN").getSymbol().equalsIgnoreCase("GIN"));
	}

}
