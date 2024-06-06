package dato;

public class QuickSort {
    // Método principal de Quicksort que ordena el array arr en el rango [low, high]
    public static void quickSort(int[] arr, int low, int high) {
        // si low es menor que high, se procede con la partición
        if (low < high) {
            // Particiona el array y obtiene el índice del pivote
            int pi = partition(arr, low, high);
            // Llama a quickSort para el subarray izquierda (antes del pivote)
            quickSort(arr, low, pi - 1);
            // Llama a quickSort para el subarray derecha (después del pivote)
            quickSort(arr, pi + 1, high);
        }
    }

    // Método para particionar el array y devolver el índice del pivote
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Selecciona el último elemento como pivote
        int i = low - 1; // Inicializa el índice de elementos más pequeños que el pivote

        // Itera sobre el array desde low hasta high-1
        for (int j = low; j < high; j++) {
            // Si el elemento actual es menor que el pivote, intercambia arr[i] y arr[j]
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Intercambia el pivote con el primer elemento mayor que el pivote
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        // Devuelve el índice del pivote
        return i + 1;
    }
}
