import java.util.ArrayList;

public class SJF implements Runnable{
    
    public static int freememory = 1024;
    public int avreagewaitingtime =0;
    public static ArrayList<PCB> readyqueue = new ArrayList<PCB>();

    public static ArrayList<PCB> getMyList() {
        return readyqueue;
        }   
    public static int getfreememory(){
        return freememory;
            }       

    @Override
    public void run(){
        
        PCB sj = Filereader.getMyList().get(0);
        //shortest job is first job right now

        //find the shortest job in the jobqueue.
        while(!Filereader.getMyList().isEmpty()){
        for(int i=0; i<Filereader.getMyList().size(); i++){
           if(sj.bursttime > Filereader.getMyList().get(i).bursttime)
           sj = Filereader.getMyList().get(i);
        }
        while(freememory < sj.memory);
            freememory = freememory - sj.memory;
           
            readyqueue.add(sj);//add to ready queue.
            Filereader.getMyList().remove(sj); //remove from job queue.

    }
}
}
