package App.ChangerEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import App.utils.Color;

public class InputChanger {
    private Changer actions;
    private int action;
    private List<Changer> changers= new ArrayList<>();
    public InputChanger(){
        actions= new ChangerName();
        this.changers.add(actions);
        actions= new ChangerAdress();
        this.changers.add(actions);
        actions= new ChangerTypeEmployee();
        this.changers.add(actions);
        actions= new ChangerPay();
        this.changers.add(actions);
        actions= new ChangerDataSynd();
        this.changers.add(actions);
        actions= new ChangerIdSynd();
        this.changers.add(actions);
        actions= new ChangerTaxSynd();
        this.changers.add(actions);
    }
    public int input(){
        Scanner input= new Scanner(System.in);
        this.action= input.nextInt();
        if(this.action-1 < 0 || this.action-1>=7){ 
            System.out.println(Color.RED+"Invalid Input"+Color.RESET);
            return 0;
        }
        return 1;
    }
    public Changer getAction() {
        return changers.get(this.action-1);
    }
    
}
