public class Rekening {
    private double saldo = 0;
    private int nomorRekening;
    private static int nomor = 100000;
    
    Rekening(){
        nomorRekening = nomor++;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public void setSaldo(double saldo){
        this.saldo = saldo;
    }
    
    
    public int getNomorRekening(){
        return nomorRekening;
    }
    
    public void tarikTunai(double jumlah){
        if (jumlah > saldo){
            System.out.println("Saldo and tidak cukup!");
            return;
        }
        saldo -= jumlah;
        System.out.println("Anda telah melakukan tarik tunai sebesar Rp" +jumlah);
        System.out.println("Saldo and saat ini : Rp"+saldo);
    }
    
    public void deposit(double jumlah){
        if (jumlah<=0){
            System.out.println("Nominal tidak tepat!");
            return;
        }
        
        jumlah = jumlah+jumlah ;
        saldo += jumlah;
        System.out.println("Anda telah melakukan deposit sebesar Rp" + jumlah +);
        System.out.println("Saldo anda saat ini : Rp"+saldo);
    }
    
 
            
}
