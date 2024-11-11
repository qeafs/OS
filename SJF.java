import java.util.ArrayList;

public class SJF implements Runnable{
    
    int freememory = 1024;
    public static ArrayList<ProcessTable> readyqueue = new ArrayList<ProcessTable>();


    @Override
    public void run(){
        
        ProcessTable sj = new ProcessTable(Filereader.getMyList().get(0)); 
        //shortest job is first job right now

        //find the shortest job in the jobqueue.
        for(int i=0; i<Filereader.getMyList().size(); i++){
           if(sj.getTime() > Filereader.getMyList().get(i).time)
           sj = Filereader.getMyList().get(i);
        }
        if(freememory >= sj.memory){
            freememory = freememory - sj.memory;
            readyqueue.add(sj);
        }
       

    }
}
