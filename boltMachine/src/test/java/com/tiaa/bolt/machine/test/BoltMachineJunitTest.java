package com.tiaa.bolt.machine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tiaa.bolt.machine.processor.EmployeeThread;
import com.tiaa.bolt.machine.util.BoltMachineConstant;
import com.tiaa.bolt.machine.util.BoltMachineUtil;

public class BoltMachineJunitTest {
	
	public static int NO_OF_BOLTS = 12;
	public static int NO_OF_MACHINES = 6;
	public static long TIME_TO_MAKE_PRODUCT_IN_SEC = 10;
	
	@Test
	public void testConveyorBelt(){
		
		BoltMachineUtil bmu = new BoltMachineUtil();
		
		assertEquals("Material picked from belt -","BOLT",bmu.pickMaterialFromConvBelt("NA"));
	}
	
	@Test
	public void testFullFunctionlaity() {

		if(NO_OF_BOLTS >= BoltMachineConstant.MIN_NO_OF_BOLTS 
				&& NO_OF_MACHINES >= BoltMachineConstant.MIN_NO_OF_MACHINES
				&& TIME_TO_MAKE_PRODUCT_IN_SEC >= 1){
			
			EmployeeThread e1 = new EmployeeThread();
			Thread t1 = new Thread(e1, "Employee_1");
			Thread t2 = new Thread(e1, "Employee_2");
			Thread t3 = new Thread(e1, "Employee_3");
			
			t1.start();
			t2.start();
			t3.start();
			
			try {
				t1.join();
				t2.join();
				t3.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("============================================="
					+ "=============================================");
			System.out.println("FINAL OUTPUT :");
			System.out.println("============================================="
					+ "=============================================");
			
			System.out.println("HAVING " + NO_OF_BOLTS + " BOLTS AND "
					+ NO_OF_MACHINES + " MACHINES WITH "
					+ TIME_TO_MAKE_PRODUCT_IN_SEC
					+ " SECONDS TO MAKE A PRODUCT, WE HAVE....");
			
			System.out.println("TOTAL PRODUCTS ASSEMBLED : "
					+ BoltMachineUtil.TOTAL_PRODUCTS);
			
			System.out.println("TOTAL TIME TAKE TO ASSEMBLE : "
					+ BoltMachineUtil.timeMap.size() / 2
					* TIME_TO_MAKE_PRODUCT_IN_SEC + " SECONDS");
			
		}else{
			System.out
					.println("*********** NOT ENOUGH RESOURCES AVAILABLE TO MAKE A PRODUCT ***********");
		}
		
	}
	
	@Test
	public void timeForAssemblingProduct(){
		
		BoltMachineUtil bmu = new BoltMachineUtil();
		
		long startTime = System.currentTimeMillis();
		
		bmu.assembleProduct();
		
		long endTime = System.currentTimeMillis();
		
		long totalTime = endTime - startTime;
		
		assertTrue(totalTime >= TIME_TO_MAKE_PRODUCT_IN_SEC);
		
	}

}
