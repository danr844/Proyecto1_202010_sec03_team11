package test.data_structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.Cola;
import model.data_structures.Multa;
import model.data_structures.Node;
import model.data_structures.Pila;
import model.logic.Modelo;

public class TestPila {


	private Modelo modelo;
	private Pila<Multa>pila;
	private Multa nueva;
	private Multa nueva2;

	@Before
	public void setUp1() {

		modelo= new Modelo();
		nueva = new Multa(1234, "hola1", "hola2", "hola3", "hola4", "hola5", "hola", "hola7");
		nueva2 = new Multa(0000, "0000", "0009", "0008", "0007", "0006", "0005", "0004");

		pila = modelo.darPila();
		pila.push(nueva);
		pila.push(nueva2);

	}


	@Test
	public void testModelo() {
		setUp1();
		assertTrue(modelo!=null);
	}

	@Test
	public void testDarTamano() {
		// TODO
		setUp1();
		assertEquals("No tiene el tamaño esperado", 2, modelo.darTamanoPila());


	}

	@Test
	public void testAgregar() 
	{
		// TODO Completar la prueba
		setUp1();
		assertNotNull("El primer objeto no deberia ser null", modelo.darPrimerElementoPila());

		assertEquals("No tiene el ID esperado", 0000, modelo.eliminarEnPila().darID());

	}

	@Test
	public void testEliminar()
	{
		setUp1();
		// TODO Completar la prueba
		pila.pop();
		pila.pop();
		assertNull("El objeto no deberia ser distinto de null",modelo.darPrimerElementoPila());

	}

}




