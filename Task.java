package sample;

import java.time.LocalDate;


public class Task {
    private String taskname;
    private LevelOfPriority level;
    private LocalDate expiringdate;
    private String addnotation;

    public Task(String taskname, LevelOfPriority level, LocalDate expiringdate, String addnotation){
        this.taskname=taskname;
        this.level=level;
        this.expiringdate=expiringdate;
        this.addnotation=addnotation;
    }

    public String getTaskname() {
        return taskname;
    }
    public LevelOfPriority getLevel(){
        return level;
    }
    public LocalDate getExpiringdate(){
        return expiringdate;
    }

    public void Print(){
        System.out.println(this.taskname);
        System.out.println(this.level);
        System.out.println(this.expiringdate);
    }

    public String toString(){
        return this.taskname+" "+this.getExpiringdate();
    }

    public String toAlert(){
        return this.taskname+'\n'+this.getExpiringdate()+'\n'+this.getLevel()+'\n'+this.getAddnotation();
    }
    public String getAddnotation(){
        return this.addnotation;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setLevel(LevelOfPriority level) {
        this.level = level;
    }

    public void setExpiringdate(LocalDate expiringdate) {
        this.expiringdate = expiringdate;
    }

    public void setAddnotation(String addnotation) {
        this.addnotation = addnotation;
    }

}
