package com.my.atm.object;

/**
 * Result
 * 
 * Class representing the Withdraw Transction Result object.
 * Contains information if the withdraw request is successful or not.
 * Information on the bills to be dispensed is also included if the 
 * withdraw request is succesful.
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
import java.util.List;

public class Result {
	
	private boolean successFlag = false;
	
	private List<Bills> listOfBills = new ArrayList<Bills>();
	
	/**
	 * Result successFlag getter
	 */
	public boolean isSuccessFlag() {
		return successFlag;
	}
	/**
	 * Result successFlag setter
	 * @param boolean successFlag value
	 */
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	/**
	 * Adds a new bill this Result's List<Bills> object
	 * @param Bills the bill object to be added
	 */
	public void addBills(Bills bill){
		listOfBills.add(bill);
	}
	/**
	 * Returns the List<Bills> of this withdrawal transaction Result 
	 * @return List<Bills> the List<Bills> object to be 
	 */
	public List<Bills> getListOfBills() {
		return listOfBills;
	}
	
	


}
