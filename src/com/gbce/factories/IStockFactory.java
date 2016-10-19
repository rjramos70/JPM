package com.gbce.factories;

import com.gbce.models.Stock;

/***
 * Interface for Stock factory implementation
 * 
 * @author Renato Ramos
 * @date   16/10/2016
 */
public interface IStockFactory {
	/**
	 * Add new Stock in the database
	 * @param stock
	 */
	void addStock(Stock stock);
	
	/**
	 * Get Stock base on the symbol
	 * @param symbol
	 * @return
	 */
	Stock getStock(String symbol);

}
