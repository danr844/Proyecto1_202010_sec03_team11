package model.data_structures;

import java.util.Comparator;

public class Ordenamientos {

	public   static int partition(Comparable[] a, int lo, int hi, Comparator comparador)
	{  // Partition into a[lo..i-1], a[i], a[i+1..hi].    
		int i = lo, j = hi+1;            
		// left and right scan indices   
		Comparable v = a[lo];            
		// partitioning item   
		while (true)   {  
			// Scan right, scan left, check for scan complete, and exchange.       
			while (less(a[++i], v, comparador)) 
				if (i == hi) break;      
			while (less(v, a[--j], comparador)) 
				if (j == lo) break;      
			if (i >= j) break;      
			exch(a, i, j);   
		}   
		exch(a, lo, j);       
		// Put v = a[j] into position    
		return j;             
		// with a[lo..j-1] <= a[j] <= a[j+1..hi]. }
	}

	public static boolean IsNull(Object x) {
		if (x == null) 
			return true;  
		else 
			return false;
	}

	public  static boolean   less(Comparable v, Comparable w, Comparator comparador)  
	{  
		return comparador.compare(v, w)<0;
	}   
	public  static void exch(Comparable[] a, int i, int j)   
	{  
		Comparable t = a[i]; 
		a[i] = a[j]; 
		a[j] = t;  
	} 
	public  static void shuffle(Comparable[] a) {
		if(!IsNull(a)){
			int n = a.length;
			for (int i = 0; i < n; i++) {
				int r = (int)   Math.random()*n;     // between i and n-1
				Comparable temp = a[i];
				a[i] = a[r];
				a[r] = temp;
			}
		}
	}
	public static void ShellSort(Comparable[] a, Comparator comparador)  
	{  
		// Sort a[] into increasing order.      
		int N = a.length;      
		int h = 1;      
		while (h < N/3) 
			h = 3*h + 1; 
		// 1, 4, 13, 40, 121, 364, 1093, ...      
		while (h >= 1)      
		{  // h-sort the array.         
			for (int i = h; i < N; i++)         
			{  // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .            
				for (int j = i; j >= h && less(a[j], a[j-h], comparador); j -= h)               
					exch(a, j, j-h);         
			}
			h = h/3;      
		}   
	}   // See page 245 
	public static void Quicksort(Comparable[] a, Comparator comparador)  
	{     
		shuffle(a);       
		// Eliminate dependence on input.   
		sortQuick(a, 0, a.length - 1, comparador);   
	}   
	private static void sortQuick(Comparable[] a, int lo, int hi, Comparator comparador)   
	{      
		if (hi <= lo) 
			return;      
		int j = partition(a, lo, hi,comparador);  
		// Partition (see page 291).      
		sortQuick(a, lo, j-1, comparador);              
		// Sort left part a[lo .. j-1].      
		sortQuick(a, j+1, hi, comparador);              
		// Sort right part a[j+1 .. hi].   

	}
	public static void sortMerge(Comparable[] a, int lo, int hi, Comparator comparador)   
	{      
		// Sort a[lo..hi].
		 if (hi <= lo) return;
		 int mid = lo + (hi - lo)/2;
		 sortQuick(a, lo, mid, comparador); // Sort left half.
		 sortQuick(a, mid+1, hi, comparador); // Sort right half.
		 merge(a, lo, mid, hi, comparador); // Merge results   

	}
	

	private static  void merge(Comparable[] a, int lo, int mid, int hi, Comparator comparador){
		// Fusionar a[lo..mid] con a[mid+1..hi].
		Comparable[] aux;
		aux = new Comparable[a.length];
		int i = lo, j = mid+1;
		//Copiar a[lo..hi] en aux[lo..hi].
		for (int k = lo; k <= hi; k++)
			aux[k] = a[k];
		// Fusionar de regreso a a[lo..hi].
		for (int k = lo; k <= hi; k++)
			if (i > mid) a[k] = aux[j++]; //Agotado izquierdo
			else if (j > hi ) a[k] = aux[i++]; //Agotado derecho
			else if (less(aux[j], aux[i], comparador)) a[k] = aux[j++]; //Insertar de derecho
			else
				a[k] = aux[i++]; //Insertar de izquierdo
	}


}




