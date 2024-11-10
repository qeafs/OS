import java.util.ArrayList;

public class ProcessTable {
    
    public ProcessTable(int id, int time, int memory){
        this.id = id;
        this.time = time;
        this.memory = memory;
    }

    int id;
    int time;
    int memory;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory = memory;
    }

    

}
