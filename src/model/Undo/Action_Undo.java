package model.Undo;

import java.util.LinkedList;
import java.util.List;
import App.Employee;
import App.Syndicate;

import App.Color;

public class Action_Undo {

    private LinkedList<Undo> Hrundo= new LinkedList<>();
    private LinkedList<Undo> undo= new LinkedList<>();
    private LinkedList<Undo> redo= new LinkedList<>();

    public void setUndo(Undo undo) {
        this.undo.add(undo);
    }
    public void setRedo(Undo undo) {
        this.redo.add(undo);
    }
    public void setHrundo(Undo undo) {
        this.Hrundo.add(undo);
    }
    public void reset(){
        this.redo= new LinkedList<>();
    }
    public boolean undo(){
        if(undo.isEmpty()){
            
            return true;
        }
        return false;
    }
    public boolean redo(){
        if(redo.isEmpty()){
            
            return true;
        }
        return false;
    }
    public void execute_undo(List<Employee> employeelist, List<Syndicate> syndicatelist){
        Undo sredo=null;
        sredo = this.undo.getLast().execute(sredo, employeelist, syndicatelist);
        this.redo.add(sredo);
        this.Hrundo.add(undo.removeLast());
        System.out.println(Color.GREEN+"Your last action was successfully undone."+Color.RESET);
    }
    public void execute_redo(List<Employee> employeelist, List<Syndicate> syndicatelist){
        redo.getLast().execute(employeelist, syndicatelist);
        redo.removeLast();
        undo.add(Hrundo.removeLast());
        System.out.println(Color.GREEN+"Your last action was successfully redone."+Color.RESET);
    }

}
