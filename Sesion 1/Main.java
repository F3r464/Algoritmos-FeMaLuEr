import javax.swing.*;
public class Main{
    public static void main(String[] args){
        JFrame ventana = new JFrame("CaLuFer Super Proyacto");
        ventana.setSize(800,600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setContentPane(new Pantalla1(ventana));
        ventana.setVisible(true);
    }
}