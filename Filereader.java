import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.List;
import java.util.Scanner;

public class Filereader implements Runnable{
    public static ArrayList<PCB> jobqueue = new ArrayList<PCB>();
//array list each item is an object from Process table each object contains the neccecarry infrormation 
// the whole array list IS the process Table (that is my idea)
        public static ArrayList<PCB> getMyList() {
            return jobqueue;
      }   

        


@Override
public void run() {
    //This thread should read the text file and create jobs and add them to the job queue.

    //while reading the file
   readtextfile("C:\\Users\\moham\\OneDrive\\Desktop\\job.txt");
}




public void readtextfile(String fileName) {
    if (!(fileName.toLowerCase().endsWith(".txt"))) {
        return;
    }
    
    try {
        Scanner fileScanner = new Scanner(new File(fileName));
        
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] parts = line.split("[:|;]"); // تقسيم السطر باستخدام : أو ;
            
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                int burstTime = Integer.parseInt(parts[1]);
                int memory = Integer.parseInt(parts[2]);
                
                PCB pcb = new PCB(id, burstTime, memory);
                jobqueue.add(pcb);
            }
        }
        fileScanner.close();
        
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + fileName);
    } catch (NumberFormatException e) {
        System.err.println("Error parsing numbers in file");
    }
}




}

