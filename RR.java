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
            Thread.sleep(100);
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
      
            PCB process = Filereader.getMyList().get(0);
            if (freememory >= process.memory) {
                freememory -= process.memory;
                readyQueue.add(process);
                Filereader.getMyList().remove(0);
                
            }

        }
    }





