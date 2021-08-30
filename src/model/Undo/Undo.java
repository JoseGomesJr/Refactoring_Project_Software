package model.Undo;

import java.util.*;

import App.Employee;
import App.Syndicate;
public class Undo {
    //Acões que foram efetuadas
    public static final int NEWEMP=1;
    public static final int REMOVEEMP=2;
    public static final int TIME=3;
    public static final int COMISSION=4;
    public static final int TAXSERVICE=5;
    public static final int CHANGERNAME=61;
    public static final int CHANGERADRESS=62;
    public static final int CHANGERTYPE=63;
    public static final int CHANGERPAY=64;
    public static final int CHANGERSYND=65;
    public static final int CHANGERIDSYN=66;
    public static final int CHANGERTAXSYND=67;
    public static final int PAY=7;
    //Meta dados a serem salvos para possivel retrocesso
    protected AuxRedo auxRedo= new AuxRedo();
    
    public void execute(List<Employee> employees, List<Syndicate> syndicates){
        System.out.println("Algo deu errado.");
    }
    public Undo execute(Undo reUndo, List<Employee> employees, List<Syndicate> syndicates) {
        System.out.println("Algo deu errado.");
        return null;
    }
}
