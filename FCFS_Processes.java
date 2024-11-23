public class FCFS_Processes implements Runnable {

    private int currentTime = 0; // Tracks elapsed time
    private StringBuilder timeline = new StringBuilder();
    private StringBuilder numbersTimeline = new StringBuilder();
    private double totalWaitingTime = 0;
    private double totalTurnaroundTime = 0;
    private int totalProcesses = 0;

    @Override
    public void run() {
        try {
            Thread.sleep(300); // Allow FCFS thread to initialize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        numbersTimeline.append(0).append("   ");
        while (true) {
           // while (FCFS.nextprocessisreadytoenter == false) {}
            executeProcesses();

            if (Filereader.getMyList().isEmpty() && FCFS.getReadyQueue().isEmpty()) {
                printResults();
                break; // Stop when all processes are completed
            }
        }
    }

    private void executeProcesses() {
        
         if (FCFS.getReadyQueue().isEmpty()) {
        return;
        }  
       

        PCB currentProcess = FCFS.getReadyQueue().get(0); // Dequeue first process
        System.out.print("=====");
        FCFS.getReadyQueue().remove(currentProcess);
        currentTime += currentProcess.bursttime;
        currentProcess.TurnaroundTime = currentProcess.WaitingTime + currentProcess.bursttime + currentProcess.TurnaroundTime;
        totalProcesses++;
        timeline.append("| J").append(currentProcess.getId()).append(" ");
        numbersTimeline.append(currentTime).append("   ");
        FCFS.DoneProcesses.add(currentProcess);
     
        for(int i = 0; i < FCFS.getReadyQueue().size(); i++){
                FCFS.getReadyQueue().get(i).WaitingTime = FCFS.getReadyQueue().get(i).WaitingTime + currentProcess.bursttime;
        }
        
        FCFS.freememory += currentProcess.memory;
    }







    private void printResults() {
     
        System.out.println("\nFCFS Timeline:");
        System.out.println(timeline+"|");
        System.out.println(numbersTimeline);



    System.out.println("Turnaround Times: ");
    for(int i = 0; i < FCFS.DoneProcesses.size(); i++){
        System.out.print("J" + FCFS.DoneProcesses.get(i).getId() + ": " + FCFS.DoneProcesses.get(i).TurnaroundTime + ", ");
    }
    System.out.println("");

    System.out.println("\nWaiting Times:");
    for(int i = 0; i < FCFS.DoneProcesses.size(); i++){
        System.out.print("J" + FCFS.DoneProcesses.get(i).getId() + ": " + FCFS.DoneProcesses.get(i).WaitingTime + ", ");
    }

                // Mohammed is my uncle 
    System.out.println("");
    System.out.println("");

       
     // Calculate and print averages
     //Average Total turnaround
    System.out.println("Average Turnaround Time: " );
    for(int i = 0; i < FCFS.DoneProcesses.size(); i++){
        totalTurnaroundTime += FCFS.DoneProcesses.get(i).TurnaroundTime;
    }
    System.out.print(totalTurnaroundTime / totalProcesses);
    System.out.println("");



    //Average total waiting
    System.out.println("Average Wait Time: " );
    for(int i = 0; i < FCFS.DoneProcesses.size(); i++){
        totalWaitingTime += FCFS.DoneProcesses.get(i).WaitingTime;
    }
    System.out.print(totalWaitingTime / totalProcesses);
    System.out.println("");


    }
}
