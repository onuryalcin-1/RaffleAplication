
import java.awt.HeadlessException;
import javax.swing.JFrame;


public class OyunEkrani extends JFrame{

    public OyunEkrani(String title) throws HeadlessException {
        super(title);
    }
    
    public static void main(String[] args) {
        OyunEkrani ekran = new OyunEkrani("Uzay Oyunu");
        
        ekran.setResizable(false);//Yapılacak bütün işlemlerin JPanel üzerinde focuslanması için böyle yaptık
        ekran.setFocusable(false);//Yapılacak bütün işlemlerin JPanel üzerinde focuslanması için böyle yaptık
        
        ekran.setSize(800,600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Oyun oyun = new Oyun();
        
        oyun.requestFocus();//Klavyeden girilen işlemlerin algılanması için oluşturulur
        
        oyun.addKeyListener(oyun);//Klavyeden girilecek işlemleri almamızı sağlar
        
        oyun.setFocusable(true);//JFrame(Oyun ekranı) üzerinde false yapılan focuslanmanın JPanel üzerine true ile çekilmesidir.
        
        oyun.setFocusTraversalKeysEnabled(false);//Klavyeden girilen işlemlerin anlaşılabilmesi için oluşturulur false olması gerekir
        //yukarıdaki fonksiyonların sırası klavyeden veri almak için önemlidir.
        //1- JPanel.requestFocus()
        //2- JPanel.addKeyListener(JPanel)
        //3- JPanel.setFocesable(true)
        //4- JPanel.setFocusTraversalKeyEnabled
        ekran.add(oyun);
        
        ekran.setVisible(true);
        
    }
    
}
