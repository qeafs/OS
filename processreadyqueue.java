import java.util.ArrayList;
public class processreadyqueue implements Runnable {
    

            /////////////////////////////////////////////////////
            ///////////////THIS IS FOR SJF ONLY//////////////////
            /////////////////////////////////////////////////////
    volatile int  freemem;
    int currentTime =0;
    public static int donejobs =0;
    //public static CopyOnWriteArrayList<PCB> completedjobs = new CopyOnWriteArrayList<PCB>();
      private static final ArrayList<PCB> completedjobs = new ArrayList<>();



    public void run(){
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        while(true){
         processJobs();

         if(SJF.getMyList2().isEmpty()){
            peinttheresult();
            break;
        }
    }

    }




    private void processJobs() {
        while (donejobs< SJF.getTotaljobs()) {
            PCB currentProcess;

            synchronized (SJF.LOCK) {
                while (SJF.getMyList2().isEmpty()) {
                    try {
                        SJF.LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                currentProcess = SJF.getMyList2().remove(0);
            }

            // Process the job
            while (currentProcess.bursttime > 0) {
                currentProcess.bursttime--;
                currentTime++;
                synchronized (SJF.LOCK) {
                    for (PCB processInReady : SJF.getMyList2()) {
                        processInReady.WaitingTime++;
                    }
                }
            }

            // Job completed
            currentProcess.finishtime = currentTime;
            currentProcess.INTturnaroundTime = currentProcess.WaitingTime + currentProcess.ogbursttime;
            donejobs++;
            completedjobs.add(currentProcess);

            synchronized (SJF.LOCK) {
                SJF.setFreememory(SJF.getfreememory() + currentProcess.memory);
                SJF.LOCK.notifyAll();
            }

            // System.out.println("Completed job: " + currentProcess.id + " | Free memory: " + SJF.getfreememory());
        }
    }



    
    public void peinttheresult(){
         int totaltime = calctotaltime();
        System.out.println("\n\n SJF Output:");

        //Grantt chart
        for(int i=0; i<completedjobs.size(); i++){
            PCB currentProcess = completedjobs.get(i);
            System.out.printf("| j%-3d",currentProcess.getId());}
            System.out.println("|");
            System.out.printf("%-6d",0);
        for(int i=0; i<completedjobs.size()-1; i++){
            System.out.printf("%-6d",completedjobs.get(i).finishtime);    
                                    }
                                    System.out.print(""+totaltime);
            
        //Turnaround time
        System.out.println("\n\n-Turnaround Times:");
        for(int i=0; i<completedjobs.size(); i++){
        PCB currentProcess = completedjobs.get(i);
        System.out.print("j" + currentProcess.getId() +
                                  ": "+currentProcess.INTturnaroundTime+"ms, ");

                                }
        
        System.out.println("\n\n-Waiting Times:");
        for(int i=0; i<completedjobs.size(); i++){
        PCB currentProcess = completedjobs.get(i);
        System.out.print("j" + currentProcess.getId() +
                                  ": "+currentProcess.WaitingTime+"ms, ");

                                }


        
        System.out.println("\n\n-Average Turnaround Time:");
        int totalTurnaround=0;
        int index =0;
        int currentProcessturaroundtime =0;
        System.out.print("(");
            for(index=0; index<completedjobs.size()-1; index++){
            PCB currentProcess = completedjobs.get(index);
            currentProcessturaroundtime = currentProcess.INTturnaroundTime;
            totalTurnaround = currentProcessturaroundtime + totalTurnaround;
            ;System.out.print(currentProcessturaroundtime+" + ");
                    }
                    PCB currentProcess = completedjobs.get(index);
                    currentProcessturaroundtime = currentProcess.INTturnaroundTime;
                    totalTurnaround = currentProcessturaroundtime + totalTurnaround;
                    System.out.print(currentProcessturaroundtime+")"+"/"+completedjobs.size()+" = "+totalTurnaround/completedjobs.size()+"ms"); 
                    




        System.out.println("\n\n-Average Waiting Time:");
     
        int totalwaitingtime=0;
        int index2=0;
        int currentProcesswaittime =0;
        System.out.print("(");
            for(index2=0; index2<completedjobs.size()-1; index2++){
             currentProcess = completedjobs.get(index2);
                currentProcesswaittime = currentProcess.WaitingTime;
            totalwaitingtime = currentProcesswaittime + totalwaitingtime;
            ;System.out.print(currentProcesswaittime+" + ");
                    }
                    currentProcess = completedjobs.get(index2);
                    totalwaitingtime = currentProcesswaittime + totalwaitingtime;
                    currentProcesswaittime = currentProcess.WaitingTime;
                    System.out.println(currentProcesswaittime+")"+"/"+completedjobs.size()+" = "+totalwaitingtime/completedjobs.size()+"ms"); 

        System.out.println("-------------------------------------------------------------------------------------------");
    
    }




    public int calctotaltime(){
        int totalwaittime =0;
        int i =1;
        for(i=1; i<completedjobs.size(); i++){
            totalwaittime = totalwaittime + completedjobs.get(i-1).ogbursttime;
        }
        totalwaittime = totalwaittime + completedjobs.get(i-1).ogbursttime;
        return totalwaittime ;
    }

   
}
