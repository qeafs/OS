import java.util.ArrayList;

public class processreadyqueue implements Runnable {
    

            /////////////////////////////////////////////////////
            ///////////////THIS IS FOR SJF ONLY//////////////////
            /////////////////////////////////////////////////////
            
    int freemem;
    int timewaiting =0;
    public static int donejobs =0;
    public static ArrayList<PCB> completedjobs = new ArrayList<PCB>();


    public void run(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    while(true){
        processjobs();
        System.err.print("");
        if(SJF.getMyList2().isEmpty()){
            peinttheresult();
            break;
        }
    }

    }

    public void processjobs(){
        //when starting the thread we need a loop forever tp proccess anything new.
        while(!SJF.getMyList2().isEmpty()){ //wait while empty
            PCB currentProcess = SJF.getMyList2().get(0);
           
                 // PRocessing the job:
               currentProcess.bursttime--;
               currentProcess.WaitingTime++;
     
                 // Check if the job is done.
                 if (currentProcess.bursttime <= 0) {
                    donejobs++;
                    completedjobs.add(currentProcess);
                    freemem = SJF.getfreememory();
                    freemem = freemem + SJF.getMyList2().get(0).getMemory();
                    SJF.getMyList2().remove(0);
                     SJF.setFreememory(freemem);
                   System.out.println("id:"+currentProcess.id);
                     
                 }
                }
    }
    
    public void peinttheresult(){
        System.out.println(" SJF Output:");

        for(int i=0; i<completedjobs.size(); i++){
            PCB currentProcess = completedjobs.get(i);
            System.out.print(" | j" + currentProcess.getId());
    
                                    }
            System.out.println(" |");
        System.out.println("-Turnaround Times:");
        for(int i=0; i<completedjobs.size(); i++){
        PCB currentProcess = completedjobs.get(i);
        System.out.print("j" + currentProcess.getId() +
                                  ": "+currentProcess.ogbursttime+"ms, ");

                                }
    }

    public int calcwaittime(PCB process){
        if(process.id == 0)
        return 0;
        for(int i =1; i<completedjobs.size(); i++){
            
        }
        
        return 0;
    }

   
}
