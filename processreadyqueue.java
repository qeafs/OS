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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    while(true){
        processjobs();
       System.out.println(" ");
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
         int totaltime = calcwaittime();
        System.out.println(" SJF Output:");

        //Grantt chart
        for(int i=0; i<completedjobs.size(); i++){
            PCB currentProcess = completedjobs.get(i);
            System.out.printf("| j%-3d",currentProcess.getId());}
            System.out.println("|");
        for(int i=0; i<completedjobs.size(); i++){
            System.out.printf("%-6d",completedjobs.get(i).WaitingTime);    
                                    }
                                    System.err.print(""+totaltime);
            
        //Turnaround time
        System.out.println("\n-Turnaround Times:");
        int tTurnaround =0;
        for(int i=0; i<completedjobs.size(); i++){
        PCB currentProcess = completedjobs.get(i);
        tTurnaround = currentProcess.ogbursttime + tTurnaround;
        System.out.print("j" + currentProcess.getId() +
                                  ": "+tTurnaround+"ms, ");

                                }
        
        System.out.println("\n-Waiting Time:");
        for(int i=0; i<completedjobs.size(); i++){
        PCB currentProcess = completedjobs.get(i);
        System.out.print("j" + currentProcess.getId() +
                                  ": "+currentProcess.WaitingTime+"ms, ");

                                }

    }

    public int calcwaittime(){
        completedjobs.get(0).WaitingTime=0;
        int totalwaittime =0;
        int i =1;
        for(i=1; i<completedjobs.size(); i++){
            totalwaittime = totalwaittime + completedjobs.get(i-1).ogbursttime;
            completedjobs.get(i).setWaitingTime(totalwaittime);
        }
        totalwaittime = totalwaittime + completedjobs.get(i-1).ogbursttime;
        return totalwaittime ;
    }

   
}
