import java.text.DateFormat;
import java.util.*;
import java.time.Duration;
import java.time.LocalTime;
public class TimeCard {
    private LocalTime entraDate;
    private LocalTime exiDate;
    private Date date;
    private Date exitdate;
    public void EntTimeCard(){
        Calendar calendar= Calendar.getInstance();
        this.entraDate = LocalTime.now();
        this.date= calendar.getTime();
    }
    public void ExiTimeCard(){
        Calendar calendar= Calendar.getInstance();
        this.exiDate = LocalTime.now();
        this.exitdate= calendar.getTime();
    }
    public void getDateInfo(){
        DateFormat dtHora = DateFormat.getDateTimeInstance();
		if(date!=null) System.out.println("Entry data: "+dtHora.format(date));
        if(exitdate!=null) System.out.println("Departure data: "+dtHora.format(exitdate));
    }
    public double InforHoras(){
        Duration duration= Duration.between(entraDate, exiDate);
        return duration.toHours();
    }
    public void resetEntry(){
        this.entraDate=null;
        this.date=null;
    }
    public void resetExit(){
        this.exiDate=null;
        this.exitdate=null;
    }
    public LocalTime getEntraDate() {
        return entraDate;
    }
    public Date getDate() {
        return date;
    }
    public LocalTime getExiDate() {
        return exiDate;
    }
    public Date getExitdate() {
        return exitdate;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setEntraDate(LocalTime entraDate) {
        this.entraDate = entraDate;
    }
    public void setExiDate(LocalTime exiDate) {
        this.exiDate = exiDate;
    }
    public void setExitdate(Date exitdate) {
        this.exitdate = exitdate;
    }
}
