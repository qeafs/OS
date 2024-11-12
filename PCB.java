public class PCB {
int id;
int bursttime;
int memory;

double WaitingTime;
double TurnaroundTime;

public PCB(int id, int bursttime, int memory) {
    this.id = id;
    this.bursttime = bursttime; 
    this.memory = memory;
    
    this.WaitingTime = 0;
    this.TurnaroundTime = 0;
}

public PCB(PCB pcb){
    this.id = pcb.id;
    this.bursttime = pcb.bursttime;
    this.memory = pcb.memory;
 
    this.WaitingTime = pcb.WaitingTime;
    this.TurnaroundTime = pcb.TurnaroundTime;
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


public double getWaitingTime() {
    return WaitingTime;
}

public void setWaitingTime(double waitingTime) {
    this.WaitingTime = waitingTime;
}

public double getTurnaroundTime() {
    return TurnaroundTime;
}

public void setTurnaroundTime(double turnaroundTime) {
    this.TurnaroundTime = turnaroundTime;
}


}
