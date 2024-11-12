public class processreadyqueue implements Runnable {
    
    int freemem;

 

    public void run(){
        
    while(true){
        //when starting the thread we need a loop forever tp proccess anything new.
        while(!SJF.getMyList().isEmpty()); //wait while empty
        PCB currentProcess = SJF.getMyList().get(0);
            
             // PRocessing the job:
             currentProcess.bursttime--;
             System.out.println("Processing: " + currentProcess.getId() + 
                              " (Remaining time: " + currentProcess.bursttime + "ms)");
 
             // Check if the job is done.
             if (currentProcess.bursttime <= 0) {
                freemem = SJF.getfreememory();
                freemem = freemem + SJF.getMyList().get(0).getMemory();
                SJF.getMyList().remove(0);
                 SJF.setFreememory(freemem);

                 System.out.println("Process " + currentProcess.getId() + 
                                  " completed. Free memory: " + freemem+"MB");
             }
    }

    }
}
