package model.Undo;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import App.Employee;
import App.Hourly;
import App.Syndicate;
public class SalveTime extends Undo {
    History hist;
    public SalveTime(int option,Double pay, Employee employee, int aux){
        hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        hist.soption=option;
        if(aux == 2){
            hist.spay= pay;
        }
        
    }
    public SalveTime(int option,Double pay, Employee employee, int aux, Date date, LocalTime datetime){
        hist= new History();
        hist.sobj=aux;
        hist.semployee=employee;
        hist.soption=option;
        hist.datehours= date;
        hist.datetimehours= datetime;
        if(aux == 2){
            hist.spay= pay;
        }
        
    }
    public void Undo_(){
        if(hist.sobj==1){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).resetEntry();
            }else{
                System.out.println("Something went wrong!");
            }
        }
        else if(hist.sobj==2){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).resetExit();
                ((Hourly)hist.semployee).setPay(hist.spay);
                hist.spay=0d;
            }else{
                System.out.println("Something went wrong!");
            }
        }
      
    }
    public void Redo_(){
        if(hist.sobj==1){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).getCard().setDate(hist.datehours);
                ((Hourly)hist.semployee).getCard().setEntraDate(hist.datetimehours);
            }else{
                System.out.println("Something went wrong!");
            }
        }
        else if(hist.sobj==2){
            if(hist.semployee.typeEmployee().equals("Hourly")){
                ((Hourly)hist.semployee).getCard().setExitdate(hist.datehours);
                ((Hourly)hist.semployee).getCard().setExiDate(hist.datetimehours);
                ((Hourly)hist.semployee).setPay(hist.spay);
                hist.spay=0d;
            }else{
                System.out.println("Something went wrong!");
            }
        }
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo =this.auxRedo.redotime(reUndo, hist);
        this.Undo_();
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Redo_();
    }
}
