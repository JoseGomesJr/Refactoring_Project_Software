import java.util.*;

public class PayDay {
    private List<String> schedule= new ArrayList<>();
    private int day;
    private int week;
    PayDay(){
        this.schedule.add("WEEKLY 1 FRIDAY");
        this.schedule.add("WEEKLY 2 FRIDAY");
        this.schedule.add("MONTLHY $");
    }
    public void addSchedulle(String schedule){
        this.schedule.add(schedule);
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setWeek(int week) {
        this.week = week;
    }
    public int getDay() {
        return day;
    }
    public int getWeek() {
        return week;
    }
    public List<String> getSchedule() {
        return schedule;
    }
    public String opitionString(int select){
        return schedule.get(select);
    }

}
