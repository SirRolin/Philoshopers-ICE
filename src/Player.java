import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    public String name;
    public int str;
    public int agi;
    public int con;
    public int wits;
    public int willPower;
    public int magi;
    private int hp;
    private int mp;
    ArrayList<Boon> boons;
    Inventory inventory;

    public Player(String name){
        this.name = name;
    }
    public Player(String name, int str, int agi, int con,int wits,int willPower,int magi,int hp,int mp){
        this.name = name;
        this.str = str;
        this.agi = agi;
        this.con = con;
        this.wits = wits;
        this.willPower = willPower;
        this.magi = magi;
        this.hp = hp;
        this.mp = mp;
        inventory = new Inventory();
    }
    /*Player(Object obj){
    }*/
    public int getMaxHP(){
        return con*3+50;
    }
    public int getMaxMP(){
        return willPower*2+5;
    }
    public int getSpeed(){
        return agi*2+5;
    }
    public int getInitiative(){
        return wits*2+3;
    }
    public int getSpellBuffProc(){
        return magi*2+30;
    }
    public void increaseHP(int hp){
        this.hp += hp;
    }
    public void increaseMP(int mp){
        this.mp += mp;
    }
    public int getHP(){
        return this.hp;
    }
    public int getMP(){
        return this.mp;
    }
    public void recover(){
        this.hp += (con+(str/2)+willPower)+ 20;
    }
}
