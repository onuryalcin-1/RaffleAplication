
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class AnaEkran extends JFrame{

    public AnaEkran(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        
    
        GrafikKullanimi grafik = new GrafikKullanimi();

        AnaEkran ekran = new AnaEkran("Grafik Kullanımı");
        
        ekran.setVisible(true);//JFrame ekranını gösterir
        ekran.setResizable(true);//ekranın köşelerden çekilerek boyutlandırılmasını sağlar
        ekran.setSize(800,600); //Ekranın x,y koordinatlarında boyutunu belirler (0,0 sol üst köşeden başlar)
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Close işlemini set eder Exit On CLose çarpıya basmak anlamına gelir
        
        ekran.add(grafik);
    
    }

    
    
}
