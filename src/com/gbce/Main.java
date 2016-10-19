package com.gbce;


import com.gbce.services.impl.StockServicesImpl;

/**
 * Main class of the project, created to start and run the application.
 * @author Renato Ramos
 * @date   16/10/2016
 */
public class Main {
		
	public static void main(String[] args) {
		/**
		 * Create an instance of StockServicesImpl class 
		 */
		StockServicesImpl service = new StockServicesImpl();
		/**
		 * Run the application
		 */
		service.run();

	}
	
}
