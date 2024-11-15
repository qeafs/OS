public class PCB {
int id;
int bursttime;
int memory;

int WaitingTime;
int startingtime;
int finishtime;
int ogbursttime;
double TurnaroundTime;

public PCB(int id, int bursttime, int memory) {
    this.id = id;
    this.bursttime = bursttime; 
    this.memory = memory;
    
    this.WaitingTime = 0;
    this.TurnaroundTime = 0;
    this.startingtime =0;
    this.finishtime =0;
    this.ogbursttime = bursttime;
}

public PCB(PCB pcb){
    this.id = pcb.id;
    this.bursttime = pcb.bursttime;
    this.memory = pcb.memory;
 
    this.WaitingTime = pcb.WaitingTime;
    this.TurnaroundTime = pcb.TurnaroundTime;
    this.startingtime =pcb.startingtime;
    this.finishtime =pcb.finishtime;
    this.ogbursttime = pcb.bursttime;
}
public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getBursttime() {
    return bursttime;
}

public void setBursttime(int bursttime) {
    this.bursttime = bursttime;
}

public int getMemory() {
    return memory;
}

public void setMemory(int memory) {
    this.memory = memory;
}


public int getWaitingTime() {
    return WaitingTime;
}

public void setWaitingTime(int waitingTime) {
    this.WaitingTime = waitingTime;
}

public double getTurnaroundTime() {
    return TurnaroundTime;
}

public void setTurnaroundTime(double turnaroundTime) {
    this.TurnaroundTime = turnaroundTime;
}

public int getStartingtime() {
    return startingtime;
}

public void setStartingtime(int startingtime) {
    this.startingtime = startingtime;
}

public int getFinishtime() {
    return finishtime;
}

public void setFinishtime(int finishtime) {
    this.finishtime = finishtime;
}


}
