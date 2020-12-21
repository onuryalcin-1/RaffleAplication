
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Main {
    public static ArrayList<Integer> icerik = new ArrayList<Integer>();
    public static void dosyaOku(){
        try {
            FileInputStream in = new FileInputStream("mabel_matiz_toy_mp3_74880.mp3");
            
            int oku;
            while((oku = in.read()) != -1){
                icerik.add(oku);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya Bulunamadı");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
}
    public static void dosyaKopyala(){
        try {
            FileOutputStream out = new FileOutputStream("MabelSarki.mp3");
            
            for(int deger : icerik){
                out.write(deger);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        long baslangic = System.currentTimeMillis();
        dosyaOku();
        
        dosyaKopyala();
        
        long bitis = System.currentTimeMillis();
        
        System.out.println("Kopyalama " + (bitis - baslangic)/1000 + " sn sürdü.");
    }
}
