public class FCFS_Processes implements Runnable {

    private int currentTime = 0; // Tracks elapsed time
    private StringBuilder timeline = new StringBuilder();
    private StringBuilder numbersTimeline = new StringBuilder();
    private double totalWaitingTime = 0;
    private double totalTurnaroundTime = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(200); // Allow FCFS thread to initialize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numbersTimeline.append(0).append("  ")
        while (true) {
           // while (FCFS.nextprocessisreadytoenter == false) {}
            executeProcesses();

            if (FCFS.empty && FCFS.getReadyQueue().isEmpty()) {
                printResults();
                break; // Stop when all processes are completed
            }
        }
    }

    private void executeProcesses() {
        
        while(FCFS.getReadyQueue().isEmpty());
        //when starting the thread we need a loop forever tp proccess anything new.

        PCB currentProcess = FCFS.getReadyQueue().get(0); // Dequeue first process
        FCFS.getReadyQueue().remove(0);
        numbersTimeline.append(currentTime).append("   ");
        timeline.append("| J").append(currentProcess.getId()).append(" ");
        // Calculate waiting time
        currentProcess.WaitingTime = currentTime;

        // Simulate process execution
        currentTime += currentProcess.bursttime;

        
        //apppend updated time
        numbersTimeline.append(currentTime).append("   ");

        // Calculate turnaround time
        currentProcess.TurnaroundTime = currentTime;


        // Update totals for statistics
        FCFS.DoneProcesses.add(currentProcess);
        totalWaitingTime += currentProcess.WaitingTime;
        totalTurnaroundTime += currentProcess.TurnaroundTime;

        // Free memory after process execution
        FCFS.freememory += currentProcess.memory;
    }

    private void printResults() {
        System.out.println("\nFCFS Timeline:");
        System.out.println(timeline);
        System.out.println(numbersTimeline);

        System.out.println("\nWaiting Times:");
        for (PCB process : FCFS.DoneProcesses) {
            System.out.println("J" + process.getId() + ": " + process.WaitingTime + "ms");
        }

        System.out.println("\nTurnaround Times:");
        for (PCB process : FCFS.DoneProcesses) {
            System.out.println("J" + process.getId() + ": " + process.TurnaroundTime + "ms");
        }
                // Mohammed is my uncle 


        // Calculate and print averages
        double avgWaitingTime = totalWaitingTime / FCFS.DoneProcesses.size();
        double avgTurnaroundTime = totalTurnaroundTime / FCFS.DoneProcesses.size();

        System.out.printf("\nAverage Waiting Time: %.2fms\n", avgWaitingTime);
        System.out.printf("Average Turnaround Time: %.2fms\n", avgTurnaroundTime);
    }
}