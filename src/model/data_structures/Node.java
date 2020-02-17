package model.data_structures;

public class Node <T> {


	private Node<T> siguiente;
	
	private T valor;

	public Node ()
	{
		valor = null;
		siguiente=  null;	
	}
	public Node<T> darSiguiente()
	{
		return siguiente;
	}
	public void cambiarSiguiente(Node<T> pSiguiente)
	{
		siguiente = pSiguiente;
	}
	public void cambiarDato(T dato){
		valor= dato;
	}
	public T darTvalor(){
		return valor;
	}
}


