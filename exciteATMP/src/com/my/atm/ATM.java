package com.my.atm;
/**
 * ATM
 * 
 * ATM Singleton class. This contain methods to perform ATM transactions.
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.my.atm.object.Bills;
import com.my.atm.object.Result;
import com.my.atm.util.ATMUtil;
import com.my.atm.util.BillComparator;

public class ATM {

	static private ATM _instance = null;
	static private long balance = 0;
	static private List<Bills> listOfBills = new ArrayList<Bills>();
	
	/**
	 * ATM private constructor.
	 * List<Bills> the List object containing all  possible Notes to be held
	 * by this ATM.
	 */
	private ATM(List<Bills> billsfromConfig) {
		//sort the bills such that the biggest denomination
		//is saved into the lower index
		Collections.sort(billsfromConfig,new BillComparator());
		ATM.listOfBills = billsfromConfig;
		
		setBalance();
	}
		
	/**
	 * This method creates an instance of the ATM object if one has not yet
	 * been created. This single instance is returned when it is already 
	 * existing.
	 * 
	 * @return ATM ATM Singleton instance
	 * List<Bills> the List object containing all Notes held
	 * by this ATM.
	 * 
	 */
	public static ATM instance(List<Bills> Bills) {
		if (_instance == null) {
			_instance = new ATM(Bills);
		}
		return _instance;
	}
	
	/**
	 * This method triggers the update of the ATM's balance.
	 * The method reads the contents of the List<Bills> attribute 
	 * of this ATM.
	 * 
	 */
	public static void setBalance(){
		
		Iterator<Bills> iterator = listOfBills.iterator();
		balance = 0;
		while(iterator.hasNext()){
			Bills currBill = (Bills)iterator.next();
			balance += currBill.getBillAmount();
		}
	}
	
	
	/**
	 * This method returns the List<Bills> attribute of this ATM class
	 * List<Bills> the List object containing all Notes held
	 * by this ATM.
	 * 
	 */
	public static List<Bills> getListOfBills() {
		return listOfBills;
	}
	
	/**
	 * This method returns the instance of this ATM class
	 * @return ATM the ATM single instance
	 */
	public static ATM instance(){
		
		return _instance;
	}

	/**
	 * This method returns a long value representing
	 * this ATM's balance
	 * @return balance the total amount of money held 
	 * by this ATM.
	 */
	public static long getBalance() {
		return balance;
	}
	
	/**
	 * This method returns a boolean value indicating
	 * if the ATM has sufficient balance to withdraw
	 * the amount parameter
	 * @return boolean value to indicate if ATM's
	 * balance is sufficient or not
	 * @param amount the amount to be withdraw in long
	 */
	public static boolean isBalanceSufficient(long amount){
		
		if (amount <= getBalance()) {
			return true;
		}
		return false;

	}
	
	/**
	 * This method returns a boolean value indicating
	 * if the amount requested to be withdrawn is
	 * within the allowed range.
	 * @return boolean value to indicate if the amount
	 * to be withdrawn is a valid amount.
	 * @param amount the amount to be withdraw in long
	 */
	public static boolean iValidAmount(long amount){
		
		if (amount>0){
			
			return true;
			
		}
		return false;
	}
	
	/**
	 * This method returns a boolean value indicating
	 * if the requested amount to be withdrawn is
	 * possible on the available denominations
	 * in this ATM.
	 * @return boolean value to indicate if the amount
	 * to be withdrawn is possible on the available
	 * Bill denominations. 
	 * @param amount the amount to be withdraw in long
	 */

	public static Result isDenomAvailable(long amount) {


		Result withdrawResult = new Result();

		long amountBalance = amount;
		withdrawResult.setSuccessFlag(Boolean.TRUE);
		
		int i = 0;
		amountBalance = amount;
		while(i<ATM.getListOfBills().size() && amountBalance!=0){	
			amountBalance = computeDenom(ATM.getListOfBills().get(i),withdrawResult,amountBalance);
			i++;
		}
		if (amountBalance!=0){
			withdrawResult.setSuccessFlag(Boolean.FALSE);
		}
		return withdrawResult;
	}
	/**
	 * This method returns a long value indicating
	 * the amount not able to be withdrawn from the ATM.
	 * 
	 * @return amount balance which the system will try to
	 * deduct from the other remaining bills available on the ATM.
	 * @param bill Bill object, containing currency denomination and available amount in
	 * pcs in the system
	 * @param withdrawResult Result objecting representing the combination of bills 
	 * for this requested withdrawal transaction
	 * @param amount the amount to be withdraw in long
	 */
	private static long computeDenom(Bills bill,Result withdrawResult,long amount){
		
		long denomAmnt = bill.getBillDenom(); 
		long denomPcs = bill.getBillPcs(); 
		long available_Amnt = denomAmnt*denomPcs; 
	    long required_pcs = amount/denomAmnt;
	    long balance = 0;
        
	    if (available_Amnt<amount){
	    	 required_pcs = denomPcs;
		     balance =  (amount - available_Amnt);
		      
	    }else{
	    	required_pcs  = amount/denomAmnt;
	        balance =  (amount -(required_pcs*denomAmnt));
	    }
	    Bills newbill = new Bills(bill.getBillID(),bill.getBillDenom(),required_pcs);
	    newbill.setBillPcs(required_pcs);
	    withdrawResult.addBills(newbill);
	    return balance;		
	}
	
	/**
	 * This method initiates the withdraw transaction on this ATM.
	 * 
	 * @param amount the amount to be withdraw in long
	 * @param withdrawResult Result objecting representing the combination of bills 
	 * for this requested withdrawal transaction
	 */
	public static void withdraw(long amount, Result result){
				int i=0;
				while(i<result.getListOfBills().size()){	
					ATM.getListOfBills().get(i).setBillPcs((ATM.getListOfBills().get(i).getBillPcs()) - (result.getListOfBills().get(i).getBillPcs()));
					i++;
				}
				ATM.setBalance();
		
	}

}
