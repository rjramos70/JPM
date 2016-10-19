package com.gbce.models;

import java.sql.Timestamp;

/**
 * Header Stock
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class StockHeader {
	private String symbol;			/** Stock symbol */
	private String type;			/** Type of stock */
	private double lastDividend;	/** Last dividend */
	private double fixedDividend;	/** Fixed dividend */
	private double parValue;		/** Par value */
	private Timestamp timeStamp;	/** Timestamp from Stock creation */ 
	
	/**
	 * Empty constructor
	 * @return StockHeader
	 */
	public StockHeader(){}
	
	/**
	 * Get symbol
	 * @return String
	 */
	public String getSymbol() {
		return symbol;
	}
	
	/**
	 * Set symbol
	 * @param symbol
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	/**
	 * Get type
	 * @return String
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set type
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get last dividend
	 * @return double
	 */
	public double getLastDividend() {
		return lastDividend;
	}
	
	/**
	 * Set last dividend
	 * @param lastDividend
	 */
	public void setLastDividend(double lastDividend) {
		this.lastDividend = lastDividend;
	}
	
	/**
	 * Get fixed dividend
	 * @return double
	 */
	public double getFixedDividend() {
		return fixedDividend;
	}
	
	/**
	 * Set fixed dividend
	 * @param fixedDividend
	 */
	public void setFixedDividend(double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	
	/**
	 * Get Par Value
	 * @return double
	 */
	public double getParValue() {
		return parValue;
	}
	
	/**
	 * Set Par Value
	 * @param parValue
	 */
	public void setParValue(double parValue) {
		this.parValue = parValue;
	}
	
	/**
	 * Get TimeStamp
	 * @return Timestamp
	 */
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * Set Timestamp
	 * @param timeStamp
	 */
	protected void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

}
