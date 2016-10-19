package com.gbce.factories;

import java.util.List;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
/**
 * Interface for Trade factory implementation 
 * @author Renato Ramos
 * @date   16/10/2016
 */
public interface ITradeFactory {
	/**
	 * Insert trade in the list
	 * @param trade
	 * @return void
	 */
	public void addToTradeList(Trade trade);
	/**
	 * Get all the trades by the Stock and past minutes until current time
	 * @param stock
	 * @param pastMinutes
	 * @return List<Trade>
	 */
	List<Trade> getTradeListByStockAndTime(Stock stock, int pastMinutes);
	/**
	 * Get all trades by the Stock
	 * @param stock
	 * @return List<Trade>
	 */
	List<Trade> getTradeListByStock(Stock stock);
	/**
	 * Get all the trades until current time
	 * @param void
	 * @return List<Trade>
	 */
	List<Trade> getTradeList();
}
