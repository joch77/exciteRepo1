package com.my.atm.util;

/**
 * BillComparator
 * 
 * Custom Comparator for comparing Bills object.
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

import java.util.Comparator;

import com.my.atm.object.Bills;

public class BillComparator implements Comparator<Bills> {
	  
    /**
	* Override implementation of Object compare method.
	* This method compares two Bills object based on their
	* assigned denomination value.
	* 
	* @return int value returns -1 if b1 denomination is 
	* greater than b2 and 1 if b1 denomination is less than 
	* b2 denomination. This effectively results to result
	* to a descending order when comparison is used for
	* sorting.
	*/
	@Override
	public int compare(Bills b1, Bills b2) {
		if (b1.getBillDenom() > b2.getBillDenom()) {
			return -1;
		} else if (b1.getBillDenom() < b2.getBillDenom()) {
			return 1;
		} else
			return 0;
	}
}