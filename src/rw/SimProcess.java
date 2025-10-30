package rw;
import java.util.Random;

public class SimProcess {
	
	private int pid;
	private String procName;
	private int totalInstructions;
	
	/**
	 * constructor takes in a process pid, name, and total # of instructions
	 * @param pid, @param procName, @param totalInstructions
	 */
	public SimProcess(int pid, String procName, int totalInstructions) {
		this.pid = pid;
		this.procName = procName;
		this.totalInstructions = totalInstructions;
	}
	
	/** method executes the instruction passed in and returns a process state 
	 * @param i The instruction */
	public ProcessState execute(int i)
	{
		System.out.println("PID: " + pid + " Proc " + procName + " executing instruction: " + i);
		Random rand = new Random();
		
		if(i >= totalInstructions) {
			return ProcessState.FINISHED;
		}
		else if(rand.nextInt(100) < 15) {
			return ProcessState.BLOCKED;
		}
		return ProcessState.READY;
	}
	
	/** method returns the process pid */
	public int getPid() {
		return this.pid;
	}
	
	/** method returns the process name */
	public String getProcName() {
		return this.procName;
	}
}
