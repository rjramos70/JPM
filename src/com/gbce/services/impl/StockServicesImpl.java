package com.gbce.services.impl;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.gbce.factories.impl.StockFactoryImpl;
import com.gbce.factories.impl.TradeFactoryImpl;
import com.gbce.models.Stock;
import com.gbce.models.Trade;
import com.gbce.models.enums.BuyOrCell;
import com.gbce.services.IStockServices;
import com.gbce.utils.GbceUtils;
import com.gbce.utils.SystemParameters;

/***
 * Class responsible for all Stock services
 * 
 * @author Renato Ramos
 * @date   15/10/2016
 */
public class StockServicesImpl implements IStockServices {
	
	/**
	 * Create Stock factory DAO object
	 */
	private StockFactoryImpl stockFactoryDAO = new StockFactoryImpl();
	/**
	 * Create Stock factory DAO object
	 */
	private TradeFactoryImpl tradeFactoryDAO = new TradeFactoryImpl();
	/**
	 * Create Stock service implementation object
	 */
	private static StockServicesImpl instance = null;

	/**
	 * Get singleton instance of StockServiceImpl
	 * @return an instance of StockServiceImpl object
	 */
	public static StockServicesImpl getInstance() {
		if (instance == null) {
	      instance = new StockServicesImpl();
	    }
	return instance;
	}
	
	/**
	 * SystemParameter class object to get all values 
	 */
	public SystemParameters systemParameter;
	
	
	/**
	 * Constructor(s)
	 */
	public StockServicesImpl(){
		/**
		 * Load the parameters 
		 */
		if (this.systemParameter == null) {
			this.systemParameter = new SystemParameters(stockFactoryDAO.getStockList().size());
		}
		
	}
	
	/**
	 * Methods responsible to generate a random stock price
	 */
	public double getRandomicStockPrice(){
		return GbceUtils.randomDoubleBetweenMinMax(this.systemParameter.getMinStockPrice(), this.systemParameter.getMaxStockPrice());
	}
	
	/**
	 * Method responsible to print all stocks available
	 * 
	 * @param void
	 * @return void
	 */
	private void printStockList(){
		/**
		 * Before load the Stock list, show the system Summary
		 */
		systemParameter.showSystemSummary();
		/**
		 * Print the stock list available loaded from the XML file
		 */
		System.out.println("|---------------------------------------------------------------------------------------------------|");
		System.out.println("|                                     STOCK LIST AVAILABLE                                          |");       
		System.out.println("|---------------------------------------------------------------------------------------------------|");
		for (String key : stockFactoryDAO.getStockList().keySet()) {
			System.out.println(stockFactoryDAO.getStockList().get(key).toString());
			System.out.println("|---------------------------------------------------------------------------------------------------|");
		}		
		System.out.println("|---------------------------------------------------------------------------------------------------|");
	    System.out.println("|                                                                                         Total: " + stockFactoryDAO.getStockList().size() + "  |");
		System.out.println("|---------------------------------------------------------------------------------------------------|");
	}
	
