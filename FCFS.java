import java.util.ArrayList;

public class FCFS implements Runnable {

    public static int freememory = 1024; // Total available memory
    public static boolean empty = false; // Flag to indicate job queue is empty
    public static ArrayList<PCB> readyQueue = new ArrayList<>(); // Ready queue
    public static ArrayList<PCB> DoneProcesses = new ArrayList<>(); // Completed processes
    public static boolean nextprocessisreadytoenter = false;

    public static ArrayList<PCB> getReadyQueue() {
        return readyQueue;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100); // Simulate file reader startup delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            movetoready();

            if (Filereader.getMyList().isEmpty()) {
                empty = true; // Mark job queue as empty
            }

            if (empty && readyQueue.isEmpty()) {
                break; // Stop when both job queue and ready queue are empty
            }
        }
    }

    private void moveProcessesToReadyQueue() {
        for (int i = 0; i < Filereader.getMyList().size(); i++) {
            PCB process = Filereader.getMyList().get(0);
            if(freememory >= process.memory){nextprocessisreadytoenter = true; System.out.println(" next process is ready");}
            else nextprocessisreadytoenter = false;

            while(freememory < process.memory); 
                freememory -= process.memory; // Allocate memory
                readyQueue.add(process); // Add to ready queue
                Filereader.getMyList().remove(i); // Remove from job queue
                System.out.println("Process "+process.id+" added ");
                i--;
        }
    }

    private void movetoready(){
        while(!Filereader.getMyList().isEmpty()){
            PCB cp = Filereader.getMyList().get(0);//shortest job is first job right now

            if(freememory >= cp.memory){    
            freememory = freememory - cp.memory;
            readyQueue.add(cp);//add to ready queue.
            Filereader.getMyList().remove(cp); //remove from job queue.
            }
        }
           
    }
}
