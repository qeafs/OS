import java.util.ArrayList;

public class Filereader extends Thread{
    
public ArrayList<ProcessTable> pcb = new ArrayList<ProcessTable>();
        ProcessTable pcb1 = new ProcessTable();

        



@Override
public void run(){
    pcb1.setId(30);         //testing how to add processes to the PCB
    pcb1.setMemory(50);
    pcb1.setTime(4);
    pcb.add(pcb1);
}

}
