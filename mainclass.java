import java.util.Scanner;

class mainclass{

    public static void main (String[] args){

        Scanner input = new Scanner(System.in);
           
        int done =0;
        while (done ==0){
            
        // ask the user for the method to do.
        System.out.println("What scheduling algorithm do you choose? (1,2,3) \n1) First-Come-First-Serve\r\n" + //
                        "2) Round-Robin\r\n" + //
                        "3) Shortest Job First");

        int algorithm = input.nextInt();
        //Now we execute what the user choose:
        
        if(algorithm == 1){
            doFCFS();
            done = 1;
        }
        else if(algorithm == 2){
            doRR();
            done =2;
        }
        else if(algorithm == 3){
            doSJF();
            done =3;
        }
        else{
            System.out.println("Please choose 1, 2, or 3");
        }
      }
    input.close();
    }

    public static void doRR(){  
        Filereader runnable = new Filereader(); 
        RR runnable2 = new RR();
        RR_Processes runnable3 = new RR_Processes();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        thread1.run();
        waitforabit();
        thread2.start();
        thread3.start();
    }
    
    public static void doFCFS(){
        Filereader runnable = new Filereader();
        FCFS runnable2 = new FCFS();
        FCFS_Processes runnable3 = new FCFS_Processes();
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        thread1.start();
        waitforabit();
        thread2.start();
        thread3.start();

    }

    public static void doSJF(){
        
        SJF runnable = new SJF();
        Filereader runnable2 = new Filereader();
        processreadyqueue runnable3 = new processreadyqueue();
        Thread thread3 = new Thread(runnable3);
        Thread thread2 = new Thread(runnable2);
        Thread thread1 = new Thread(runnable);
        thread2.start(); //start the file reader thread to fill the jobqueue
        //waitforabit();  //we wait for the file reader thread to get a head start 
        thread1.start();// start the sjf thread to fill the ready queue
      
        thread3.start();// start the processing thread .
        
           
    }
    static void waitforabit(){
        double t1 =  java.lang.System.currentTimeMillis();
        long x= -999999999;
        while(x<999999999)x++;
        
         double t2 = java.lang.System.currentTimeMillis();
         double t3 = t2-t1;
         System.err.println(t3+"ms taken by wait func");
     }
     
   
    
}
