package com.gbce.models;

import java.sql.Timestamp;

/***
 * Class that has all Trade properties an methods
 * @author Renato Ramos
 * @date   15/10/2016
 */
public class Trade implements Comparable<Trade>{
	private Timestamp timeStamp;
	private Stock stock;
	private int quantity;
	private String buyOrSell;
	private double price;
	
	/**
	 * Constructor
	 * @param timeStamp
	 * @param stock
	 * @param quantity
	 * @param buyOrSell
	 * @param price
	 * @return Trade
	 */
	public Trade(Timestamp timeStamp, Stock stock, int quantity,
			String buyOrSell, double price) {
		super();
		this.timeStamp = timeStamp;
		this.stock = stock;
		this.quantity = quantity;
		this.buyOrSell = buyOrSell;
		this.price = price;
	}
	
	/**
	 * Get timeStamp
	 * @return Timestamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Set timeStamp
	 * @param timeStamp
	 */
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	/**
	 * Get the Stock
	 * @return Stock
	 */
	public Stock getStock() {
		return stock;
	}
	
	/**
	 * Set the Stock
	 * @param stock
	 */
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	/**
	 * Get quantity
	 * @return int
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * Set quantity
	 * @param quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Get if the Trade is BUY or SELL
	 * @return String
	 */
	public String getBuyOrSell() {
		return buyOrSell;
	}
	
	/**
	 * Set if the trade is BUY or SELL
	 * @param buyOrSell
	 */
	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}
	
	/**
	 * Get trade price
	 * @return double
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set trade price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Override toString method
	 * @return String
	 */
	@Override
	public String toString(){
		return  " ======= TRADE =========================== "
				.concat("\n| TimeStamp: ").concat(String.valueOf(getTimeStamp()))
				.concat("\n| Stock Symbol: ").concat(getStock().getSymbol())
				.concat("\n| Stock Qtd: ").concat(String.valueOf(getQuantity()))
				.concat("\n| Buy/Sell: ").concat(getBuyOrSell())
				.concat("\n| Stock DividendYield Price: ").concat(String.valueOf(getStock().getDividendYield()))
				.concat("\n| Stock P/E Ratio: ").concat(String.valueOf(getStock().getPeRatio()))
				.concat("\n| Stock Volume Weight: ").concat(String.valueOf(getStock().getVolumeWeightedBasedInPast5Minutes()))
				.concat("\n| Trade Price: ").concat(String.valueOf(getPrice()))
				.concat("\n =====================================");
	}
	
	/**
	 * Return a string customized with symbol, quantity, price, 
	 * situation (BUY or SELL), and timeStamp
	 * @return String
	 */
	public String strQuantyPriceAndTimeStamp(){
		return "->TRADE: Symbol: ".concat(getStock().getSymbol())
				.concat("; Stock Qtd.:").concat(String.valueOf(getQuantity()))
				.concat("; Price: ").concat(String.valueOf(getPrice()))
				.concat("; Situation :").concat(getBuyOrSell())
				.concat("; TimeStamp: ").concat(getTimeStamp().toString());
	}

	/**
	 * Implement interface Comparable
	 * @return int
	 */
	public int compareTo(Trade trade) {
		return trade.getTimeStamp().compareTo(this.timeStamp);
	}
	

}
