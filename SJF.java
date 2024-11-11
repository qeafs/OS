import java.util.ArrayList;

public class SJF implements Runnable{
    
    int freememory = 1024;


    @Override
    public void run(){
        ProcessTable sj = new ProcessTable(Filereader.getMyList().get(0)); //shortest job is first job right now
        for(int i=0; i<Filereader.getMyList().size(); i++){
           
        }
       

    }
}
