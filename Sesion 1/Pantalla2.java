import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
public class Pantalla2 extends JPanel {
    private Image fondo;
    JFrame ventana;
    public Pantalla2(JFrame ventana){
        this.ventana = ventana;
        setLayout(null);
        cargarImagenFondo("fondo.png");
        iniciarBotones();
    }
    private void cargarImagenFondo(String ruta){
        fondo = new ImageIcon(ruta).getImage();
    }

    private void iniciarBotones(){

        JButton bt = new JButton("hola");
        bt.setBounds(100,100,100,40);
        add(bt);
        bt.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String url="https://empresanuevaf3rn464.netlify.app/";
                    Desktop.getDesktop().browse(new URI(url));
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        JButton salir = new JButton(new ImageIcon("salir.png"));
        salir.setBounds(300,100,120,60);
        salir.setBorderPainted(false);
        salir.setContentAreaFilled(false);
        salir.setFocusPainted(false);
        add(salir);
        salir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(fondo!=null){
            g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
        }
    }
}