package com.my.atm;
/**
 * ATMMain
 * 
 * ATMMain is the main class for this ATM project.
 * This contains the main menu to perform different ATM transactions.
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
import java.util.Scanner;

import com.my.atm.util.ATMUtil;
import com.my.atm.util.Messages;

public class ATMMain {

	public static final Scanner input = new Scanner(System.in);

	
	/**
	 * Main method that initiates the whole ATM machine interface.
	 * @param args[] user input upon start-up
	 */
	public static void main(String args[]) {
		ATMUtil.initialize();
		boolean continueATM = true;
		ATMUtil.displayATMBalance();
		do {
			int menuCode = ATMUtil.showMenu();
			switch (menuCode) {
			case 1:
				ATMUtil.displayATMBalance();
				break;
			case 2:
				ATMUtil.showWithdraw();
				break;
			case 3:
				ATMUtil.showExit();
				continueATM = false;
				System.exit(0);
			default:
				System.out
						.println(Messages.ATM_OPTION_UNAVAILABLE.getMessage());
				ATMUtil.showMenu();
			}
		} while (continueATM);

	}

}