	/**
	   * Round up to number of places passed as parameter
	   * @param value
	   * @param places
	   * @return double
	   */
	  private static double round(double value, int places) {
	    // System.out.println("   round(double value...: " + value);
		BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_EVEN);
	    return bd.doubleValue();
	  }

	/**
	 * Method responsible to calculate the Dividend yield for common stock
	 * 
	 * @param stock
	 * @return double
	 */
	private double calculateCommonDividend(Stock stock, double randPrice){
		return stock.getLastDividend() / randPrice;
	}
	
	/**
	 * Method responsible to calculate the Dividend yield for preferred stock
	 * @param stock
	 * @return double
	 */
	private double calculatePreferredDividend(Stock stock, double randPrice){
		return ((stock.getFixedDividend()/100) * stock.getParValue()) / randPrice;
	}
	
	/**
	 * Method responsible to given stock price every interval
	 * 
	 * @param interval
	 * @return void
	 */
	private void generateStockPrice(int inter) {
		/**
		 * Check if the parameters is zero.
		 */
		if (inter == 0 || inter < 1) {
			inter = systemParameter.getIntervalGivenStockPrice();
		}
		
		Timer timer = new Timer();
		/**
		 * delay in seconds to start running the method.
		 */
		int delay = inter * 1000;   
		/**
		 * interval in minutes every 30 seconds to start.
		 */
		int interval = ((inter * 500) * 60);	
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				/**
				 * Start calculates method
				 */
				calculates();
			}
		/**
		 * Generate stock price every 30 seconds interval	
		 */
		}, delay, interval);
	}
	
	/**
	 * Method that starts the calculations
	 */
	private void calculates() {
		
		/**
		 * Iterate on the StockList and:
		 * 
		 * 1. Generate a random price;
		 * 2. Calculate Dividend Yield;
		 * 3. Calculate the P/E Ratio;
		 * 4. Calculate the Volume Weight based on trades in past 5 minutes
		 * 5. Update type Stock
		 * 6. Add trade to the list
		 */

		/**
		 * Looping on the stock list
		 */
		for (String symbol : getStockMapList().keySet()) {
			/**
			 * 1. Generate a random price for each Stock on the list
			 */
			double randPrice = getRandomicStockPrice();
			/**
			 * 2. Calculate Dividend Yield for each Stock
			 */
			getStockMapList().get(symbol).setDividendYield(round(calculateDividendYield(getStockMapList().get(symbol), randPrice), 4));
			/**
			 * 3. Calculate and set the P/E Ratio for each Stock
			 */
			getStockMapList().get(symbol).setPeRatio(round(calculatePERatio(getStockMapList().get(symbol), randPrice), 4));
			/**
			 * 4. Calculate and Set the Volume Weight
			 * For it Stock to all trades in past 5 minutes
			 */
			 getStockMapList().get(symbol).setVolumeWeightedBasedInPast5Minutes(round(calculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice(getTradeList(), getStockMapList().get(symbol), randPrice), 4));
			/**
			 * 5. Update type Stock
			 */
			Map<String, Integer> tmpStatus = calBuySellStock(getStockMapList().get(symbol));
			Trade tmpTrade = null;
			for (String tmpKey : tmpStatus.keySet()) {
				tmpTrade = new Trade(GbceUtils.getTimeStamp(), getStockMapList().get(symbol), tmpStatus.get(tmpKey), tmpKey, randPrice);
			}
			
			/**
			 * 6. Add trade to the list
			 */
			addToTradeList(tmpTrade);
				
		}

	}
	
	/***
	 * Method responsible to calculate, each interval of time, the GBCE for all 
	 * share index using geometric mean of the volume weight stock price for all stocks. 
	 * 
	 * @param void
	 * @return print Share list in the console
	 */
	private void runCalcGBCEByTime() {
		System.out.println("|  Wait, we are calculating the GBCE value every " + systemParameter.getIntervalToCalculateGBCEByTime() + " minutes..");
		System.out.println("|---------------------------------------------------------------------------------------------------|");
		/**
		 * Get the interval time to calculate the GBCE
		 */
		int interval = systemParameter.getIntervalToCalculateGBCEByTime();
		Timer timer = new Timer();
		
		/**
		 * Delay based on the interval in minutes.
		 */
		int delay = (interval * 1000) * 60;   
	    /**
	     * Create the schedule with a new TimeTask
	     */
		timer.schedule(new TimerTask() {
	    	@Override
			public void run() {
	    		/**
	    		 * Get all trades
	    		 */
	    		List<Trade> tmpTradeList = getTradeList();
    		
	    		/**
	    		 * Calculate the GBCE returning Double
	    		 */
	    		Double tmpCal1 = calculateGBCEReturningDouble(tmpTradeList);
	    		/**
	    		 * Print the GBCE share, quantity of stocks used in the formula, and the timestamp
	    		 */
	    		System.out.println("| GBCE share        : " + tmpCal1 
	    				       + "\n| Quantity of stocks: " + getTradeList().size() 
	    				       + "\n| Time              : " + GbceUtils.getTimeStamp().toString());
	    		System.out.println(" ========================================================");
	    		
	    		/**
	    		 * CAUTION:
	    		 * 		When the trades list is large, the double type can not store very large 
	    		 *      numbers, so we created a method with BigDecimal.
	    		 */
	    		
	    		
	    		/**
	    		 * Calculate the GBCE returning BigDecimal
	    		 */
//	    		 BigDecimal tmpCal2 = calculateGBCEReturningBigDecimal(tmpTradeList);
	    		
	    		/**
	    		 * Print the GBCE share, quantity of stocks used in the formula, and the timestamp
	    		 */
//	    		System.out.println("| tmpCal2:" 
//	    				       + "\n| GBCE share: " + tmpCal2 
//	    				       + "\n| Quantity of stocks:  " + getTradeList().size() 
//	    				       + "\n| Time: " + GbceUtils.getTimeStamp().toString());
//	    		System.out.println(" ========================================================");
	    		
	    		
			}
		}, delay, ((interval * 1000) * 60));
		
	}
	
	
	/**
	 * Method responsible to calculate the GBCE that runs over the trade list getting every 
	 * Stock volume weight and calculate the value every interval time
	 * 
	 * @param void
	 * @return BigDecimal
	 */
	public BigDecimal calculateGBCEReturningBigDecimal(List<Trade> tradeList) {
		
		BigDecimal total = new BigDecimal(1);
		/**
		 * 1. Loop for all trades
		 */
		for (int i = 0; i < tradeList.size(); i++) {
			total = total.multiply(BigDecimal.valueOf(tradeList.get(i).getStock().getVolumeWeightedBasedInPast5Minutes())).setScale(4, RoundingMode.HALF_EVEN);
		}
		return sqrt(total).setScale(4, RoundingMode.HALF_EVEN);
	}
	
	/**
	 * Method responsible to calculate the GBCE that runs over the trade list getting every 
	 * Stock volume weight and calculate the value every interval time
	 * 
	 * @param void
	 * @return Double
	 */
	public Double calculateGBCEReturningDouble(List<Trade> tradeList) {
		/**
		 * Return variable
		 */
		Double total = 1.0;
		/**
		 * 1. Loop for all trades
		 */
		for (int i = 0; i < tradeList.size(); i++) {
			/**
			 * Multiply each new volume weight price buy total
			 */
			total *= tradeList.get(i).getStock().getVolumeWeightedBasedInPast5Minutes();
		}
	return Math.pow(total, (1D / tradeList.size()));
	}

	/**
	 * Method 
	 * @param value
	 * @return square root from the parameter number
	 */
	public static BigDecimal sqrt(BigDecimal value) {
		/**
		 * Create a BigDecimal instance
		 */
	    BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
	    return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() / (x.doubleValue() * 2.0)));
	}

	
	/**
	 * Get trade list
	 * @return List<Trade>
	 */
	public List<Trade> getTradeList() {
		return tradeFactoryDAO.getTradeList();
	}
	
	/**
	 * Set trade list
	 * @param tradeList
	 */
	public void setTradeList(List<Trade> tradeList) {
		tradeFactoryDAO.setTradeList(tradeList);
	}
	
	/**
	 * Insert in the trade list
	 */
	public void insertTradeList(Trade trade){
		tradeFactoryDAO.addToTradeList(trade);
	}
	
	/**
	 * Method responsible to add a new Trade on the main list
	 * @param trade
	 */
	private void addToTradeList(Trade trade){
		tradeFactoryDAO.addToTradeList(trade);
	}
	
	/**
	 * Get all stock available from the list
	 * @return Map<String, Stock>
	 */
	private Map<String, Stock> getStockMapList(){
		return stockFactoryDAO.getStockList();
	}
	
	/**
	 * Method responsible to calculate the Volume Weight for specific  
	 * Stock, based on all trades in past 5 minutes
	 *  
	 * @param interval
	 * @return void
	 */
	@Override
	public double calculateVolumeWeightedPriceForStockBasedOnTradelistStockAndRandomicPrice(List<Trade> tradeList, Stock stock, double randPrice){
		/**
		 * IF tradeList is empty, return the randPrice. 
		 */
		if (tradeList.size() < 1) {
			return randPrice;
		}else{
			/**
			 * ELSE read all registers
			 */
			double volumeWeight = 0;
			
			/**
			 * Calculate the time 5 minutes before
			 */
			int fiveMinutesBefore = 5;	
			Date date = new Date(System.currentTimeMillis() - (fiveMinutesBefore*60*1000));
			Timestamp lastTime = new Timestamp(date.getTime());
			
			/**
			 * Get the current time
			 */
			Timestamp currentTime = GbceUtils.getTimeStamp();
			double priceByQuantity = 0;
			double quantity = 0;
			
			/**
			 * Looping the trade list
			 */
			for (int i = 0; i < tradeList.size(); i++) {
				/**
				 * Identifies the Stock based on the symbol
				 */
				if (tradeList.get(i).getStock().getSymbol().equalsIgnoreCase(stock.getSymbol())) {
					/**
					 * Get Trades between lastTime(-5 minutes) and current time
					 */
					if (tradeList.get(i).getTimeStamp().getTime() >= lastTime.getTime() && tradeList.get(i).getTimeStamp().getTime() <= currentTime.getTime()) {						
						/**
						 * Multiply tha Stock price by quantity of Stocks bought or sold
						 */
						priceByQuantity += randPrice * tradeList.get(i).getQuantity();
						
						/**
						 * Accumulated amount of Stocks
						 */
						quantity += tradeList.get(i).getQuantity();
					}
			
					/**
					 * IF price by quantity equals to zero and quantity equals to zero, return zero; 
					 */
					if (priceByQuantity == 0 && quantity == 0) {
						volumeWeight = 0;
					}else{
						/**
						 * ( Trade Stock Price * Stock Quantity ) / Accumulated amount of Stocks
						 */
						volumeWeight = priceByQuantity / quantity;
					}

				}
						
			}
		/**
		 * Return the Volume Weight
		 */
		return volumeWeight;	
		}
		
		
	}
	
	/**
	 * Method responsible for calculating the amount of stocks that will be 
	 * bought or sold and quantity
	 * @param stock
	 * @return Map<String, Integer>
	 */
	public static Map<String, Integer> calBuySellStock(Stock stock){
		/**
		 * Create a temporary instance of a HashMap list
		 */
		Map<String, Integer> resultCalStock = new HashMap<String, Integer>();
		/**
		 * Create a temporary instance of SystemParameters
		 */
		SystemParameters tmpParameters = new SystemParameters();
		String str = null;
		int qtd = 0;
		/**
		 * IF stock price >= stock Par Value
		 */
		if (stock.getPrice() >= stock.getParValue()) {
			/**
			 * SELL Stock
			 */
			str = BuyOrCell.SELL.name();
			/**
			 * Quantity to be purchased
			 */
			qtd = (int) ( GbceUtils.randomIntBetweenMinMax(1, tmpParameters.getQuantityStockBuySell()));
		}else{
			/**
			 * BUY Stock
			 */
			str = BuyOrCell.BUY.name();
			/**
			 * Quantity to be sold
			 */
			qtd = (int) ( GbceUtils.randomIntBetweenMinMax(1, tmpParameters.getQuantityStockBuySell()));
		}
		/**
		 * Insert Stock and Quantity on the Map
		 */
		resultCalStock.put(str, qtd);
		/**
		 * Return the Map
		 */
		return resultCalStock;
	}
	
	/**
	 * Get Stock list
	 * @return
	 */
	public Map<String, Stock> getStockList() {
		
		return stockFactoryDAO.getStockList();
	}

	/**
	 * Add stock to the list
	 */
	@Override
	public void addStock(Stock stock) {
		stockFactoryDAO.addStock(stock);
	}

	/**
	 * Get Stock by the symbol
	 */
	@Override
	public Stock getStock(String symbol) {
		return stockFactoryDAO.getStock(symbol);
	}	
	
	/**
	 * Method responsible to calculate the Dividend 
	 * Yield based on the type of Stock
	 * @param stock
	 * @return
	 */
	@Override
	public double calculateDividendYield(Stock stock, double randPrice) {
		double ret = 0;
		/**
		 * Base on what type of Stock, calculate the Dividend Yield
		 * IF Common
		 */
		if (stock.getType().equalsIgnoreCase("Common")) {
			ret = calculateCommonDividend(stock, randPrice);
		}
		/**
		 * IF Preferred
		 */
		if (stock.getType().equalsIgnoreCase("Preferred")) {
			ret = calculatePreferredDividend(stock, randPrice);
		}
	/**
	 * Return dividend yield value 	
	 */
	return ret;
	}

	/**
	 * Method responsible to calculate the P/E Ratio for preferred stock
	 * @param stock
	 * @return double
	 */
	@Override
	public double calculatePERatio(Stock stock, double randPrice) {
		/**
		 * IF the last dividend EQUALS zero, return 0.0
		 */
		if (stock.getLastDividend() == 0.0) {
			return 0.0;
		}
	/**
	 * ELSE return random price / stock last dividend
	 */
	return randPrice / stock.getLastDividend();
	}

	/**
	 * Start running the program 
	 * 
	 */
	public void run(){
		/**
		 * Load and Print stock list	
		 */
		printStockList();
		
		/**
		 * Generate the stock price every interval time
		 */
		generateStockPrice(systemParameter.getIntervalGivenStockPrice());
		
		/**
		 * Every interval run to calculate the GBCE price.
		 */
		runCalcGBCEByTime();
	}
	
}


