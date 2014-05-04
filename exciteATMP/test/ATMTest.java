import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.my.atm.ATM;
import com.my.atm.object.Bills;
import com.my.atm.util.ATMUtil;
import com.my.atm.util.BillComparator;

public class ATMTest extends TestCase{
	
	  public void setUp () {
	      //simulate bill intialization from config.properties file
		  List<Bills> listOfBills = new ArrayList<Bills>();
		  listOfBills.add(0,new Bills("FIFTY",50,3));
		  listOfBills.add(0,new Bills("TWENTY",20,2));
		  Collections.sort(listOfBills,new BillComparator());
		  ATM.instance(listOfBills);
	   }
	  
       public void testInitializedATM(){

           assertEquals(190,ATM.getBalance());
           assertFalse(ATM.isBalanceSufficient(200));
           assertTrue(ATM.isBalanceSufficient(100));
           assertTrue(ATM.isBalanceSufficient(0));
           assertTrue(ATM.isDenomAvailable(20).isSuccessFlag());
           assertTrue(ATM.isDenomAvailable(50).isSuccessFlag());
           assertTrue(ATM.isDenomAvailable(190).isSuccessFlag());
           assertFalse(ATM.isDenomAvailable(155).isSuccessFlag()); 
           
	   }
}
