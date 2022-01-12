
public class TahapanGold extends Rekening {
    private static String tipeRekening = "Tahapan Gold";
    
    TahapanGold(double jumlahDeposit){
        this.setSaldo(jumlahDeposit);
        this.setBunga(0.05);   
    }
    @Override
    public String toString(){
       return "Tipe Rekening : "+tipeRekening+"\n"+
               "Nomor Rekening : "+this.getNomorRekening()+"\n"+
               "Saldo : "+this.getSaldo()+"\n"+
               "Bunga : "+this.getBunga()+"%\n";
    }
}
