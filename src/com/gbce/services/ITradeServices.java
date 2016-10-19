package com.gbce.services;

import java.util.List;

import com.gbce.models.Stock;
import com.gbce.models.Trade;
/**
 * Interface for Trade Services implementation
 * 
 * @author Renato Ramos
 * @date 15/10/2016  
 */
public interface ITradeServices {
	/**
	 * Add to trade list
	 * @param trade
	 */
	public void addToTradeList(Trade trade);
	
	/**
	 * Get trades base on a Stock and interval time in minutes. 
	 * @param stock
	 * @param numberOfMinutes
	 * @return List<Trade>
	 */
	public List<Trade> getTrades(Stock stock, int numberOfMinutes);

	/**
	 * Get trade list
	 * @param void
	 * @return List<Trade>
	 */
	public List<Trade> getTradeList();

}
