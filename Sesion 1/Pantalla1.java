import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Pantalla1 extends JPanel implements ActionListener{
    private Image fondo;
    JFrame ventana;
    public Pantalla1(JFrame ventana){
        this.ventana =ventana;
        setLayout(null);
        cargarImagenFondo("fondo.png");
        iniciarBoton();
    }
    private void cargarImagenFondo(String ruta){
        fondo = new ImageIcon(ruta).getImage();
    }
    private void iniciarBoton(){
        JButton act = crearBoton("boton.png","boton.png",100,100);
        add(act);
        act.addActionListener(e ->{
            ventana.setContentPane(new Pantalla2(ventana));
            ventana.revalidate();
        });
    }
    private JButton crearBoton(String rutaA, String rutaB, int x, int y){
        JButton btn = new JButton(new ImageIcon(rutaA));
        btn.setRolloverIcon(new ImageIcon(rutaB));
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setBounds(x,y,200,50);
        return btn;
    }
    public void actionPerformed(ActionEvent e){
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(fondo!=null){
            g.drawImage(fondo,0,0,getWidth(),getHeight(),this);
        }
    }
}