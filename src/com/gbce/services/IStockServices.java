package com.gbce.services;

import java.math.BigDecimal;
import java.util.List;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
/**
 * Interface for Stock Services implementation
 * 
 * @author Renato Ramos
 * @date   16/10/2016
 */
public interface IStockServices {
	/**
	 * Add stock to the list
	 * @param stock
	 * @return void
	 */
	public void addStock(Stock stock);
	
	/**
	 * Get Stock by the symbol
	 * @param symbol
	 * @return Stock
	 */
	public Stock getStock(String symbol);
	
	/**
	 * Calculate the Dividend Yield base on Stock and Price
	 * @param stock
	 * @param price
	 * @return double
	 */
	public double calculateDividendYield(Stock stock, double price);
	
	/**
	 * Calculate the P/E Ratio base on Stock and Price
	 * @param stock
	 * @param price
	 * @return double
	 */
	public double calculatePERatio(Stock stock, double price);
	
	/**
	 * Calculate Volume Weight Price base on list of Trades, Stock and a randomic price
	 * @param tradeList
	 * @param stock
	 * @param randPrice
	 * @return double
	 */
	public double calculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice(List<Trade> tradeList, Stock stock, double randPrice);
	
	/**
	 * Calculate the GBCE all share base on a list of trades
	 * @param trades
	 * @return
	 */
	public BigDecimal calculateGBCEReturningBigDecimal(List<Trade> trades);
	
}
