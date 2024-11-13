import java.util.ArrayList;

public class SJF implements Runnable{
    
    public static int freememory = 1024;
    public int avreagewaitingtime =0;
    public static ArrayList<PCB> readyqueue = new ArrayList<PCB>();

    public static ArrayList<PCB> getMyList2() {
        return readyqueue;
        }   
    public static int getfreememory(){
        return freememory;
            }       

    public static void setFreememory(int freememory) {
        SJF.freememory = freememory;
    }
    @Override
    public void run(){
         
        
        
            
        //find the shortest job in the jobqueue.
        while(!Filereader.getMyList().isEmpty()){
            PCB sj = Filereader.getMyList().get(0);//shortest job is first job right now
            
         for(int i=0; i<Filereader.getMyList().size(); i++){
           if(sj.bursttime > Filereader.getMyList().get(i).bursttime)
           sj = Filereader.getMyList().get(i);
            }
            if(freememory >= sj.memory){    
            //freememory = freememory - sj.memory;
            readyqueue.add(sj);//add to ready queue.
            System.err.println("SJ id is :"+sj.id);
            for(int i =0; i<Filereader.getMyList().size(); i++)
            System.out.println(Filereader.getMyList().get(i).id);  // Debugging output

            Filereader.getMyList().remove(sj); //remove from job queue.
            }
        }
           
}
}
