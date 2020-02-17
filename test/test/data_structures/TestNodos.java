package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Multa;
import model.data_structures.Node;

public class TestNodos {

	private static int TAMANO=100;
	private Multa multa;
	private Node<Multa> nodo;

	@Before
	public void setUp1() {

		nodo = new Node<Multa>();
		multa = new Multa(0, "", "", "", "", "", "","");
		nodo.cambiarDato(multa);
	}

	public void setUp2() {
		nodo = new Node<Multa>();
		Node<Multa> nodo2 = new Node<Multa>();
		multa = new Multa(0, "", "", "", "", "", "","");
		Multa nueva2 = new Multa(1, "we", "na", "s", "xd", "qu", "e","massss");
		nodo.cambiarDato(multa);
		nodo2.cambiarDato(nueva2);
		nodo.cambiarSiguiente(nodo2);


	}

	@Test
	public void testNodos() {

		setUp2();
		assertNotNull("El primer nodo no deberia ser null", nodo.darSiguiente());
		assertEquals("El primero nodo no tiene el valor esperado",0, nodo.darTvalor().darID());
		assertEquals("El primero nodo no tiene el valor esperado",1, nodo.darSiguiente().darTvalor().darID());

		// TODO
	}

	@Test
	public void testDarElemento() 
	{
		setUp2();
		// TODO
		assertEquals("El primero nodo no tiene el valor esperado de clase","", nodo.darTvalor().darClaseVehiculo());
		assertEquals("El segundo nodo no tiene el valor esperado de fecha","we", nodo.darSiguiente().darTvalor().darFecha());
		assertEquals("El primero nodo no tiene el valor esperado de id",0, nodo.darTvalor().darID());
		assertEquals("El primero nodo no es el esperado",multa, nodo.darTvalor());


	}

}
