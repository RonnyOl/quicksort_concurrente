package test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

import dato.QuickSort;
import dato.QuickSortMultithreaded;
 
public class Test  {

    public static void main(String args[])
    {
    	//Definimos los valores límites del random para cargar cada posición del vector
    	
    	int max = 10000;
    	int min = -10000;
       	
    	//Se define el tamaño
       
    	int n = 8000000;
        int[] arr = new int[n];    	//Cargamos el tamaño del vector
       
        // Se define la entidad random
		
        Random random = new Random();
        
		for(int i = 0; i<n;i++) {
			// Se genera el random
			arr[i] = random.nextInt(max-min+1)+min;
		}
		
		// Se crea la copia que irá para el secuencial
		int arrayCopiaQuick[] = Arrays.copyOf(arr, arr.length);
       
		// Forkjoin Se encargara de gestionar los hilos necesarios para ejecutar las tareas.
        // Administrando la cantidad de hilos basandose en los recursos del sistema
		// Está optimizado para dividir las tareas grandes en subtareas más pequeñas.
		ForkJoinPool pool = new ForkJoinPool(); 
        
		//Se define los tiempos para realizar los calculos
		double tiempoInicial, tiempoFinal;
        tiempoInicial = System.nanoTime(); // Toma el tiempo donde arrancó
        
        //Inicia la tarea dentro del fork, iniciando una nueva instancia del QuickSortMultithreaded
        pool.invoke( new QuickSortMultithreaded(0, n - 1, arr));
      
        tiempoFinal = System.nanoTime()- tiempoInicial;  //Se toma el tiempo final       
        System.out.println("El Algoritmo QuickSort de forma concurrente tardo: "+tiempoFinal/1000); // Se imprime cuanto tardó.
        
        /*********SECUENCIAL************/
        tiempoInicial = System.nanoTime(); // Se vuelve a guardar el tiempo inicial
     
        // Inicia el QuickSort secuencial, dandole el array previamente copiado, y definiendo el inicio 0 y el final n-1
    	QuickSort.quickSort(arrayCopiaQuick, 0, n-1); 
    	
    	tiempoFinal = System.nanoTime()- tiempoInicial;	 //Se toma el tiempo final
    	System.out.println("El algoritmo QuickSort de forma SECUENCIAL tardo "+tiempoFinal/1000); // Se imprime cuanto tardó.
      
    }
}