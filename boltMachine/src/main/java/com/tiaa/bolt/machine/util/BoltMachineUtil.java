package com.tiaa.bolt.machine.util;

import java.util.HashMap;
import java.util.Map;

import com.tiaa.bolt.machine.main.BoltMachineTest;

/**
 * This class simulates conveyor belt behavior
 * which rotates bolt and machines in alternate sequence
 * @author ssdhande
 *
 */
public class BoltMachineUtil {

	public static int AVLBL_NO_OF_BOLTS = BoltMachineTest.NO_OF_BOLTS;
	public static int AVLBL_NO_OF_MACHINES = BoltMachineTest.NO_OF_MACHINES;
	public static long TOTAL_TIME_TAKEN = 0L;
	public static long TOTAL_PRODUCTS = 0;
	public static String lastProvidedResource = null;
	public static Map<Long, String> timeMap = new HashMap<Long, String>();
	
	public String pickMaterialFromConvBelt(String materialRequired){
		
		if (BoltMachineTest.NO_OF_BOLTS == AVLBL_NO_OF_BOLTS
				&& BoltMachineTest.NO_OF_MACHINES == AVLBL_NO_OF_MACHINES) {
			
			AVLBL_NO_OF_BOLTS -= 1;
			lastProvidedResource = BoltMachineConstant.RESOURCE_BOLT;
			
		}else if (BoltMachineConstant.RESOURCE_BOLT.equals(lastProvidedResource)
				&& (BoltMachineConstant.RESOURCE_MACHINE.equals(materialRequired)
						|| BoltMachineConstant.RESOURCE_ANY.equals(materialRequired))) {
			
			AVLBL_NO_OF_MACHINES -= 1;
			lastProvidedResource = BoltMachineConstant.RESOURCE_MACHINE;
			
		}else if (BoltMachineConstant.RESOURCE_MACHINE.equals(lastProvidedResource)
				&& (BoltMachineConstant.RESOURCE_BOLT.equals(materialRequired)
						|| BoltMachineConstant.RESOURCE_ANY.equals(materialRequired))) {
			
			AVLBL_NO_OF_BOLTS -= 1;
			lastProvidedResource = BoltMachineConstant.RESOURCE_BOLT;
			
		}
		
		return lastProvidedResource;
		
	}
	
	/**
	 * After picking 2 bolts and 1 machine from conveyor belt
	 * employee assembles it to product
	 * which takes ${TIME_TO_MAKE_PRODUCT_IN_SEC} seconds
	 */
	public void assembleProduct(){
		
		TOTAL_PRODUCTS += 1;
		TOTAL_TIME_TAKEN += BoltMachineTest.TIME_TO_MAKE_PRODUCT_IN_SEC;
		
		try{
			timeMap.putIfAbsent(System.currentTimeMillis(), BoltMachineConstant.KEY_ACTION_START);
			
			Thread.sleep(BoltMachineTest.TIME_TO_MAKE_PRODUCT_IN_SEC*1000);
			
			timeMap.putIfAbsent(System.currentTimeMillis(), BoltMachineConstant.KEY_ACTION_END);
			
		}catch(InterruptedException e){
			
			System.out.println("INTERRUPTED EXCEPTION OCCURS "+ e.getMessage());
			
		}
	}
	
}
