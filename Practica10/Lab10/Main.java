package Lab10;

import actividades.Actividad1;
import ejercicios.Ejercicio2JGraphT;
import ejercicios.Ejercicio4Propiedades;

/**
 * Clase Ejecutable Principal.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=====================================================");
        System.out.println("      SISTEMA DE GESTIÓN DE GRAFOS - LAB 09          ");
        System.out.println("=====================================================\n");

        // 1. Ejecución de la Guía Guiada (Actividad 1)
        Actividad1.grafoNoDirigido();
        System.out.println("\n-----------------------------------------------------\n");
        Actividad1.grafoDirigido();
        System.out.println("\n-----------------------------------------------------\n");
        Actividad1.grafoPonderado();
        System.out.println("\n-----------------------------------------------------\n");

        // 2. Red de Ciudades usando JGraphT (Ejercicio 2 e Interfaz Ejercicio 3)
        Ejercicio2JGraphT.ejecutar();
        System.out.println("\n-----------------------------------------------------\n");

        // 3. Análisis de Propiedades Complejas (Ejercicio 4)
        Ejercicio4Propiedades.ejecutar();

        System.out.println("\n=====================================================");
        System.out.println("          PROCESO FINALIZADO SIN ERRORES             ");
        System.out.println("=====================================================");
    }
}