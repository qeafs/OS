import java.util.ArrayList;

public class RR implements Runnable {

    public static int freememory = 1024;
    public static boolean empty = false;
    public static ArrayList<PCB> readyQueue = new ArrayList<>();
    public static ArrayList<PCB> DoneProcesses = new ArrayList<>();
    public static ArrayList<PCB> getReadyQueue() {
        return readyQueue;
    }

    @Override
    public void run() {
     
     try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
            }
        while (true) {
            moveProcessesToReadyQueue();
           if(Filereader.getMyList().isEmpty()){
            empty = true;
          
            
            }
        }
    }

    private void moveProcessesToReadyQueue() {
        
    while(true){
       // while(RR_Processes.block);
      if(!Filereader.getMyList().isEmpty()){
             PCB process = Filereader.getMyList().get(0);
            if (freememory >= process.memory) {
                freememory -= process.memory;
                readyQueue.add(process);
                Filereader.getMyList().remove(0);
            }
          if (Filereader.getMyList().isEmpty()){
            break;
          }
      /*  for (int i = 0; i < Filereader.getMyList().size(); i++) {
            while(RR_Processes.block);
            PCB process = Filereader.getMyList().get(i);
            if (freememory >= process.memory) {
                freememory -= process.memory;
                readyQueue.add(process);
                Filereader.getMyList().remove(i);
                i=0;
            }*/
        }
    }
}
}       




