import java.util.ArrayList;

public class SJF implements Runnable{
    private static final int MAX_MEMORY = 1024;
    private static volatile int freememory = MAX_MEMORY;
    public int avreagewaitingtime =0;
    public static int totaljobs=0;
     public static final Object LOCK = new Object(); // Shared lock
    
    public static int getTotaljobs() {
        return totaljobs;
    }
    private static final ArrayList<PCB> readyqueue = new ArrayList<>();


    public static synchronized ArrayList<PCB> getMyList2() {
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
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        totaljobs = Filereader.getMyList().size();
        System.out.println("total jobs: "+totaljobs);
                while(!Filereader.getMyList().isEmpty())sjftoreadyqueue();
     }



        public void sjftoreadyqueue(){

            if(Filereader.getMyList().size() == 0)return;
            //find the shortest job in the jobqueue.
                PCB sj = Filereader.getMyList().get(0);//shortest job is first job right now
           
            for(int i=0; i<Filereader.getMyList().size(); i++){
                if(sj.bursttime > Filereader.getMyList().get(i).bursttime)
                sj = Filereader.getMyList().get(i);
         
             }
          
             synchronized (LOCK) {
                while (getfreememory() < sj.memory) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
    
                setFreememory(getfreememory() - sj.memory);
                readyqueue.add(sj);
                Filereader.getMyList().remove(sj);
                LOCK.notifyAll();
            }
                
                
            
        }

    }

