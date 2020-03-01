package test.logic;

import static org.junit.Assert.*;

import model.data_structures.ArregloDinamico;
import model.data_structures.Comparendo;
import model.data_structures.Node;
import model.logic.Modelo;

import org.junit.Before;
import org.junit.Test;

public class TestModelo {
	
	private Modelo modelo;
	private Comparendo nueva;
	private Comparendo nueva2;


	
	@Before
	public void setUp1() {
		modelo= new Modelo(15);
		nueva = new Comparendo(1234, "hola1", "hola2", "hola3", "hola4", "hola5", "hola", "hola7");
		nueva2 = new Comparendo(0000, "0000", "0009", "0008", "0007", "0006", "0005", "0004");
		modelo.agregarArregloDinamico(nueva);
		modelo.agregarArregloDinamico(nueva2);
		
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
		assertEquals("No tiene el tamaño esperado", 2, modelo.darTamano());
		
	}

	@Test
	public void testAgregar() 
	{
			// TODO Completar la prueba
		setUp1();
		assertEquals("No tiene el tamaño esperado", 2, modelo.darTamano());
		nueva = new Comparendo(1, "hola1", "hola2", "hola3", "hola4", "hola5", "hola", "hola7");
		modelo.agregarArregloDinamico(nueva);
		assertEquals("No tiene el tamaño esperado", 3, modelo.darTamano());

			
	}

	@Test
	public void testBuscar()
	{
		setUp1();
		// TODO Completar la prueba
		assertNotNull("El objeto no deberia ser null1", modelo.buscar(1234));
		assertNotNull("El objeto no deberia ser null", modelo.buscar(0000));
		assertNull("El objeto deberia ser distinto de null",modelo.buscar(1));
	}
	@Test
	
	public void testCargarInfo() {
		setUp1();
		assertNotNull("la informacion no fue cargada", modelo.cargarInfo());
	}

}
