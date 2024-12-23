public class RR_Processes implements Runnable { 
    private static final int TIME_QUANTUM = 10;
   private double totalWaitingTime = 0;
   private double totalTurnaroundTime = 0;
   private int totalProcesses = 0;
   private int dummy = 0;
   private StringBuilder timeline = new StringBuilder();
   private int currentTime = 0;
   private StringBuilder numbersTimeline = new StringBuilder();
   public static boolean block = false;
    @Override
    public void run(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true){
            executeProcesses();
            if(RR.empty&&RR.getReadyQueue().isEmpty()){
                
                printResults();
                break;
            }
        }
    }
private void executeProcesses() {
    if (RR.getReadyQueue().isEmpty()) {
        return;
    }

    PCB currentProcess = RR.getReadyQueue().remove(0);
    if (dummy == 0){
        numbersTimeline.append(0).append("   ");
        dummy = 1;
    }
    
    if (currentProcess.bursttime > TIME_QUANTUM) {
      

        currentProcess.TurnaroundTime+=TIME_QUANTUM;
        currentProcess.bursttime -= TIME_QUANTUM;
        currentTime += TIME_QUANTUM;
        timeline.append("| J").append(currentProcess.getId()).append(" ");
        numbersTimeline.append(currentTime).append("   ");
        block = true;
        for(int i = 0; i < RR.getReadyQueue().size(); i++){
            RR.getReadyQueue().get(i).WaitingTime = RR.getReadyQueue().get(i).WaitingTime + TIME_QUANTUM;
        }
        block = false;
 
            RR.getReadyQueue().add(currentProcess);
         
    } else {
       
       
        //----------------------------------------------------------------------------
        currentTime += currentProcess.bursttime;
        currentProcess.TurnaroundTime = currentProcess.WaitingTime + currentProcess.bursttime + currentProcess.TurnaroundTime;
      
        
        totalProcesses++;
        timeline.append("| J").append(currentProcess.getId()).append(" ");
        numbersTimeline.append(currentTime).append("   ");
        RR.DoneProcesses.add(currentProcess);
        for(int i = 0; i < RR.getReadyQueue().size(); i++){
            RR.getReadyQueue().get(i).WaitingTime = RR.getReadyQueue().get(i).WaitingTime + currentProcess.bursttime;
        }
        RR.freememory += currentProcess.memory;
       try {
        Thread.sleep(20);
       } catch (InterruptedException e) {
        e.printStackTrace();
       }
    }
}
private void printResults(){
    RR.DoneProcesses.sort((a,b)-> Integer.compare(a.getId(), b.getId()));
    System.out.println(timeline);
    System.out.println(numbersTimeline);
    System.out.println(" Waiting Times: " );
    for(int i = 0; i < RR.DoneProcesses.size(); i++){
        System.out.print("J" + RR.DoneProcesses.get(i).getId() + ": " + RR.DoneProcesses.get(i).WaitingTime + " ,");
    }
    System.out.println();
    System.out.println("Turnaround Times: ");
    for(int i = 0; i < RR.DoneProcesses.size(); i++){
        System.out.print("J" + RR.DoneProcesses.get(i).getId() + ": " + RR.DoneProcesses.get(i).TurnaroundTime + " ,");
    }
    System.out.println();
    //Average Turnaround Time
    System.out.println("Average Turnaround Time: " );
    System.out.print("(");
    for(int i = 0; i < RR.DoneProcesses.size(); i++){
        totalTurnaroundTime += RR.DoneProcesses.get(i).TurnaroundTime;
        System.out.print(RR.DoneProcesses.get(i).TurnaroundTime + " ");
        if(i != RR.DoneProcesses.size() - 1){
            System.out.print("+");
        }
    }
        System.out.print(")");
    
    System.out.print(" / " + totalProcesses + " = " + (totalTurnaroundTime / totalProcesses));
    System.err.println();
    //Average Waiting Time
    System.err.println("Average Waiting Time: " );
    System.out.print("(");
    for(int i = 0; i < RR.DoneProcesses.size(); i++){
        totalWaitingTime += RR.DoneProcesses.get(i).WaitingTime;
        System.err.print(RR.DoneProcesses.get(i).WaitingTime + " ");
        if(i != RR.DoneProcesses.size() - 1){
            System.err.print("+");
        }
    }
        System.err.print(")");
    
    System.err.print(" / " + totalProcesses + " = " + (totalWaitingTime / totalProcesses));
    System.err.println();
for(int i = 0; i < Filereader.getMyList().size(); i++){
    System.out.println("J" + Filereader.getMyList().get(i).getId() + "  ");
}   
}
}
