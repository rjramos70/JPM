package com.gbce.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gbce.factories.impl.StockFactoryImpl;

public class SystemParameters {
	/**
	 * Properties to create random Stocks
	 */
	private double minStockPrice;
	private double maxStockPrice;
	private int quantityStockBuySell;
	private int intervalToCreateStockFirstLoad;
	private int intervalCalculateVolumeWeightedStockPrice;
	private int intervalGivenStockPrice;
	private int intervalToCalculateGBCEByTime;
	private int quantityOfStockLoaded;
//	private StockFactoryImpl stockFactory = new StockFactoryImpl();
//	private StockServices stockService;
//	private List<Stock> allStocks;
//	/**
//	 * Singleton instance of SystemParameters class
//	 */
//	private static SystemParameters instance = new SystemParameters();
	
	/**
	 * Constructor(s)
	 */
	public SystemParameters(int quantityOfStockLoaded){
		this.quantityOfStockLoaded = quantityOfStockLoaded;
		loadParameters();
	}
	
	public SystemParameters(){
		loadParameters();
	}
	
	/**
	 * Method responsible to print in the console 
	 * the system summary
	 * 
	 * @param void
	 */
	public void showSystemSummary() {
		@SuppressWarnings("unused")
		StockFactoryImpl stockTmpFactory = new StockFactoryImpl();
		System.out.println("|---------------------------------------------------------------------------------------------------|");
		System.out.println("|                                          SYSTEM SUMMARY                                           |");       
		System.out.println("|---------------------------------------------------------------------------------------------------|");
		System.out.println("| 1. System will load " + this.quantityOfStockLoaded + " Stocks from XML file. \t\t\t\t\t\t\t    |");
		System.out.println("| 2. Stock price varies from " + GbceUtils.doubleToStringWithTwoDecimalPlaces(getMinStockPrice()) + " to " + GbceUtils.doubleToStringWithTwoDecimalPlaces(getMaxStockPrice()) + " \t\t\t\t\t\t\t    |");
		System.out.println("| 3. Maximum quantity of BUY or SELL Stocks varies between 1 and " + getQuantityStockBuySell() + " \t\t\t\t    |");
		System.out.println("| 4. First Stock load has a delay of " + getIntervalToCreateStockFirstLoad() + " seconds to be created.   \t\t\t\t    |");
		System.out.println("| 5. Interval of given Stock price occurs each " + (getIntervalGivenStockPrice() * 30) + " seconds,\t\t\t\t\t    |");
		System.out.println("|    and calculates the Volume Weight Price for the respective Stock.                               |");
		System.out.println("| 6. Every " + getIntervalToCalculateGBCEByTime() + " minutes, the system will calculate the GBCE all share \t\t\t\t    |");
		System.out.println("|    index base on each volume weight stock price of all shareds accumulated from when the system   |");
		System.out.println("|    started run.                                                                                   |");
		// System.out.println("|---------------------------------------------------------------------------------------------------|"); 
		
		
	}
	
