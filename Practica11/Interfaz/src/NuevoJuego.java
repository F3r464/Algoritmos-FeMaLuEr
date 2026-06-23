package Practica11.Interfaz.src;

import javax.swing.*;
import java.awt.*;

public class NuevoJuego extends JFrame {

    public NuevoJuego() {

        setTitle("Nuevo Juego");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(25, 25, 25));
        panel.setLayout(null);

        JLabel titulo = new JLabel("Crear Nuevo Mundo");
        titulo.setBounds(180, 30, 350, 40);
        titulo.setFont(new Font("Consolas", Font.BOLD, 28));
        titulo.setForeground(Color.GREEN);

        JLabel jugador = new JLabel("Nombre del jugador");
        jugador.setBounds(80, 100, 180, 25);
        jugador.setForeground(Color.WHITE);

        JTextField txtJugador = new JTextField();
        txtJugador.setBounds(300, 100, 250, 30);

        JLabel crypto = new JLabel("Nombre de la Crypto");
        crypto.setBounds(80, 160, 180, 25);
        crypto.setForeground(Color.WHITE);

        JTextField txtCrypto = new JTextField();
        txtCrypto.setBounds(300, 160, 250, 30);

        JLabel simbolo = new JLabel("Símbolo");
        simbolo.setBounds(80, 220, 180, 25);
        simbolo.setForeground(Color.WHITE);

        JTextField txtSimbolo = new JTextField();
        txtSimbolo.setBounds(300, 220, 100, 30);

        JLabel dificultad = new JLabel("Dificultad");
        dificultad.setBounds(80, 280, 180, 25);
        dificultad.setForeground(Color.WHITE);

        JComboBox<String> combo = new JComboBox<>(new String[]{
                "Fácil", "Normal", "Difícil"
        });
        combo.setBounds(300, 280, 150, 30);

        JButton comenzar = new JButton("Crear Mundo");
        comenzar.setBounds(240, 370, 200, 40);

        comenzar.setBackground(new Color(0, 150, 0));
        comenzar.setForeground(Color.WHITE);

        comenzar.addActionListener(e -> {
            GameData.jugador = txtJugador.getText();
            GameData.crypto = txtCrypto.getText();
            GameData.simbolo = txtSimbolo.getText();
            GameData.dificultad = (String) combo.getSelectedItem();

            dispose();

            new Practica11.Interfaz.src.HashGamePanel();
        });

        panel.add(titulo);

        panel.add(jugador);
        panel.add(txtJugador);

        panel.add(crypto);
        panel.add(txtCrypto);

        panel.add(simbolo);
        panel.add(txtSimbolo);

        panel.add(dificultad);
        panel.add(combo);

        panel.add(comenzar);

        add(panel);
        setVisible(true);
    }
}