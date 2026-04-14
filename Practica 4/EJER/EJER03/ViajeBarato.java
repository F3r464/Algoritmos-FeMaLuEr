public class ViajeBarato {

    public static void main(String[] args) {

        // Matriz de costos directos 
        int[][] T = {
                {0, 3, 8, 15},
                {0, 0, 2, 6},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        int n = T.length;

        // Matriz donde guardaremos los costos mínimos
        int[][] C = new int[n][n];

        // Inicializamos la diagonal en 0 (ir al mismo punto cuesta 0)
        for (int i = 0; i < n; i++) {
            C[i][i] = 0;
        }

        // Recorremos por diagonales (programación dinámica)
        for (int d = 1; d < n; d++) { // d = distancia entre i y j

            for (int i = 0; i < n - d; i++) {

                int j = i + d; // destino

                // Inicialmente el mejor costo es el directo
                C[i][j] = T[i][j];

                // Probamos pasar por un punto intermedio k
                for (int k = i + 1; k < j; k++) {

                    
                    int costo = C[i][k] + C[k][j];

                    // si encontramos un viaje más barato, se actualiza
                    if (costo < C[i][j]) {
                        C[i][j] = costo;
                    }
                }
            }
        }
        System.out.println("Costo mínimo de 0 a " + (n - 1) + " es: " + C[0][n - 1]);
    }
}
