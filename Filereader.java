import java.util.ArrayList;
import java.util.List;

public class Filereader implements Runnable{
    
    public static ArrayList<ProcessTable> pcb = new ArrayList<ProcessTable>();
//array list each item is an object from Process table each object contains the neccecarry infrormation 
// the whole array list IS the process Table (that is my idea)
        public static ArrayList<ProcessTable> getMyList() {
            return pcb;
      }   

        



@Override
public void run(){
    //This thread should read the text file and create jobs and add them to the job queue.

    //while reading the file
    while(true){
        //create a process(job)
    ProcessTable job1 = new ProcessTable(1,30,4); //examble
        //add to PCB
        pcb.add(job1); 
    }

}

}
