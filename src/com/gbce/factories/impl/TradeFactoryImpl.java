package com.gbce.factories.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.gbce.factories.ITradeFactory;
import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.utils.GbceUtils;

public class TradeFactoryImpl implements ITradeFactory {
	/**
	 * Create a instance of Map TradeFactoryImpl (DAO class)
	 */
	private List<Trade> tradeList = new ArrayList<Trade>();
	
	private static TradeFactoryImpl instance = null;
	
	/**
	 * Create a instance of TradeFactoryImpl
	 * @return TradeFactoryImpl
	 */
	public static TradeFactoryImpl getInstance() {
		if (instance == null) {
	      instance = new TradeFactoryImpl();
	    }
	    return instance;
	  }
	
	/**
	 * Get trade list
	 * @return List<Trade>
	 */
	public List<Trade> getTradeList() {
		return tradeList;
	}

	/**
	 * Set trade list
	 * @param tradeList
	 * @return void
	 */
	public void setTradeList(List<Trade> tradeList) {
		this.tradeList = tradeList;
	}
	
	/**
	 * Insert in the trade list
	 * @param trade
	 * @return void
	 */
	public void addToTradeList(Trade trade){
		getTradeList().add(trade);
	}
	
	/**
	 * Get trade list by stock
	 * @return List<Trade>
	 */
	@Override
	public List<Trade> getTradeListByStock(Stock stock) {
		/**
		 * Create a temporary list of trades    
		 */
		List<Trade> tmpTradeList = new ArrayList<Trade>();
		    
	    /**
	     * Get past 5 minutes time
	     */
		int fiveMinutesBefore = 5;	
		Date date = new Date(System.currentTimeMillis() - (fiveMinutesBefore*60*1000));
		Timestamp lastTime = new Timestamp(date.getTime());
		    
		/**
		 * Get the current time
		 */
		Timestamp currentTime = GbceUtils.getTimeStamp();
	    /**
	     * Interact in the list of trades 
	     */
		for (int i = 0; i < getTradeList().size(); i++) {
			/**
			 * Check every stock symbol in the Trade is equal to Stock symbol parameter
			 */
			if (getTradeList().get(i).getStock().getSymbol().equalsIgnoreCase(stock.getSymbol())) {
				/**
				 * Check the Trades between past 5 minutes and current time 
				 */
				if (getTradeList().get(i).getTimeStamp().getTime()  >= lastTime.getTime() && 
						getTradeList().get(i).getTimeStamp().getTime()  <= currentTime.getTime()) {
					tmpTradeList.add(getTradeList().get(i));
				}
				
			}
		}
    /**
    * Return List<Trade>
    */
    return tmpTradeList;
	}

	/**
	 * Get all trades based on Stock and past time
	 * @param stock
	 * @param pastMinutes
	 */
	@Override
	public List<Trade> getTradeListByStockAndTime(Stock stock, int pastMinutes) {
		/**
		 * Create a temporary list of trades    
		 */
		List<Trade> tmpTradeList = new ArrayList<Trade>();
	    
		/**
	     * Calculate the time based on pastMinutes
	     */
		Date date = new Date(System.currentTimeMillis() - (pastMinutes*60*1000));
		Timestamp lastTime = new Timestamp(date.getTime());
	    
		/**
		 * Get the current time
		 */
		Timestamp currentTime = GbceUtils.getTimeStamp();
	    
	    
		/**
	     * Interact on the trade list 
	     */
		for (int i = 0; i < getTradeList().size(); i++) {
			/**
			 * Check every stock symbol in the Trade is equal to Stock symbol parameter
			 */
			if (getTradeList().get(i).getStock().getSymbol().equalsIgnoreCase(stock.getSymbol())) {
				/**
				 * Check the Trades between past 5 minutes and current time 
				 */
				if (getTradeList().get(i).getTimeStamp().getTime()  >= lastTime.getTime() && 
						getTradeList().get(i).getTimeStamp().getTime()  <= currentTime.getTime()) {
					tmpTradeList.add(getTradeList().get(i));
				}
			}
		}
	/**
	* Return List<Trade>
	*/
	return tmpTradeList;
	}

}
