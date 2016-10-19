package com.gbce.factories.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gbce.factories.IStockFactory;
import com.gbce.models.Stock;
import com.gbce.utils.SystemParameters;

/***
 * Class factory responsible to implement DAO functionalities.
 * 
 * @author Renato Ramos
 * @date   16/10/2016
 *
 */
public class StockFactoryImpl implements IStockFactory {
	/**
	 * Create Map stock list
	 */
	private Map<String, Stock> stockList = new HashMap<String, Stock>();
	
	/**
	 * Create a instance of SystemParameters
	 */
	SystemParameters systemParameter = new SystemParameters();
	
	/**
	 * Constructor
	 */
	public StockFactoryImpl(){
		/**
		 * Load the Stock list
		 */
		loadStockList();

	}
	
	/**
	 * Method responsible to load the Stock data from the XML resource file
	 * @return void
	 */
	public void loadStockList(){
		File xmlFile = null;
		try {
			/**
			 * Create a File object
			 */
			xmlFile = new File("resources/StockList.xml");
			/**
			 * Create a instance of DocumentBuilderFactory
			 */
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			/**
			 * Create a instance of DocumentBuilder
			 */
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			/**
			 * Create a inbstance of Document parsing File
			 */
			Document doc = dBuilder.parse(xmlFile);
			/**
			 * Normalize the Document
			 */
			doc.getDocumentElement().normalize();
			/**
			 * Get the parent node
			 */
			NodeList nList = doc.getElementsByTagName("tns:Stock");
			/**
			 * Get all element node	
			 */
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					/**
					 * Insert a new Stock on the list
					 */
					stockList.put(eElement.getElementsByTagName("tns:symbol").item(0).getTextContent(), new Stock(eElement.getElementsByTagName("tns:symbol").item(0).getTextContent(), 
							  					  eElement.getElementsByTagName("tns:type").item(0).getTextContent(), 
							  					  Double.parseDouble(eElement.getElementsByTagName("tns:lastDividend").item(0).getTextContent()), 
							  					  Double.parseDouble(eElement.getElementsByTagName("tns:fixedDividend").item(0).getTextContent()),
							  					  Double.parseDouble(eElement.getElementsByTagName("tns:parValue").item(0).getTextContent())));

				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Get system parameter 
	 * @return SystemParameters
	 */
	public SystemParameters getSystemParameter() {
		return systemParameter;
	}
	/**
	 * Get Stock list
	 * @return
	 */
	public Map<String, Stock> getStockList() {
		
		return this.stockList;
	}
	
	/**
	 * Add stock to the list
	 */
	@Override
	public void addStock(Stock stock) {
		this.stockList.put(stock.getSymbol(), stock);
		
	}
	/**
	 * Get stock from the list base on stock symbol
	 */
	@Override
	public Stock getStock(String symbol) {
		Stock ret = null;
		for (String key : stockList.keySet()) {
			if (stockList.get(key).getSymbol().equalsIgnoreCase(symbol)) {
				ret = stockList.get(key);
			}
		}
	return ret;
	}
	

	
}
