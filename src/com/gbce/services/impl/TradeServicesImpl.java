package com.gbce.services.impl;

import java.util.List;

import com.gbce.factories.impl.TradeFactoryImpl;
import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.services.ITradeServices;
import com.gbce.utils.GbceUtils;
import com.gbce.utils.SystemParameters;

/***
 * Class responsible for all Trade services
 * 
 * @author Renato Ramos
 * @date   12/10/2016
 */
public class TradeServicesImpl implements ITradeServices {
	/**
	 * Create a instance of TradeFactoryImpl (DAO class)
	 */
	private TradeFactoryImpl tradeFactoryDAO = new TradeFactoryImpl();
	
	/**
	 * Create a instance of TradeServicesImpl
	 */
	private static TradeServicesImpl instance = null;
	
	/**
	 * Get a singleton instance of the TradeServicesImpl class
	 * @return TradeServicesImpl
	 */
	public static TradeServicesImpl getInstance() {
	  if (instance == null) {
	     instance = new TradeServicesImpl();
	  }
	 return instance;
	}
	
	/**
	 * Create a object SystemParameters
	 */
	private SystemParameters systemParameters;
	
	/**
	 * Return TradeServicesImpl
	 */
	public TradeServicesImpl(){
		/**
		 * Create a instance of class SystemParameters
		 */
		if (systemParameters == null) {
			this.systemParameters = new SystemParameters();
		}
	}
	

	/**
	 * Insert Trade on the list
	 * @param Trade
	 * @return void
	 */
	@Override
	public void addToTradeList(Trade trade) {
		tradeFactoryDAO.addToTradeList(trade);
	}

	/**
	 * Get all trades base on stock and a past time interval 
	 * @param Trade
	 * @return List<Trade>
	 */
	@Override
	public List<Trade> getTrades(Stock stock, int numberOfMinutes) {
		return tradeFactoryDAO.getTradeListByStockAndTime(stock, numberOfMinutes);
	}

	/**
	 * Get all trades from the list 
	 * @param void
	 * @return List<Trade>
	 */
	@Override
	public List<Trade> getTradeList() {
		return tradeFactoryDAO.getTradeList();	
	}
	
	/**
	 * Generate random quantity of trades
	 * @return int
	 */
	public int getRandomicTradeQuantity(){
		return GbceUtils.randomIntBetweenMinMax(1, systemParameters.getQuantityStockBuySell());
	}
	
	/**
	 * Print all trades from the list parameter
	 * @param tradeList
	 */
	public void printAllTrades(List<Trade> tradeList){
		System.out.println("| -------------------- ALL TRADES ---------------------------- |");
		for (int i = 0; i < tradeList.size(); i++) {
			Trade tmpTrade = tradeList.get(i);
			System.out.println("| TRADE    : " + (i + 1));
			System.out.println("| Stock    : ");
			System.out.println("|          Symbol ........................... : " + tmpTrade.getStock().getSymbol());
			System.out.println("|          Price ............................ : " + tmpTrade.getStock().getPrice());
			System.out.println("|          Created .......................... : " + tmpTrade.getStock().getTimeStamp().toString());
			System.out.println("|          Dividend Yield ................... : " + tmpTrade.getStock().getDividendYield());
			System.out.println("|          P /E Ratio ....................... : " + tmpTrade.getStock().getPeRatio());
			System.out.println("|          Volume Weight in the past 5 minutes: " + tmpTrade.getStock().getVolumeWeightedBasedInPast5Minutes());
			System.out.println("|          Quantity ......................... : " + tmpTrade.getQuantity());
			System.out.println("| Situation: " + tmpTrade.getBuyOrSell());
			System.out.println("| time     : " + tmpTrade.getTimeStamp().toString());
			if (i < tradeList.size()) {
				System.out.println("| ------------------------------------------------------------ |");
			}
			
			
		}
		System.out.println("| ------------------------------------------------------------ |");
	}

	/**
	 * Print all trades based on stock symbol
	 * @param tradeList
	 * @param symbol
	 * @return void
	 */
	public void printAllTradesByStockSymbol(List<Trade> tradeList, String symbol){
		System.out.println("| -------------------- ALL TRADES ---------------------------- |");
		for (int i = 0; i < tradeList.size(); i++) {
			if (tradeList.get(i).getStock().getSymbol().equalsIgnoreCase(symbol)) {
				Trade tmpTrade = tradeList.get(i);
				System.out.println("| TRADE    : " + (i + 1));
				System.out.println("| Stock    : ");
				System.out.println("|          Symbol ........................... : " + tmpTrade.getStock().getSymbol());
				System.out.println("|          Price ............................ : " + tmpTrade.getStock().getPrice());
				System.out.println("|          Created .......................... : " + tmpTrade.getStock().getTimeStamp().toString());
				System.out.println("|          Dividend Yield ................... : " + tmpTrade.getStock().getDividendYield());
				System.out.println("|          P /E Ratio ....................... : " + tmpTrade.getStock().getPeRatio());
				System.out.println("|          Volume Weight in the past 5 minutes: " + tmpTrade.getStock().getVolumeWeightedBasedInPast5Minutes());
				System.out.println("|          Quantity ......................... : " + tmpTrade.getQuantity());
				System.out.println("| Situation: " + tmpTrade.getBuyOrSell());
				System.out.println("| time     : " + tmpTrade.getTimeStamp().toString());
				if (i < tradeList.size()) {
					System.out.println("| ------------------------------------------------------------ |");
				}
				
			}	
			
		}
		
	}

}
