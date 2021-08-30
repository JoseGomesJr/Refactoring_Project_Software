package model.Undo;

import java.util.*;

import App.Employee;
import App.Syndicate;

public class SalveIDSyn extends Undo {
    public  History hist;
    public SalveIDSyn(int option, int idsyn, int sobj){
        hist= new History();
        hist.soption= option;
        hist.idsyn= idsyn;
        hist.sobj= sobj;

    }
    public void Undo_Redo(List<Syndicate> syndicatelist){
        syndicatelist.get(hist.sobj).setId(hist.idsyn);
        
    }
    @Override
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates){
        Undo redo = this.auxRedo.rediddyn(reUndo, hist, syndicates);
        this.Undo_Redo(syndicates);
        return redo;
    }
    @Override
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        this.Undo_Redo(syndicates);
    }
}
