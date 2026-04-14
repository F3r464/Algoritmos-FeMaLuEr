// Método que calcula la moda de un arreglo
public static int moda1(int array[]) {
    int first = 0;                  // índice inicial del arreglo
    int end = array.length - 1;     // índice final del arreglo
    // Caso base:solo hay un elemento
    if (first == end)
        return array[first];
    int moda = array[first];        // El primero es la moda
    int maxfrec = frecuencia(array, first, end, moda); // frecuencia del primero
    // recorremos el arreglo desde la segunda posición
    for (int i = first + 1; i <= end; i++) {
        // calculamos la frecuencia del elemento actual
        int frec = frecuencia(array, i, end, array[i]); 
// si encontramos mayor frecuencia, actualizamos
        if (frec > maxfrec) {
            maxfrec = frec;
            moda = array[i];
        }
    }
    return moda; // devolvemos la moda
}
// La función Frecuencia es la que calcula el número de veces que se repite un elemento dado:

// Cuenta cuántas veces aparece un elemento en el arreglo
private static int frecuencia(int[] array, int first, int end, int ele) {
    if (first > end)
        return 0; // caso inválido
    int suma = 0; // contador
    // recorremos el arreglo
    for (int i = first; i <= end; i++) {
        if (array[i] == ele) // si encontramos el elemento
            suma++;          // aumentamos contador
    }
    return suma; // retornamos cantidad de repeticiones
}
