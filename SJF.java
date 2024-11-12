import java.util.ArrayList;

public class SJF implements Runnable{
    
    int freememory = 1024;
    public static ArrayList<PCB> readyqueue = new ArrayList<PCB>();


    @Override
    public void run(){
        
        PCB sj = Filereader.getMyList().get(0);
        //shortest job is first job right now

        //find the shortest job in the jobqueue.
        while(true){
        for(int i=0; i<Filereader.getMyList().size(); i++){
           if(sj.bursttime > Filereader.getMyList().get(i).bursttime)
           sj = Filereader.getMyList().get(i);
        }
        while(!(freememory >= sj.memory)){
            freememory = freememory - sj.memory;
            sj.state = "ready";
            readyqueue.add(sj);
            Filereader.getMyList().remove(sj);
        }
       

    }
}
}
