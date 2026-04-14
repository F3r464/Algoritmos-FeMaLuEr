package EJER04;

public class Laberinto {

    public static boolean resolver(int[][] lab, int x, int y, int[][] sol) {
        int n = lab.length;

        // Caso Base: llegamos al destino
        if (x == n - 1 && y == n - 1 && lab[x][y] == 0) {
            sol[x][y] = 1;
            return true;
        }

        // Validar límites, paredes y celdas ya visitadas
        if (x >= 0 && x < n && y >= 0 && y < n && lab[x][y] == 0 && sol[x][y] == 0) {
            
            sol[x][y] = 1; // Marcar paso

            // Intentar las 4 direcciones
            if (resolver(lab, x + 1, y, sol)) return true; // Abajo
            if (resolver(lab, x, y + 1, sol)) return true; // Derecha
            if (resolver(lab, x - 1, y, sol)) return true; // Arriba
            if (resolver(lab, x, y - 1, sol)) return true; // Izquierda

            sol[x][y] = 0; // Backtracking
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] laberinto = {
            {0, 0, 1},
            {1, 0, 1},
            {1, 0, 0}
        };

        int n = laberinto.length;
        int[][] solucion = new int[n][n];

        boolean exito = resolver(laberinto, 0, 0, solucion);
        System.out.println("Salida: " + exito);
    }
}