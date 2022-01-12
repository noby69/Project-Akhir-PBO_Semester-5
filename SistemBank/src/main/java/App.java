import java.util.ArrayList;
import java.util.Scanner;

public class App {
    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    Nasabah nasabah;
    String tipeRekening;
    String namaNasabah;
    double jumlahDeposit;
   
    public static void main(String args[]) {
        App app = new App();
        app.jalankanMenu();
    }
    
    public void jalankanMenu() {
        System.out.println("+--------------------------+");
        System.out.println("|      Selamat Datang      |");
        System.out.println("|         Bank ABC         |");
        System.out.println("+--------------------------+");
        System.out.println("\n##### Pilih menu dibawah ini! #####");
        System.out.println("1) Buat rekening baru");
        System.out.println("2) Deposit");
        System.out.println("3) Tarik tunai");
        System.out.println("4) Tampilkan daftar rekening");
        System.out.println("0) Exit");
        int pilihan = getInput();
        proses(pilihan);
    }
    
    private int getInput() {
        int pilihan = -1;
        do {
            System.out.println("Masukkan pilihan anda: ");
            pilihan = Integer.parseInt(keyboard.nextLine());
            if (pilihan < 0 || pilihan > 4) {
                System.out.println("Pilihan salah! Silahkan pilih lagi");
            }
        } while (pilihan < 0 || pilihan > 4);
        return pilihan;
    }   
    
    private void proses(int pilihan) {
        switch (pilihan) {
            case 0:
                System.out.println("Terima kasih telah menggunakan aplikasi kami!");
                System.exit(0);
                break;
            case 1:
                buatRekening();
                break;
            case 2:
                deposit();
                break;
            case 3:
                tarikTunai();
                break;
            case 4:
                tampilkanDaftarRekening();
                break;
        }
    }
    
    private void buatRekening() {
        System.out.println("\n##### Buat Rekening #####");
        
        //Mengambil data informasi rekening
        tipeRekening = askQuestion("Masukkan tipe rekening anda (gold/junior): ");
        namaNasabah = askQuestion("Masukkan nama anda: ");
        jumlahDeposit = getDeposit(tipeRekening);
        
        //Membuat rekening baru
        Rekening rekening;
        if (tipeRekening.equalsIgnoreCase("gold")) {
            rekening = new TahapanGold(jumlahDeposit);
        } else {
            rekening = new TahapanJunior(jumlahDeposit);
        }
        System.out.println("Rekening berhasil di buat!");
        nasabah = new Nasabah(namaNasabah, rekening);
        bank.tambahNasabah(nasabah);
        jalankanMenu();
    }
    
    private String askQuestion(String pertanyaan) {
        //Membuat fungsi untuk membuat pertanyaan dan mengembalikan nilai jawaban
        String jawaban = "";
        Scanner input = new Scanner(System.in);
        System.out.println(pertanyaan);
        jawaban = input.nextLine();
        return jawaban;
    }
    
    private double getDeposit(String tipeRekening) {
        //Mengambil nilai jumlah deposit
        double jumlahDeposit = 0;
        boolean valid = false;
        while (!valid) {
            System.out.println("Masukkan jumlah deposit pertama anda : Rp. ");
            try {
                jumlahDeposit = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan data angka!");
            }
            if (tipeRekening.equalsIgnoreCase("gold")) {
                if (jumlahDeposit < 500000) {
                    System.out.println("Tipe Tahapan Gold memerlukan minimal Rp.500.000 untuk membuka rekening!");
                } else {
                    valid = true;
                }
            } else if (tipeRekening.equalsIgnoreCase("junior")) {
                if (jumlahDeposit < 50000) {
                    System.out.println("Tipe Tahapan Junior memerlukan minimal Rp.50.000 untuk membuka rekening");
                } else {
                    valid = true;
                }
            }
        }
        return jumlahDeposit;
    }
    
    private void deposit() {
        System.out.println("\n##### Deposit Tabungan #####");
        int pilihan = pilihRekening();
        if (pilihan >= 0) {
            double nominal = jumlahNominal("Berapa nominal yang ingin anda tabung ?: ");
            bank.getNasabah(pilihan).getRekening().deposit(nominal);
        }
        jalankanMenu();
    }
    
    private void tarikTunai() {
        System.out.println("\n##### Tarik Tunai #####");
        int pilihan = pilihRekening();
        if (pilihan >= 0) {
            double nominal = jumlahNominal("Berapa nominal yang ingin anda tarik ?: ");
            bank.getNasabah(pilihan).getRekening().tarikTunai(nominal);
        }
        jalankanMenu();
    }
    
    private double jumlahNominal(String pertanyaan) {
        System.out.println(pertanyaan);
        double nominal = 0;
        try {
            nominal = Double.parseDouble(keyboard.nextLine());
        } catch (NumberFormatException e) {
            nominal = 0;
        }
        return nominal;
    }
    
    private void tampilkanDaftarRekening() {
        System.out.println("\n##### Daftar Rekening #####");
        int pilihan = pilihRekening();
        if (pilihan >= 0) {
            System.out.println("\n##### Detail Rekening #####");
            System.out.println(bank.getNasabah(pilihan).getRekening());
        }
        jalankanMenu();
    }
    
    private int pilihRekening() {
        ArrayList<Nasabah> nasabahBank = bank.getNasabahBank();
        if (nasabahBank.size() <= 0) {
            System.out.println("Tidak ada nasabah di Bank!");
            jalankanMenu();
            return -1;
        }
        System.out.println("Pilih salah satu akun");
        for (int i = 0; i < nasabahBank.size(); i++) {
            System.out.println("\t" + (i+1) + ") " + nasabahBank.get(i).basicInfo());
        }
        int pilihan;
        System.out.println("Masukkan pilihan anda: ");
        try {
            pilihan = Integer.parseInt(keyboard.nextLine()) - 1;
        } catch (NumberFormatException e){
            pilihan = -1;
        }
        if (pilihan < 0 || pilihan > nasabahBank.size()) {
            System.out.println("Pilihan tidak tepat!");
            pilihan = -1;
        }
        return pilihan;
    }
}
