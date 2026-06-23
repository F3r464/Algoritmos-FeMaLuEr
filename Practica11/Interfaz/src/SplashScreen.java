package Practica11.Interfaz.src;

import javax.swing.*;

import java.awt.*;

public class SplashScreen extends JFrame {

    private JProgressBar barra;
    private JLabel porcentaje;

    public SplashScreen() {

        setTitle("HashVerse");
        setSize(700,400);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(20,20,20));
        panel.setLayout(null);

        JLabel titulo = new JLabel("HASHVERSE");
        titulo.setBounds(200,50,300,50);
        titulo.setFont(new Font("Consolas",Font.BOLD,36));
        titulo.setForeground(Color.GREEN);

        JLabel subtitulo = new JLabel("Initializing Blockchain...");
        subtitulo.setBounds(230,120,250,30);
        subtitulo.setForeground(Color.WHITE);

        barra = new JProgressBar();
        barra.setBounds(100,250,500,25);
        barra.setMaximum(100);

        porcentaje = new JLabel("0%");
        porcentaje.setBounds(330,280,100,30);
        porcentaje.setForeground(Color.WHITE);

        panel.add(titulo);
        panel.add(subtitulo);
        panel.add(barra);
        panel.add(porcentaje);

        add(panel);

        setVisible(true);

        cargar();

    }

    private void cargar(){

        Thread hilo = new Thread(() ->{

            try{

                for(int i=0;i<=100;i++){

                    barra.setValue(i);
                    porcentaje.setText(i+"%");

                    Thread.sleep(30);

                }

            }catch(Exception e){}

            dispose();

            GameManager.getInstance().startMenu();
        });

        hilo.start();

    }

}