	/**
	 * Method responsible to load all parameters 
	 * from XML file.
	 * 
	 * @return void
	 */
	private void loadParameters(){
		Properties prop = new Properties();
		 InputStream input = null;
		try {
			input = new FileInputStream("./resources/config.properties");
			
			/**
			 * Load the properties form config file
			 */
			prop.load(input);
			
			/**
			 * Set all the parameters from XML file
			 */
			setQuantityStockBuySell(Integer.parseInt(prop.getProperty("quantityStocks").replaceAll("\\s+","")));
			setMinStockPrice(Float.parseFloat(prop.getProperty("minStockPrice").replaceAll("\\s+","")));
			setMaxStockPrice(Float.parseFloat(prop.getProperty("maxStockPrice").replaceAll("\\s+","")));
			setIntervalToCalculateGBCEByTime(Integer.parseInt(prop.getProperty("intervalToCalculateGBCEByTime").replaceAll("\\s+","")));
			setIntervalGivenStockPrice(Integer.parseInt(prop.getProperty("intervalGivenStockPrice").replaceAll("\\s+","")));
			setIntervalCalculateVolumeWeightedStockPrice(Integer.parseInt(prop.getProperty("intervalCalculateVolumeWeightedStockPrice").replaceAll("\\s+","")));
			setIntervalToCreateStockFirstLoad(Integer.parseInt(prop.getProperty("intervalToCreateStockFirstLoad").replaceAll("\\s+","")));
		
		
//		System.out.println("++ quantityStocks ......................... : " + getQuantityStockBuySell());
//		System.out.println("++ minStockPrice .......................... : " + getMinStockPrice());
//		System.out.println("++ maxStockPrice .......................... : " + getMaxStockPrice());
//		System.out.println("++ intervalToCalculateGBCEByTime .......... : " + getIntervalToCalculateGBCEByTime());
//		System.out.println("++ intervalGivenStockPrice ................ : " + getIntervalGivenStockPrice());
//		System.out.println("++ intervalCalculateVolumeWeightedStockPrice: " + getIntervalCalculateVolumeWeightedStockPrice());
//		System.out.println("++ intervalToCreateStockFirstLoad ......... : " + getIntervalToCreateStockFirstLoad());
		
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					System.out.println("ERROR on load properties from config file.");
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	
//	private void showSystemSummary() {
//		
//		System.out.println(" SIZE: " + StockFactory.getInstance().getStockList().size());
//		
//		System.out.println("|---------------------------------------------------------------------------------------------------|");
//		System.out.println("|                                          SYSTEM SUMMARY                                           |");       
//		System.out.println("|---------------------------------------------------------------------------------------------------|");
//		System.out.println("| 1. System will load " + StockFactory.getInstance().getStockList().size() + " Stocks from XML file. \t\t\t\t\t\t\t    |");
//		System.out.println("| 2. Stock price varies from " + GbceUtils.doubleToStringWithTwoDecimalPlaces(getMinStockPrice()) + " to " + GbceUtils.doubleToStringWithTwoDecimalPlaces(getMaxStockPrice()) + " \t\t\t\t\t\t\t    |");
//		System.out.println("| 3. Maximum quantity of BUY or SELL Stocks varies between 1 and " + getQuantityStockBuySell() + " \t\t\t\t    |");
//		System.out.println("| 4. First Stock load has a delay of " + getIntervalToCreateStockFirstLoad() + " seconds to be created.   \t\t\t\t    |");
//		System.out.println("| 5. Interval of given Stock price occurs each " + (getIntervalGivenStockPrice() * 30) + " seconds,\t\t\t\t\t    |");
//		System.out.println("|    and calculates the Volume Weight Price for the respective Stock.                               |");
//		System.out.println("| 6. Every " + getIntervalToCalculateGBCEByTime() + ", the system will calculate the GBCE all share \t\t\t\t\t    |");
//		System.out.println("|    index base on each volume weight stock price of all shareds accumulated from when the system   |");
//		System.out.println("|    started run.                                                                                   |");
//		System.out.println("|---------------------------------------------------------------------------------------------------|"); 
//		
//		
//	}
	
	
	
	
	/**
	 * Getters and Setters
	 */
	public double getMinStockPrice() {
		return minStockPrice;
	}
	private void setMinStockPrice(double minStockPrice) {
		this.minStockPrice = minStockPrice;
	}
	public double getMaxStockPrice() {
		return maxStockPrice;
	}
	private void setMaxStockPrice(double maxStockPrice) {
		this.maxStockPrice = maxStockPrice;
	}
	public int getQuantityStockBuySell() {
		return quantityStockBuySell;
	}
	private void setQuantityStockBuySell(int quantityStockBuySell) {
		this.quantityStockBuySell = quantityStockBuySell;
	}
	public int getIntervalToCreateStockFirstLoad() {
		return intervalToCreateStockFirstLoad;
	}
	private void setIntervalToCreateStockFirstLoad(int intervalToCreateStockFirstLoad) {
		this.intervalToCreateStockFirstLoad = intervalToCreateStockFirstLoad;
	}
	public int getIntervalCalculateVolumeWeightedStockPrice() {
		return intervalCalculateVolumeWeightedStockPrice;
	}
	private void setIntervalCalculateVolumeWeightedStockPrice(
			int intervalCalculateVolumeWeightedStockPrice) {
		this.intervalCalculateVolumeWeightedStockPrice = intervalCalculateVolumeWeightedStockPrice;
	}
	public int getIntervalGivenStockPrice() {
		return intervalGivenStockPrice;
	}
	private void setIntervalGivenStockPrice(int intervalGivenStockPrice) {
		this.intervalGivenStockPrice = intervalGivenStockPrice;
	}
	public int getIntervalToCalculateGBCEByTime() {
		return intervalToCalculateGBCEByTime;
	}
	private void setIntervalToCalculateGBCEByTime(int intervalToCalculateGBCEByTime) {
		this.intervalToCalculateGBCEByTime = intervalToCalculateGBCEByTime;
	}	
	
}
