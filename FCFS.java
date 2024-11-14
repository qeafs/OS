import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS implements Runnable {
    
    int freememory = 1024;
    private final Queue<PCB> readyQueue;
    ArrayList<PCB> jobqueue;

    public FCFS() {
        readyQueue = new LinkedList<>();
        jobqueue = Filereader.getMyList();
    }

    public void addProcess(PCB process) {
        if (process.getMemory() <= freememory) {
            readyQueue.offer(process);
            freememory -= process.getMemory();
            System.out.println("Process " + process.getId() + " added to ready queue");
        } else {
            System.out.println("Not enough memory");
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);  // Give file reader time to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while (true) {
            moveProcessesToReadyQueue();
            executeProcesses();
        }
    }

    private void moveProcessesToReadyQueue() {
        for (int i = 0; i < jobqueue.size(); i++) {
            PCB process = jobqueue.get(i);
            if (freememory >= process.getMemory()) {
                addProcess(process);
                jobqueue.remove(i);
                i--;
            }
        }
    }

    private void executeProcesses() {
        if (!readyQueue.isEmpty()) {
            PCB currentProcess = readyQueue.poll();
            System.out.println("Executing process " + currentProcess.getId());

            try {
                Thread.sleep(currentProcess.getBursttime() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            freememory += currentProcess.getMemory();
            System.out.println("Process " + currentProcess.getId() + " finished");
        }
    }
}
