package com.gbce.models;

import com.gbce.utils.GbceUtils;

/**
 * Class is responsible for the stock model
 * 
 * @author  Renato Ramos
 * @date    15/10/2016
 *
 */
public class Stock extends StockHeader{
	
	private double price;			/** Stock Price */ 
	private double dividendYield;	/** Dividend yield */
	private double peRatio;			/** P/E Ratio */
	private double volumeWeightedBasedInPast5Minutes;  /** Volume Weighted Stock Price */
	
	
	/**
	 * Constructor
	 * @param price
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 * @param dividendYield
	 * @param peRatio
	 * @return Stock
	 */
	public Stock(double price, String symbol, String type, double lastDividend,
			double fixedDividend, double parValue, double dividendYield,
			double peRatio) {
		super();
		setPrice(price);
		setSymbol(symbol);
		setType(type);
		setLastDividend(lastDividend);
		setFixedDividend(fixedDividend);
		setParValue(parValue);
		setDividendYield(dividendYield);
		setPeRatio(peRatio);
		setTimeStamp(GbceUtils.getTimeStamp());
	}
	/**
	 * Constructor	
	 * @param symbol
	 * @param type
	 * @param lastDividend
	 * @param fixedDividend
	 * @param parValue
	 * @return Stock
	 */
	public Stock(String symbol, String type, double lastDividend,
			double fixedDividend, double parValue) {
		super();
		setSymbol(symbol);
		setType(type);
		setLastDividend(lastDividend);
		setFixedDividend(fixedDividend);
		setParValue(parValue);
		setTimeStamp(GbceUtils.getTimeStamp());

	}
	/**
	 * Empty constructor
	 * @return Stock
	 */
	public Stock(){ 
		super();
	}
	
	
	/**
	 * Get price
	 * @return double
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set price
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Get Dividend Yield
	 * @return double
	 */
	public double getDividendYield() {
		
		return dividendYield;
	}
	
	/**
	 * Set Dividend Yield
	 * @param dividendYield
	 */
	public void setDividendYield(double dividendYield) {
		
		this.dividendYield = dividendYield;
	}

	/**
	 * Get P/E Ratio
	 * @return double
	 */
	public double getPeRatio() {
		return peRatio;
	}
	
	/**
	 * Set P/E Ratio
	 * @param peRatio
	 */
	public void setPeRatio(double peRatio) {
		this.peRatio = peRatio;
	}
	
	/**
	 * Get Volume Weight based in past 5 minutes
	 * @return double
	 */
	public double getVolumeWeightedBasedInPast5Minutes() {
		return volumeWeightedBasedInPast5Minutes;
	}
	
	/**
	 * Set Volume Weight based in past 5 minutes
	 * @param volumeWeightedBasedInPast5Minutes
	 */
	public void setVolumeWeightedBasedInPast5Minutes(
			double volumeWeightedBasedInPast5Minutes) {
		this.volumeWeightedBasedInPast5Minutes = volumeWeightedBasedInPast5Minutes;
	}
	
	/**
	 * Override toString method
	 * @return String
	 */
	@Override
	public String toString(){
		return            "| Symbol        : ".concat(getSymbol()).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Price         : ").concat(String.valueOf(this.price)).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Type          : ").concat(getType()).concat("\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Last Dividend : ").concat(String.valueOf(getLastDividend())).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Fixed Dividend: ").concat(String.valueOf(getFixedDividend()).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Par Value     : ").concat(String.valueOf(getParValue())).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Dividend Yield: ").concat(String.valueOf(getDividendYield())).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| P /E Ratio    : ").concat(String.valueOf(getPeRatio())).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| Volume Weight : ").concat(String.valueOf(getVolumeWeightedBasedInPast5Minutes())).concat("\t\t\t\t\t\t\t\t\t\t    |")
				.concat("\n| TimeStamp     : ").concat(getTimeStamp().toString())).concat("\t\t\t\t\t\t\t    |");
	}
	
	/**
	 * Custom return string with symbol and price stock
	 * @return String
	 */
	public String strStockPriceAndSymbol(){
		return  "|>> Symbol: ".concat(getSymbol())
				.concat("; Price: ").concat(String.valueOf(getPrice()));
	}

}
