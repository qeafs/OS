import java.util.ArrayList;

public class SJF implements Runnable{
    private static volatile int freememory = 1024;
    public int avreagewaitingtime =0;
    public static volatile int totaljobs=0;
    
    public static int getTotaljobs() {
        return totaljobs;
    }
    public static ArrayList<PCB> readyqueue = new ArrayList<PCB>();

    public static ArrayList<PCB> getMyList2() {
        return readyqueue;
        }   
    public static synchronized int getfreememory(){
        return freememory;
            }       

    public static synchronized void setFreememory(int freememory) {
        SJF.freememory = freememory;
    }


    @Override
    public void run(){
         
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        totaljobs = Filereader.getMyList().size();
        System.out.println("total jobs: "+totaljobs);

        while (!Filereader.getMyList().isEmpty()) {
            sjftoreadyqueue();
        }

        System.out.println(" I am done with moving The jobs to ready queue");
           
     }



        public synchronized void sjftoreadyqueue(){

            //find the shortest job in the jobqueue.
            while(!Filereader.getMyList().isEmpty()){
                PCB sj = Filereader.getMyList().get(0);//shortest job is first job right now

            for(int i=0; i<Filereader.getMyList().size(); i++){
                if(sj.bursttime > Filereader.getMyList().get(i).bursttime)
                sj = Filereader.getMyList().get(i);
         
             }
          
             while(getfreememory() < sj.memory){ 
                //System.out.println();
             }
                setFreememory(getfreememory() - sj.memory);
                //System.out.println("\nJob "+sj.id+" entred the ready queue Freemem = "+getfreememory()+"Mb");
                readyqueue.add(sj);//add to ready queue.
                Filereader.getMyList().remove(sj); //remove from job queue.
                System.out.println("\nJob "+sj.id+" entred the ready queue");
         
            }
                
        }
    

     
}
