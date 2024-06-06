package dato;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
public class QuickSortMultithreaded extends RecursiveTask<Integer> {
	// Definimos los atributos que se usará para hacer el QuickSort
	int start, end;
    int[] arr;
    // La función para implementar el metodo de QuickSort
    public QuickSortMultithreaded(int start,int end, int[] arr)
    {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }
    /**
     Debido a que hay varias formas de obtener el pivote
     voy a encontrar al pivote de forma random
    
     */
    private int partition(int start, int end,int[] arr)
    {
    
        int i = start, j = end; // Definimos i y j como el inicio y el fin
 
        // Definimos el pivote de forma random
        int pivoted = new Random().nextInt(j - i) + i;
 
     // Intercambiamos el pivote con el último elemento del array
        int t = arr[j];
        arr[j] = arr[pivoted];
        arr[pivoted] = t;
        j--;
 
        // Comenzamos la partición
        while (i <= j) {
        	
            // Si el elemento en i es menor o igual que el pivote, incrementamos i
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }
            // Si el elemento en j es mayor o igual que el pivote, decrementamos j
            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }
            // Si no se cumplen las condiciones anteriores, intercambiamos los elementos en i y j
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            // Decrementamos j e incrementamos i
            j--;
            i++;
        }
 
        // Intercambiamos el pivote con su posición correcta
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }
 
    @Override
    protected Integer compute()
    {
    	 // Caso base: si el inicio es mayor o igual al final, no hacemos nada
        if (start >= end)
            return null;
 
        // Encontramos la posición del pivote
        int p = partition(start, end, arr);
 
        // Dividimos el array en dos subproblemas
        QuickSortMultithreaded left = new QuickSortMultithreaded(start, p - 1,arr);
 
        QuickSortMultithreaded right = new QuickSortMultithreaded(p + 1,end,arr);
        // Ejecutamos las sub-tareas de forma paralela
        invokeAll(left,right);
        // No necesitamos devolver nada
        return null;
    }
}
