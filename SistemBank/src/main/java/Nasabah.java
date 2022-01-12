
public class Nasabah {
    private final String namaNasabah;
    private final Rekening rekening;
    
    public Nasabah(String name, Rekening rekening){
        this.namaNasabah = name;
        this.rekening = rekening;
        System.out.println("\nInfo Rekening\n" + "Nama Nasabah : " +namaNasabah+"\n"+rekening);
    }
    
    public String basicInfo(){
        return "Nomor rekening " + rekening.getNomorRekening() +"- Nama Nasabah : "+namaNasabah;
    }
    
    Rekening getRekening(){
        return rekening;
    }
}
