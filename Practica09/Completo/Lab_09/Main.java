package Lab_09;

import actividades.Actividad3;
import ejercicios.Ejercicio01;
import ejercicios.Ejercicio02;
import ejercicios.Ejercicio03;
import ejercicios.Ejercicio04;
import exceptions.TreeException;

public class Main {
    public static void main(String[] args) {
        try {
            // ─── Orquestador Secuencial Modular ───────────────────────────────
            
            // Llama y ejecuta las actividades base del Árbol B
            Actividad3.ejecutar();
            System.out.println("\n\n");
            
            // Llama a los laboratorios de control algorítmico individualizados
            Ejercicio01.ejecutar();
            System.out.println("\n\n");
            
            Ejercicio02.ejecutar();
            System.out.println("\n\n");
            
            Ejercicio03.ejecutar();
            System.out.println("\n\n");
            
            // Despliega la aplicación de la Biblioteca Digital
            Ejercicio04.ejecutar();
            
        } catch (TreeException e) {
            System.err.println("❌ Error crítico interceptado en Main: " + e.getMessage());
        }
    }
}