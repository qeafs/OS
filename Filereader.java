import java.util.ArrayList;

public class Filereader extends Thread{
    
    public ArrayList<ProcessTable> pcb = new ArrayList<ProcessTable>();
//array list each item is an object from Process table each object contains the neccecarry infrormation 
// the whole array list IS the process Table (that is my idea)

        



@Override
public void run(){
    //This thread should read the text file and create jobs and add them to the job queue.

    //while reading the file
    while(true){
        //create a process(job)
    ProcessTable job1 = new ProcessTable(1,30,4); //examble
    //add to PCB
    pcb.add(job1);}
    
}

}
