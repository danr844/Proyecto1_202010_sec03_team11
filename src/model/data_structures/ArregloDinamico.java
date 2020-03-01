package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable<T>> implements IArregloDinamico<T>  {
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T[] elementos ;

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos =   (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
		int x=0;
		System.out.println(x++);
		System.out.println(++x);
		System.out.println(x--);
		System.out.println(--x);

		
	}

	public void agregar( T dato )
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax =  tamanoMax*2;
			Object [ ] copia = elementos;
			elementos = (T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = (T) copia[i];
			} 
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct] = (T) dato;
		tamanoAct++;
	}
	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}
	public boolean estaVacio()
	{
		return true? tamanoAct==0:false;
	}

	public  T darElemento(int i) {
		// TODO implementar
		return elementos[i];
	}
	public T[] darElementos(){
		return elementos;
	}

	public T buscar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		boolean encontrado = false;
		for(int i = 0; i<elementos.length && !encontrado; i++)
		{
			if(elementos[i].compareTo((T) dato)==0)
			{
				encontrado = true;
				return elementos[i];
			}
		}
		return null;
	}
	public void exch( int i, int j)  
	{  
		T t = elementos[i];
		elementos[i] = elementos[j]; 
		elementos[j] = t; 
	}
	public void cambiarElementoEnPosicion(int i, T k){
		elementos[i] = k;
	}
	public T eliminar(T dato) {
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		T retorno=null;
		for(int i=0; i<elementos.length;i++)
		{	
			if(elementos[i].compareTo( dato)==0)
			{
				retorno=elementos[i];
			}
			else if(elementos[i].compareTo( dato)>0)
			{
				elementos[i-1]=elementos[i];
			}
		}
		elementos[tamanoAct-1]=null;
		tamanoAct --;
		return retorno;
	}


}
