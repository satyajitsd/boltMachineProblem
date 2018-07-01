package com.tiaa.bolt.machine.processor;

import com.tiaa.bolt.machine.util.BoltMachineConstant;
import com.tiaa.bolt.machine.util.BoltMachineUtil;

/**
 * Class extends thread which simulates
 * employee and their work
 * @author ssdhande
 *
 */
public class EmployeeThread implements Runnable{

	BoltMachineUtil util = new BoltMachineUtil();
	
	public void run() {
		
		int boltCount = 0;
		int machineCount = 0;
		String materialRequired = BoltMachineConstant.RESOURCE_ANY;
		
		while(BoltMachineUtil.AVLBL_NO_OF_BOLTS > 0
				&& BoltMachineUtil.AVLBL_NO_OF_MACHINES > 0){
			
			System.out.println("[" + Thread.currentThread().getName()+"] Requires Material ("+ materialRequired+")");
			
			String resourcePicked = util.pickMaterialFromConvBelt(materialRequired);
			
			System.out.println("[" + Thread.currentThread().getName()+"] PICKS RESOURCE ("+ resourcePicked+")");
			
			if(BoltMachineConstant.RESOURCE_BOLT.equals(resourcePicked)){
				boltCount++;
			}else if (BoltMachineConstant.RESOURCE_MACHINE.equals(resourcePicked)) {
				machineCount++;
			}
			
			if(boltCount == BoltMachineConstant.MIN_NO_OF_BOLTS 
					&& machineCount < BoltMachineConstant.MIN_NO_OF_MACHINES){
				materialRequired = BoltMachineConstant.RESOURCE_MACHINE;
			}
			
			if(machineCount == BoltMachineConstant.MIN_NO_OF_MACHINES 
					&& boltCount < BoltMachineConstant.MIN_NO_OF_BOLTS){
				materialRequired = BoltMachineConstant.RESOURCE_BOLT;
			}
			
			System.out.println("[" + Thread.currentThread().getName()
					+ "] [ BOLTS >> " + boltCount 
					+ " & MACHINES >> "	+ machineCount + "]");
			
			if(boltCount == BoltMachineConstant.MIN_NO_OF_BOLTS 
					&& machineCount == BoltMachineConstant.MIN_NO_OF_MACHINES){
				
				System.out.println("[" + Thread.currentThread().getName()+"] "
						+ "********** GOING TO ASSEMBLE PRODUCT **********");
				
				util.assembleProduct();
				
				boltCount = 0;
				machineCount = 0;
				materialRequired = BoltMachineConstant.RESOURCE_ANY;
				
			}
		}
	}

}
