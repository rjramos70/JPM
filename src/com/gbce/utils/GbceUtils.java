package com.gbce.utils;

import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;

public class GbceUtils extends Random {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * @parameter minimum value. Can not be more then maximum
	 * @parameter maximum value 
	 * 
	 * @return int values between [minimum, maximum]
	 */
	public static int randomIntBetweenMinMax(int minimum, int maximum){
		Assert.assertFalse("minimum value can not be more then maximum; [" + minimum +", " + maximum +" ]", minimum > maximum);
		if (minimum == maximum) {
			return maximum;
		}
		return minimum + (int)(Math.random() * ((maximum - minimum) + 1));
	}
	
	public static float randomFloatBetweenMinMax(float minimum, float maximum){
		Assert.assertFalse("minimum value can not be more then maximum; [" + minimum +", " + maximum +" ]", minimum > maximum);
		if (minimum == maximum) {
			return maximum;
		}
		String strVal = String.format("%.2f", minimum + (float)(Math.random() * ((maximum - minimum) + 1)));
		
		return Float.parseFloat(strVal);
				
		// return minimum + (float)(Math.random() * ((maximum - minimum) + 1));
	}
	
	public static double randomDoubleBetweenMinMax(double minimum, double maximum){
		Assert.assertFalse("minimum value can not be more then maximum; [" + minimum +", " + maximum +" ]", minimum > maximum);
		if (minimum == maximum) {
			return maximum;
		}
		
		String strVal = String.format("%.2f", minimum + (Math.random() * ((maximum - minimum) + 1))).replace(",", ".");
		return Double.parseDouble(strVal);
	}
	
	public static Timestamp getTimeStamp(){
		Date date = new Date();
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}
	
	public static double doubleToDoubleWithTwoDecimalPlaces(String value){
		return Double.parseDouble(String.format("%.2f", value));
	}
	
	public static String doubleToStringWithTwoDecimalPlaces(double doubleValue){
		NumberFormat dataForm = DecimalFormat.getInstance();
		dataForm.setMinimumFractionDigits(2);
		dataForm.setMaximumFractionDigits(4);
		dataForm.setRoundingMode(RoundingMode.DOWN);
		return dataForm.format(doubleValue);
	}
	
	public String stringValueWithTwoDecimalPlaces(String value){
		return String.format("%.2f", value);
	}
	
	public static void clearConsole() {
	    try {
	      final String os = System.getProperty("os.name");
	      if (os.contains("Windows")) {
	          // Runtime.getRuntime().exec("cls");
	    	  Runtime.getRuntime().exec("cmd /c cls");   
	      } else {
	          Runtime.getRuntime().exec("clear");
	      }
	    } catch (Exception e) {
	        System.out.println(" Something wrong cleaning console : ".concat(e.getMessage()));
	        e.printStackTrace();
	    	//mConsole.printf("something went wrong :(");
	    }
	  }

	
}
