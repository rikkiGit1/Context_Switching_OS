package rw;
import java.util.*;

public class SimProcessor {
	
	private SimProcess currProcess;
	private int currInstruction;
	private int register1, register2, register3, register4;
	
	/**
	 * method sets current process to the process passed in
	 * @param currProcess The current process 
	 */
	public void setCurrProcess(SimProcess currProcess) {
		this.currProcess = currProcess;
	}
	
	/** method returns the current process */
	public SimProcess getCurrProcess() {
		return this.currProcess;
	}
	
	/**
	 * method sets the registers to values passed in
	 * @param the register values 
	 */
	public void setRegisterValues(int register1, int register2, int register3, int register4) {
		this.register1 = register1;
		this.register2 = register2;
		this.register3 = register3;
		this.register4 = register4;
	}
	
	/** method returns the value of register 1 */
	public int getRegister1() {
		return this.register1;
	}
	
	/** method returns the value of register 2 */
	public int getRegister2() {
		return this.register2;
	}
	
	/** method returns the value of register 3 */
	public int getRegister3() {
		return this.register3;
	}
	
	/** method returns the value of register 4 */
	public int getRegister4() {
		return this.register4;
	}
	
	/**
	 * method sets current instruction to the instruction number passed in
	 * @param currentInstruction The current instruction value 
	 */
	public void setCurrInstruction(int currInstruction) {
		this.currInstruction = currInstruction;
	}
	
	/** method returns the value of the current instruction */
	public int getCurrInstruction() {
		return this.currInstruction;
	}
	
	/** method calls execute method of current process, passing value of current instruction
	 * increments current instruction, and returns the result of the execute method
	 * it also sets all 4 registers to random values at each execution 
	 */
	public ProcessState executeNextInstruction() {
		ProcessState procState = this.currProcess.execute(currInstruction);
		this.currInstruction++;
		
		Random rand = new Random(); //before returning the registers are set to random values
		setRegisterValues(rand.nextInt(), rand.nextInt(), rand.nextInt(), rand.nextInt());
		
		return procState; //returns result of the execute method
	}
}
