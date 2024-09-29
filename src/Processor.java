public class Processor {
    private int index;
    private int power;
    private boolean isFree;
    private int timeWorkTask;
    private int timeWork;
    private int timeSleep;

    public Processor(int index, int power) {
        this.index = index;
        this.power = power;
        this.isFree = true;
        this.timeWorkTask = 0;
        this.timeWork = 0;
        this.timeSleep = 0;
    }

    public int getIndex() {
        return index;
    }

    public int getPower() {
        return power;
    }

    public boolean isFree() {
        return isFree;
    }

    public int getTimeWorkTask() {
        return timeWorkTask;
    }

    public int getTimeWork() {
        return timeWork;
    }

    public int getTimeSleep() {
        return timeSleep;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public void setTimeWorkTask(int timeWorkTask) {
        this.timeWorkTask = timeWorkTask;
    }

    public void resetProcessor(){
        timeSleep = 0;
        timeWork = 0;
        timeWorkTask = 0;
    }

    public void run(){
        if(isFree){
            timeSleep++;
        }
        else {
            timeWork++;
            timeWorkTask--;
            if(timeWorkTask == 0){
                isFree = true;
            }
        }
    }
}
