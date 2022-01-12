import java.util.ArrayList;
public class Bank {
    ArrayList<Nasabah> nasabahBank = new ArrayList<>();
    
    public void tambahNasabah(Nasabah nasabah){
        nasabahBank.add(nasabah);
    }
    
    Nasabah getNasabah(int rekening){
        return nasabahBank.get(rekening);
        
    }
    
    ArrayList<Nasabah> getNasabahBank(){
        return nasabahBank;
    }
    
}
