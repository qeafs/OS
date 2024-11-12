import java.util.LinkedList;
import java.util.Queue;
public class FCFS implements Runnable{
    
    int freememory = 1024;
    private Queue<PCB> readyQueue;
    


public FCFS(){
    readyQueue = new LinkedList<>();
}
public void addProcess(PCB process){
    if(process.getMemory() <= freememory){
        readyQueue.offer(process);
        freememory -= process.getMemory();
        System.out.println("Process " + process.getId() + " added to ready queue");
    }
    else{
        System.out.println("Not enough memory");
    }
}

    @Override
    public void run(){
        while(true){
            if(!readyQueue.isEmpty()){
                PCB currentProcess = readyQueue.poll();
                System.out.println("Executing process " + currentProcess.getId());

                try {
                    Thread.sleep(currentProcess.getBursttime() * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("Process execution interrupted: " + e.getMessage());
                }

                freememory += currentProcess.getMemory();
                System.out.println("Process " + currentProcess.getId() + " finished");
            }
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread interrupted: " + e.getMessage());
            }


        }

    }

}
