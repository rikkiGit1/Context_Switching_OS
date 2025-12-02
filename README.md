# Context Switching OS Simulation
This project is an operating system simulation designed to demonstrate how context switching, process scheduling, and basic CPU management work at an OS level. 
The program models a CPU executing multiple processes, saving and restoring process states, and switching between them as the scheduler determines which process should run next.

## Features
Process Control Block (PCB): stores PID, program counter, registers, and state.
Ready Queue: holds runnable processes.
Scheduler: selects the next process using a Round-Robin scheme.
Context Switching: saves the current process state and loads the next one.
CPU Simulation: executes instructions until the process blocks, its time quantum expires, or it completes.

## Simulation
The program loads processes into a ready queue, selects the next process through the scheduler, restores its saved context, and runs it on the simulated CPU. 
When a switch is required, the OS saves the current process’s state and moves on to the next one, continuing this cycle until all processes terminate. 
Throughout execution, the program outputs which process is running, when context switches occur, how registers and the program counter change, and when each process finishes.
