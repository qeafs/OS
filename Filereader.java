import java.util.ArrayList;

public class Filereader extends Thread{
    
    public ArrayList<ProcessTable> pcb = new ArrayList<ProcessTable>();

//array list each item is an pbject from Process table each object contains the neccecarry infrormation 
// the whole array list IS the process Table 
        ProcessTable pcb1 = new ProcessTable(1,30,4);

        



@Override
public void run(){
    //just testing 
    pcb.add(pcb1);
}

}
