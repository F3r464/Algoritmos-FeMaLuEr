package Practica11.Interfaz.src;

import javax.swing.*;
import java.awt.*;

public class Creditos extends JFrame {

    public Creditos() {

        setTitle("FeMaLuEr Credt");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(10, 10, 10));

        JLabel titulo = new JLabel("FeMaLuEr");
        titulo.setBounds(200, 40, 300, 40);
        titulo.setFont(new Font("Consolas", Font.BOLD, 32));
        titulo.setForeground(Color.GREEN);

        JLabel i1 = crearLabel("Alvarez Abarca Erica Lenny");
        i1.setBounds(120, 120, 400, 25);

        JLabel i2 = crearLabel("Alarcon Cervantes Fernando Santiago");
        i2.setBounds(120, 160, 400, 25);

        JLabel i3 = crearLabel("Kana Zambrano Luz Clarita");
        i3.setBounds(120, 200, 400, 25);

        JLabel i4 = crearLabel("Paredes Romero Mateo Alejandro");
        i4.setBounds(120, 240, 400, 25);

        panel.add(titulo);

        panel.add(i1);
        panel.add(i2);
        panel.add(i3);
        panel.add(i4);

        add(panel);
        setVisible(true);
    }

    private JLabel crearLabel(String texto) {
        JLabel l = new JLabel(texto);
        l.setForeground(Color.WHITE);
        l.setFont(new Font("Consolas", Font.PLAIN, 16));
        return l;
    }
}