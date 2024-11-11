import java.util.*;
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
        
    }

    public static void doRR(){
            
    }
    public static void doFCFS(){

    }

    public static void doSJF(){
        SJF runnable = new SJF();
        Thread thread1 = new Thread(runnable);

    }
}    
