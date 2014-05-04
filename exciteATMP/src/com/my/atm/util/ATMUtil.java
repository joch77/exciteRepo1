package com.my.atm.util;

/**
 * ATMUtil
 * 
 * Utility class for performing ATM transactions
 * 
 * @author jochebed
 * @version 1.0
 * 
 * Change History:
 * --------------------------------------------------------------------------------------------
 * Date 		DTS	ID  		Modified By			Brief Description of Change
 * --------------------------------------------------------------------------------------------
 * 20140502         		 	 jochebed				initial version
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.my.atm.ATM;
import com.my.atm.object.Bills;
import com.my.atm.object.Result;

public class ATMUtil {

	private static List<String> denomIDList = null;
	private static List<String> denomDenomsList= null;
	public static final Scanner input = new Scanner(System.in);

	/**
	 * 
	 * Reads a list of all possible currency denominations from the config.properties 
	 * file which will be used to initialize the ATM's initial balance
	 * 
	 */
	private static void initDenominations() {
		
		Properties prop = new Properties();
    	InputStream input = null;
    	
		try {
		
			String filename = "config.properties";
    		input = Test.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		    return;
    		}
 
    		//load a properties file from class path, inside static method
    		prop.load(input);
			

			String denomIDFromConfig = (String) (prop.getProperty("denomID"));
			String denomDenomsFromConfig = (String) (prop.getProperty("denomDenom"));
			
			denomIDList = Arrays.asList(denomIDFromConfig.split("\\s*,\\s*"));
			denomDenomsList = Arrays.asList(denomDenomsFromConfig.split("\\s*,\\s*"));
			System.out
			.println("======== Initializing Demominations from Properties file ===========");
			System.out.println("Retrieved Denomination IDs:"+ denomIDList.toString());
			System.out.println("Retrieved Denomination Denoms:"+ denomDenomsList.toString());
			displayDivider();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * Method for displaying the ATM's current balance
	 */
	public static void displayATMBalance() {
		System.out
				.println("======================== ATM Current Balance =======================");

		int i = 0;
		while (i < ATM.getListOfBills().size()) {
			System.out.println(ATM.getListOfBills().get(i).getBillPcs()
					+ " Pc(s) of " + ATM.getListOfBills().get(i).getBillDenom()
					+ " Dollar Bill(s)");
			i++;
		}
		System.out.println("Total Balance in Dollars " + ATM.getBalance());
		displayDivider();
	}
	
	/**
	 * Method for initializing the ATM's starting balance
	 */
	public static void initialize() {
		initDenominations();
		System.out
				.println("====================== ATM Initialization Setup =====================");
		ATMUtil.displayDivider();
		List<Bills> listOfBills = new ArrayList<Bills>();
		for (int i = 0; i < denomDenomsList.size(); i++) {
			System.out.println("Enter amount (in pcs) of "+ denomDenomsList.get(i)+ " $:");
		      Bills newbill = new Bills(denomIDList.get(i),Long.parseLong(denomDenomsList.get(i)),input.nextInt());
		      listOfBills.add(i,newbill);
		}

		ATMUtil.displayDivider();
		ATM.instance(listOfBills);
	}
	/**
	 * Method for displaying this ATM's main menu
	 */

	public static int showMenu() {
		int menuCode = 0;
		if (ATM.instance() != null) {
			System.out
					.println("================================= ATM Menu ==========================");
			System.out.println("Enter (1) to Inquire ATM Balance");
			System.out.println("Enter (2) to Withdraw Cash");
			System.out.println("Enter (3) to Exit ATM");
			displayDivider();

			menuCode = input.nextInt();
		}

		return menuCode;
	}
	
	/**
	 * Method to end the ATM's session
	 */

	public static void showExit() {
		System.out
				.println("============================= Goodbye ==============================");

	}

	/**
	 * Method to initiate Withdraw Mode for this ATM
	 */

	public static void showWithdraw() {

		System.out
				.println("=========================== Withdraw Menu ===========================");
		System.out.println("Enter desired amount:");
		long amount = input.nextInt();
		displayDivider();
		
		if(ATM.iValidAmount(amount)){
			// check if has enough amount for amount requested
			if (ATM.isBalanceSufficient(amount)) {
				Result result = ATM.isDenomAvailable(amount);
				if (result.isSuccessFlag()) {
					ATM.withdraw(amount, result);
					displayResult(result);
				} else {
					System.out.println(Messages.NO_DEMONINATION.getMessage());
				}
			} else {
				System.out.println(Messages.INSUFFICIENT_BALANCE.getMessage());
			}
		}else{
			System.out.println(Messages.NEGATIVE_AMOUNT.getMessage());
		}
		
		

	}

	public static void displayResult(Result result){
		int i=0;
		displayDivider();
		System.out.println("Dispensing:");
		while(i<result.getListOfBills().size()){	
			System.out.println(result.getListOfBills().get(i).getBillPcs()+ " Pc(s) of " + result.getListOfBills().get(i).getBillDenom() +" Dollar Bill(s)");
			i++;
		}
		displayDivider();
	}
	
	public static void displayDivider() {
		System.out
				.println("=====================================================================");

	}

}
