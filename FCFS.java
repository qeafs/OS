import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FCFS implements Runnable {
    
    int freememory = 1024;
    private final Queue<PCB> readyQueue;
    ArrayList<PCB> jobqueue;
    private boolean isRunning = true;
    private long totalExecutionTime = 0;
    private long idleTime = 0;
    private final long startTime;
    private ArrayList<Integer> turnaroundTimes = new ArrayList<>();
    private ArrayList<Integer> waitingTimes = new ArrayList<>();
    private ArrayList<String> processOrder = new ArrayList<>();

    public FCFS() {
        readyQueue = new LinkedList<>();
        jobqueue = Filereader.getMyList();
        startTime = System.currentTimeMillis();
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

        while (isRunning) {
            if (jobqueue.isEmpty() && readyQueue.isEmpty()) {
                System.out.println("All processes completed.");
                printStatistics();
                isRunning = false;
                break;
            }
            
            moveProcessesToReadyQueue();
            executeProcesses();
            
            if (readyQueue.isEmpty()) {
                try {
                    Thread.sleep(100);
                    idleTime += 100;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
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
            int waitTime = (int)(System.currentTimeMillis() - startTime) / 1000;
            waitingTimes.add(waitTime);
            
            System.out.println("J" + currentProcess.getId() + " | " + waitTime);
            processOrder.add("J" + currentProcess.getId());

            try {
                Thread.sleep(currentProcess.getBursttime() * 1000);
                int turnaroundTime = (int)(System.currentTimeMillis() - startTime) / 1000;
                turnaroundTimes.add(turnaroundTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            freememory += currentProcess.getMemory();
        }
    }

    private void printStatistics() {
        System.out.println("\nFCFS Output:");
        // Print process order and completion times
        System.out.print("| ");
        for (String process : processOrder) {
            System.out.print(process + " | ");
        }
        System.out.println();
        
        // Print times
        System.out.print("0 ");
        for (Integer time : turnaroundTimes) {
            System.out.print(time + " ");
        }
        System.out.println("\n");

        // Print Turnaround Times
        System.out.println("Turnaround Times:");
        for (int i = 0; i < processOrder.size(); i++) {
            System.out.println(processOrder.get(i) + ": " + turnaroundTimes.get(i) + "ms");
        }

        // Print Waiting Times
        System.out.println("\nWaiting Times:");
        for (int i = 0; i < processOrder.size(); i++) {
            System.out.println(processOrder.get(i) + ": " + waitingTimes.get(i) + "ms");
        }

        // Calculate and print averages
        double avgTurnaround = turnaroundTimes.stream().mapToInt(Integer::intValue).average().orElse(0);
        double avgWaiting = waitingTimes.stream().mapToInt(Integer::intValue).average().orElse(0);
        
        System.out.println("\nAverage Turnaround Time: " + String.format("%.2f", avgTurnaround) + "ms");
        System.out.println("Average Waiting Time: " + String.format("%.2f", avgWaiting) + "ms");
    }

    public void stop() {
        isRunning = false;
    }
}