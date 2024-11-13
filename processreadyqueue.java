public class processreadyqueue implements Runnable {
    
    int freemem;
    int timewaiting =0;
    public void run(){

    while(true){
        //when starting the thread we need a loop forever tp proccess anything new.
        while(!SJF.getMyList2().isEmpty()){ //wait while empty
        PCB currentProcess = SJF.getMyList2().get(0);
        PCB nextprocess;
        /*if(SJF.getMyList2().get(1) != null){ 
            nextprocess = SJF.getMyList2().get(1);
            nextprocess.WaitingTime++;}
             // PRocessing the job:*/
             currentProcess.bursttime--;
             System.out.println("Processing: " + currentProcess.getId() + 
                              " (Remaining time: " + currentProcess.bursttime + "ms)");
 
             // Check if the job is done.
             if (currentProcess.bursttime <= 0) {
                freemem = SJF.getfreememory();
                freemem = freemem + SJF.getMyList2().get(0).getMemory();
                SJF.getMyList2().remove(0);
                 SJF.setFreememory(freemem);
               // nextprocess.WaitingTime += currentProcess.TurnaroundTime;
                 System.out.println("Process " + currentProcess.getId() +
                                  " completed."+" It waited for: "+currentProcess.getWaitingTime() +"ms. Free memory: " + freemem+"MB");
             }
            }
    }

    }
   
}
