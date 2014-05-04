package com.my.atm.object;

/**
 * Bills
 * 
 * Class representing the Bill/Note object.
 * Contains information on the bill ID, bill denomination and available quantity
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

public class Bills {
	
	private String billID;
	private long billDenom = 0;
	private long billPcs = 0;

	
	/**
	 * Bills constructor
	 * @param billID String ID for this bill
	 * @param billDemo denomination for this bill
	 * @param billPcs quantity to initialize for this bill
	 */
	public Bills(String billId,long billDenom, long billPcs){
		
		this.billID = billId;
		this.billDenom = billDenom;
		this.billPcs = billPcs;
	}
	
	/**
	 * Bills denomination getter
	 */
	public long getBillDenom() {
		return billDenom;
	}

	/**
	 * Bills denomination setter
	 * @param billDenom this bill's denomination
	 */
	public void setBillDenom(long billDenom) {
		this.billDenom = billDenom;
	}

	/**
	 * Bill Pcs getter
	 */
	public long getBillPcs() {
		return billPcs;
	}

	/**
	 * Bill Pcs setter
	 * @param billPcs quantity to set for this Bill
	 */
	public void setBillPcs(long billPcs) {
		this.billPcs = billPcs;
	}

	/**
	 * Bill ID getter
	 */
   public String getBillID() {
		return billID;
	}

	/**
	 * Bill ID setter
	 * @param bill ID to set for this Bill
	 */
	public void setBillID(String billID) {
		this.billID = billID;
	}

	/**
	 * Method to trigger bill amount setting
	 */
    public long getBillAmount(){
	   return billDenom*billPcs;
   }

}
