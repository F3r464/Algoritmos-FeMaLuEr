package Practica11.Interfaz;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {

        setTitle("FeMaLuEr Hash 00");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(20, 20, 20));

        JLabel titulo = new JLabel("HASHVERSE");
        titulo.setBounds(280, 60, 300, 50);
        titulo.setFont(new Font("Consolas", Font.BOLD, 40));
        titulo.setForeground(Color.GREEN);

        JButton iniciar = crearBoton("INICIAR");
        iniciar.setBounds(280, 150, 250, 40);

        JButton creditos = crearBoton("CRÉDITOS");
        creditos.setBounds(280, 210, 250, 40);

        JButton salir = crearBoton("SALIR");
        salir.setBounds(280, 270, 250, 40);

        iniciar.addActionListener(e -> {
            dispose();
            new Practica11.Interfaz.HashGamePanel();
        });

        creditos.addActionListener(e -> {
            new Creditos();
        });

        salir.addActionListener(e -> {
            System.exit(0);
        });

        panel.add(titulo);
        panel.add(iniciar);
        panel.add(creditos);
        panel.add(salir);

        add(panel);
        setVisible(true);
    }

    private JButton crearBoton(String texto) {

        JButton b = new JButton(texto);
        b.setBackground(new Color(50, 50, 50));
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Consolas", Font.BOLD, 18));
        b.setFocusPainted(false);

        return b;
    }
}