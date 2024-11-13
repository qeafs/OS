import java.util.ArrayList;

public class processreadyqueue implements Runnable {
    

            /////////////////////////////////////////////////////
            ///////////////THIS IS FOR SJF ONLY//////////////////
            /////////////////////////////////////////////////////
            
    int freemem;
    int timewaiting =0;
    public static int donejobs =0;
    public static ArrayList<PCB> finishedjobslist = new ArrayList<PCB>();
    public void run(){
            
    while(true){
        //when starting the thread we need a loop forever tp proccess anything new.
        while(!SJF.getMyList2().isEmpty()){ //wait while empty
        PCB currentProcess = SJF.getMyList2().get(0);
       
             // PRocessing the job:
           currentProcess.bursttime--;  /*  
             System.out.println("Processing: " + currentProcess.getId() + 
                              " (Remaining time: " + currentProcess.bursttime + "ms)");  */
 
             // Check if the job is done.
             if (currentProcess.bursttime <= 0) {
                donejobs++;
                if(donejobs ==1)System.err.println("-Turnaround Times:");
                freemem = SJF.getfreememory();
                freemem = freemem + SJF.getMyList2().get(0).getMemory();
                SJF.getMyList2().remove(0);
                 SJF.setFreememory(freemem);
               // nextprocess.WaitingTime += currentProcess.TurnaroundTime;
                 System.out.print("j" + currentProcess.getId() +
                                  ": "+currentProcess.ogbursttime+"ms, ");
             }
            }
    }

    }


   
}
