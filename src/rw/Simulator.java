package rw;
import java.util.*;

public class Simulator {

    public static void main(String[] args) {
        final int QUANTUM = 5; 
        ArrayList<ProcessControlBlock> readyList = new ArrayList<>();
        ArrayList<ProcessControlBlock> blockedList = new ArrayList<>();
        SimProcessor processor = new SimProcessor();

        //adds 10 processes and their respective PCBs to the ready list
        readyList.add(new ProcessControlBlock(new SimProcess(1, "Proc1", 150)));
        readyList.add(new ProcessControlBlock(new SimProcess(2, "Proc2", 267)));
        readyList.add(new ProcessControlBlock(new SimProcess(3, "Proc3", 100)));
        readyList.add(new ProcessControlBlock(new SimProcess(4, "Proc4", 202)));
        readyList.add(new ProcessControlBlock(new SimProcess(5, "Proc5", 302)));
        readyList.add(new ProcessControlBlock(new SimProcess(6, "Proc6", 308)));
        readyList.add(new ProcessControlBlock(new SimProcess(7, "Proc7", 109)));
        readyList.add(new ProcessControlBlock(new SimProcess(8, "Proc8", 301)));
        readyList.add(new ProcessControlBlock(new SimProcess(9, "Proc9", 199)));
        readyList.add(new ProcessControlBlock(new SimProcess(10, "Proc10", 213)));

        ProcessControlBlock currPcb = readyList.get(0); //sets the currPcb variable to the first pcb on the ready list
        processor.setCurrProcess(currPcb.getProcess()); //puts the first process on the processor
        int quantumCtr = 0; //quantum counter

        for (int i = 1; i <= 3000; i++) {
            System.out.print("Step: " + i + " ");
            
            //processor executes an instruction of current process and a process state is returned
            ProcessState procState = processor.executeNextInstruction(); 
            quantumCtr++;

            if (procState == ProcessState.FINISHED) {
                System.out.println("*** Process Completed ***");
                readyList.remove(currPcb); //removes finished process from the ready list
                
                if (readyList.isEmpty()) {
                    System.out.println("*** All Processes Completed ***");
                    break; 
                }
            } else if (quantumCtr == QUANTUM || procState == ProcessState.BLOCKED) {
                if (quantumCtr == QUANTUM) {
                    System.out.println("*** Quantum Expired ***");
                } else {
                    System.out.println("*** Process Blocked ***");
                }
                quantumCtr = 0; //sets quantum counter back to 0
                saveProcess(readyList, blockedList, currPcb, processor, i + 1, procState); //calls method that saves a process
                i++;
            } 
            
            //before performing a context switch, if there are no available ready processes
            if(readyList.isEmpty()) { 
            	System.out.println("Processor is Idling...");
            	wakeUp(blockedList, readyList); //calls method that wakes up blocked processes
            }
            
            //after quantum expires or process is blocked, context switch will occur (process whose quantum did not expire will continue to execute)
            if (!readyList.isEmpty() && readyList.get(0) != currPcb) { 
                currPcb = readyList.get(0); //sets currPcb to next available process
                processor = contextSwitch(processor, currPcb, i + 1, readyList); //calls method that performs a context switch
                i++;
            }
            wakeUp(blockedList, readyList); //after performing any step, the method that wakes up blocked processes is called
        }
    }

    //Method that saves the current process info
    public static void saveProcess(ArrayList<ProcessControlBlock> readyList, ArrayList<ProcessControlBlock> blockedList,
                                     ProcessControlBlock currPcb, SimProcessor processor, int i, ProcessState procState) {
        
    	System.out.println("Step " + i + " Context Switch. Saving process: " + currPcb.getProcess().getPid());
        System.out.println("\t  Instruction " + processor.getCurrInstruction() + " - R1: " + processor.getRegister1() + 
                           ", R2: " + processor.getRegister2() + ", R3: " + processor.getRegister3() + ", R4: " + processor.getRegister4());

        //saves the current process instruction and register values to its respective pcb 
        currPcb.setCurrInstruction(processor.getCurrInstruction());
        currPcb.setRegisterValues(processor.getRegister1(), processor.getRegister2(), processor.getRegister3(), processor.getRegister4());

        //if the process is blocked, it is removed from ready list and placed on blocked list
        if (procState == ProcessState.BLOCKED) { 
            readyList.remove(currPcb);  
            blockedList.add(currPcb);
        //if the quantum expired and the process is ready, it is put at the end of the ready list
        } else {   
            readyList.remove(currPcb);
            readyList.add(currPcb);
        }
    }

    //Method that performs a context switch, it restores values for new current process
    public static SimProcessor contextSwitch(SimProcessor processor, ProcessControlBlock currPcb, int i, ArrayList<ProcessControlBlock> readyList) {
    	
    	//restores the new current process instruction and register values
        processor.setCurrProcess(currPcb.getProcess()); 
        processor.setCurrInstruction(currPcb.getCurrInstruction()); 
        processor.setRegisterValues(currPcb.getRegister1(), currPcb.getRegister2(), currPcb.getRegister3(), currPcb.getRegister4());

        if (processor.getCurrInstruction() != 0) {
            System.out.println("Step " + i + " Context Switch: Restoring Process: " + processor.getCurrProcess().getPid());
            System.out.println("\t\tInstruction: " + processor.getCurrInstruction() + " - R1: " + processor.getRegister1() + 
                               ", R2: " + processor.getRegister2() + ", R3: " + processor.getRegister3() + ", R4: " + processor.getRegister4());
        }
        return processor; //returns updated processor for execution
    }

    //Method that wakes up blocked processes
    public static void wakeUp(ArrayList<ProcessControlBlock> blockedList, ArrayList<ProcessControlBlock> readyList) {
        Random rand = new Random();
        
        //loops through blocked list, wakes each process up with 30% probability
        for (int i = 0; i < blockedList.size(); i++) {
            if (rand.nextInt(100) < 30) { 
                readyList.add(blockedList.get(i)); //adds woken up process to readyList
                blockedList.remove(i);             //and removes it from blocked list
                i--;
            }
        }
    }
}

