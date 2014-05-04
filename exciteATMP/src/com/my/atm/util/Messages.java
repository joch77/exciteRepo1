package com.my.atm.util;

/**
 * Messages
 * 
 * Enum for this ATM's validation and error messages
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

public enum Messages {
  NO_DEMONINATION(0, "Desired amount is not possible on the available denominations."),
  INSUFFICIENT_BALANCE(1, "ATM does not have enough balance to dispense desired amount."),
  ATM_OPTION_UNAVAILABLE(2, "Option selected is not available in the menu."),
  NEGATIVE_AMOUNT(3, "Please enter an amount greater than zero.");
  
  private final int code;
  private final String description;
  private final String message;
  
 /**
   * Messages Constructor
   * @param int code for this message
   * @param String description for this message
   */
  private Messages(int code, String description) {
    this.code = code;
    this.description = description;
    this.message = "MSG"+this.code + ": " + this.description;
  }

  /**
   * Messages description getter
   * @return the message's description
   */
  public String getDescription() {
     return description;
  }

  /**
   * Messages code getter
   * @return int the message's code
   */
  public int getCode() {
     return code;
  }

  /**
   * Messages message details getter
   * @return the message's details
   */
  public String getMessage() { return message; }
}