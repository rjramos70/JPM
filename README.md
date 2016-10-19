# JPM
Super Simple Stock Market Assignment
/*
 * Header		: /GBCE/README.txt 
 * Project Name	: GBCE
 * Author		: Renato Jiquirica Ramos
 * Version		: 1.0 
 * Date			: 15/10/2016 
 *
 * ====================================================================
 *
 * Copyright (C) 2016 by Global Beverage Corporation Exchange
 *
 * This system is developed to help Global Beverage Corporation  
 * Exchange, a new stock market trading for drinks companies; you 
 * can not redistribute it and/or modify it under the terms of 
 * the GBCE License; either version 2 of the License, or any 
 * later version.
 *
 * ====================================================================
 *  API REFERENCE
 * ====================================================================
 * To run test scripts, it is necessary to have JUnit installed, 
 * download the junit.jar in www.junit.org after add it to the 
 * class path to compile and run the test programs. 
 *
 * All security bugs should be sent directly to support@gbce.com and
 * the subject should begin with [GBCE BUG]
 * 
 * GBCE is a java project.This release has only been tested on the 
 * following configurations:
 * on the following databases:
 *   * ???? version ?.??.??
 *   * ???? version ?.??.??
 *   * ???? version ?.??.??
 *
 * on the following App Servers:
 *   * ???? version ?.?.??
 *   * ???? version ?.?.??
 *   * ???? version ?.?.??
 *
 * on the following JDK:
 *   * Sun JDK 1.8
 *
 * on the following OS:
 *   * Windows version 7
 *   * MacOS X Yosemite version 10.10.5
 *
 * Example program can reached at:
 * Voice	:	123-999-8765
 * Website	:	www.gbce.com
 * E-mail	:	contact@gbce.com
 *
 * Support can be obtained from support web site page at:
 * http://www.bgce.com/support/
 * 
 * All copyright notices regarding GBCE
 * MUST remain intact in the scripts
 *
 * ====================================================================
 *  HOW TO RUN
 * ====================================================================
 *
 * To run and test this project, check the config.properties file, this 
 * class has all general parameters of the system, we can updated any 
 * parameters to change the program performance.
 *
 *
 * ====================================================================
 *  PROBLEMS
 * ====================================================================
 *
 * During the testing phase, we found that when the trades list is too 
 * large, the method "calculateGBCEReturningDouble" in the class 
 * "StockServicesImpl" is generating very large numbers, this numbers
 * bigger than the possible number to be stored in a Double type.
 * To try to solve this problem, we created another method name 
 * "calculateGBCEReturningBigDecimal", in this method we are working
 * with BigDecimal types using the same geometric mean, but comparing
 * the values between this two methods, the results are different 
 * between them.
 *
 * 
 * During the test fase, with the quantity of from minimum parameters and maximun values, until the 
 * amount of stocks
 *
 * Class responsible for all external properties 
 *
 *
 * =======================================================================
 *   PROBLEMS DURING THE DEVELOPMENT PHASE
 * =======================================================================
 *
 * 15/10/2016
 * ==========
 *
 * A)  In the application 2.a.i, "Given the price any input", means that I 
 *     have to generate random prices for each Stock?
 *
 * B) In the application 2.a.iii, what criteria to BUY and SELL one stock? 
 *    How do you calculate the amount? This price that I have generated 
 *    randomly ? 
 *
 * C) In the calculation of P / E Ratio, the dividend and the "dividend 
 *    yield" or "Last Dividend" or "Fixed Dividend"? According to my web 
 *    research, the formula on the Assignment is not correct is not correct 
 *    dividing Price on Dividend, but dividing Price by earnings, I did not 
 *    have return, I used the formula as in the Assignment form.
 *
 *
 *
 *
 *
 *
 */




