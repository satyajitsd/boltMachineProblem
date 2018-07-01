package com.tiaa.bolt.machine.main;

/**
 * This is the main class to test
 * bolt machine use case
 * 
 * @author ssdhande
 */
import com.tiaa.bolt.machine.processor.EmployeeThread;
import com.tiaa.bolt.machine.util.BoltMachineConstant;
import com.tiaa.bolt.machine.util.BoltMachineUtil;

public class BoltMachineTest {
	
	public static int NO_OF_BOLTS = 12;
	public static int NO_OF_MACHINES = 6;
	public static long TIME_TO_MAKE_PRODUCT_IN_SEC = 10;
	
	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args){
		
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

}
