
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Ates{
    //Ateşimizin bir x, y koordinatı olacak ve her actionPerformed çalıştığında ateşimiz bir ileri gitmeye çalışacak
    
    private int x; //X Koordinatı
    private int y; //Y Koordinatı

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    


}

public class Oyun extends JPanel implements KeyListener,ActionListener{
        //KeyListener interface klavyeden bir tuşa basıldığında gerekli metodları kullanabilmemizi sağlar
        //ActionListener interface nesnelere hareket kazandırmak için kullanılır
    
    Timer timer = new Timer(5, this);
    private int gecen_sure = 0;
    private int harcanan_ates = 0;
    
    private BufferedImage image;//proje içerisindeki .png dosyasını  alarak JPanel üzerinde kullanmamız için obje oluşturduk
    
    private ArrayList<Ates> atesler = new ArrayList<Ates>();
    
    //Ateşlerimiz yukarı doğru gidiyor sağa sola gitmiyor. Ateşleri her timer çalıştığında 1 ileri götürmek için atesdirY = 1 olur
    //sağa sola hareket etmediği için topX = 0 tanımlarız
    private int atesdirY = 1; //Ateşler oluşacak ve bu ates her actionPerformed çalıştığında o ateşleri Y koordinatına ekleyeceğiz ve böylelikle ateşlerimiz hareket edecek
    
    private int topX = 0; //Sağa sola gitmeyi ayarlar ve ilk başta top 0,0 noktasından başlar bu top'ı sürekli bir artıracağız böylece tpumuz sürekli hareket edecek
    
    private int topdirX = 2;// topdirX sürekli topX e eklenecek, böylece topX sağda belli bir limite çarptığı zaman sola dönecek 
    
    private int uzayGemisiX = 0; // Uzay Gemisinin ilk olarak hangi noktadan başlayacağını gösterir
    
    private int dirUZayX = 20; // Bu sayede sağ veya sol yön tuşuna bastığımızda uzay gemisi 20 birim hareket edecek
    
    public boolean kontrolEt(){
        for(Ates ates : atesler){
            if(new Rectangle(ates.getX(), ates.getY(), 10, 20).intersects(new Rectangle(topX, 0,20,20))){
                
                //Intersects iki karenin birbirine çarpıp çarpmadığını kontrol etmek için kullanılır
                return true;
            }
        }
        return false;
        
    }
    public Oyun(){
        
        try {
            image = ImageIO.read(new FileInputStream(new File("uzaygemisi.png")));
            //image nesnesini ImageIO clasından okutarak uzaygemisi.png ekleyerek oluşturduk
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        setBackground(Color.black);//JPanel arka plan rengi siyah yapıldı.
        
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        gecen_sure += 5;
        g.setColor(Color.red);
        
        g.fillOval(topX, 0, 20, 20); 
        //Başlangıç noktası 0,0 Y hiç hareket etmeyeceği için direk 0 yazıldı. topX güncellendikçe top X-X yönünde hareket edecek.
        //20,20 topun çapı
        
        g.drawImage(image, uzayGemisiX, 490, image.getWidth()/10,image.getHeight()/10,this);
        
        for(Ates ates : atesler){
            if(ates.getY() < 0){
                atesler.remove(ates);
            }
        }
        g.setColor(Color.blue);
        
        for(Ates ates : atesler){
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        if(kontrolEt()){
            timer.stop();
            
            String message = "Kazandınıız \n"+
                             "Harcanan Ateş : " + harcanan_ates +"\n"+
                             "Geçen süre : " + gecen_sure / 1000.0;
                    
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
        
        //aslında Repaint çağrıldığında paintte birlikte çağrılır
        //repaint() oyunlarda kesin yazılmalıdır
        //ActionPerformed fonksiyonu yazıldığında bu metot en sonda yazılacak ve şekillerimizi yeniden çizme işlemini yapacak
        //Bu metot sayesinde paint yeniden çalıştırıl ve şekiller çizilir
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            if(uzayGemisiX <= 0){
                uzayGemisiX = 0;
            }else{
                uzayGemisiX -= dirUZayX;
            }
        }
        if(c == KeyEvent.VK_RIGHT){
            if(uzayGemisiX >= 750){
                uzayGemisiX = 750;
            }else{
                uzayGemisiX += dirUZayX;
            }
        }
        else if(c == KeyEvent.VK_CONTROL){
            atesler.add(new Ates(uzayGemisiX+15,470));
            
            harcanan_ates++;
        }
        
    }    
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        //ActionListener interface içerisinde ki bu actionPerformed metodu timer her çalıştığı zaman bu metot harekete geçer ve topları hareket ettirmeyi sağla
        for(Ates ates : atesler){
            ates.setY(ates.getY() - atesdirY);
        }
        
        
        
        
        topX += topdirX;
        
        if(topX >= 750){
            topdirX = -topdirX;
        }
        if(topX <=0){
            topdirX = -topdirX;
        }
        repaint();
    
    }
        
    
}
