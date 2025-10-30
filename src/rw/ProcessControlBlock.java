package rw;

public class ProcessControlBlock {
	
	private SimProcess process;
	private int currInstruction;
	private int register1, register2, register3, register4;
	
	/**
	 * constructor takes in a process and sets current instruction to 0
	 * @param process A new process 
	 */
	public ProcessControlBlock(SimProcess process) {
		this.process = process;
		this.currInstruction = 0;
	}
	
	/** method returns the process field */
	public SimProcess getProcess() {
		return this.process;
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
}
