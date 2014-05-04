package com.my.atm.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.my.atm.object.Bills;

public class Test {

	private static List<String> denomIDList = null;
	private static List<String> denomDenomsList = null;
	public static final Scanner input = new Scanner(System.in);

	/**
	 * 
	 * @return list of IP prefixes.
	 * 
	 *         Method to populate the ipHigh list with the 'ipHighs' enumerated
	 *         on the config.properties file
	 * 
	 */
	private static void initDenominations() {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			String filename = "config.properties";
			input = Test.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			String denomIDFromConfig = (String) (prop.getProperty("denomID"));
			String denomDenomsFromConfig = (String) (prop
					.getProperty("denomDenom"));

			denomIDList = Arrays.asList(denomIDFromConfig.split("\\s*,\\s*"));
			denomDenomsList = Arrays.asList(denomDenomsFromConfig
					.split("\\s*,\\s*"));

			System.out.println("Retrieved Denomination IDs:"
					+ denomIDList.toString());
			System.out.println("Retrieved Denomination Denoms:"
					+ denomDenomsList.toString());

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String args[]) {

		initDenominations();

		System.out
				.println("====================== ATM Initialization Setup =====================");
		ATMUtil.displayDivider();
		List<Bills> listOfBills = new ArrayList<Bills>();
		for (int i = 0; i < denomDenomsList.size(); i++) {
			System.out.println("Enter amount (in pcs) of "
					+ denomDenomsList.get(i) + " $:");
			Bills newbill = new Bills(denomIDList.get(i),
					Long.parseLong(denomDenomsList.get(i)), input.nextInt());
			listOfBills.add(i, newbill);
		}
		System.out.println("ListOfBills Size" + listOfBills.size());
		
		for (int i = 0; i < listOfBills.size(); i++) {
			
			System.out.println("ID["+i+"]"+listOfBills.get(i).getBillID());
			System.out.println("Denom["+i+"]"+listOfBills.get(i).getBillDenom());
			System.out.println("Amount["+i+"]"+listOfBills.get(i).getBillAmount());
			
		}

	}
}
