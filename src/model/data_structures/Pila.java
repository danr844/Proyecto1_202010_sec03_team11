package model.data_structures;

public class Pila<T> implements IPila<T>
{
	private int tamano;
	private boolean vacia;
	private Node<T> primerElemento;
	private Node<T> ultimoElemento;
	
	public Pila()
	{
		tamano=0;
		vacia=true;
		primerElemento=null;
		ultimoElemento=null;
	}
	
	public int darTamanoPila()
	{
		return tamano;
	}
	public boolean estaVacia()
	{
		return vacia;
	}
	
	
	public void push(T pElemento)
	{
		Node<T> nuevo= new Node<>();
		nuevo.cambiarDato(pElemento);
		if (vacia==true)
		{
			primerElemento=nuevo;
			ultimoElemento=nuevo;
			tamano++;
			vacia=false;
		}
		else
		{
			nuevo.cambiarSiguiente(primerElemento);
			primerElemento=nuevo;
			tamano++;
		}
	}
	
	public T pop()
	{
		Node<T> retorno=null;
		if(vacia==false)
		{
			if(ultimoElemento==primerElemento)
			{
				retorno=primerElemento;
				vacia=true;
				primerElemento=null;
				ultimoElemento=null;
				tamano--;
			}
			else
			{
				retorno=primerElemento;
				primerElemento=primerElemento.darSiguiente();
				tamano--;
			}
		}
		
		return retorno.darTvalor();
	}
	public Node<T> darPrimerElemento(){
		return primerElemento;
	}
	public Node<T> darUltimoElemento(){
		return primerElemento;
	}
	
	
}
