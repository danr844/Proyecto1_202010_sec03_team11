package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;

	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(i);
		}
	}

	@Test
	public void testArregloDinamico() {

		setUp2();
		assertEquals("El arreglo no tiene el tamaño espera", 200  ,arreglo.darTamano());
		arreglo.eliminar(0);
		assertEquals("El arreglo no tiene el tamaño espera",199, arreglo.darTamano());
		// TODO
	}

	@Test
	public void testDarElemento() 
	{
		setUp2();
		// TODO
		assertEquals("No es el elemento esperado",1 , arreglo.darElemento(1));
		assertEquals("No es el elemento esperado",50 , arreglo.darElemento(50));
		assertEquals("No es el elemento esperado",199 , arreglo.darElemento(199));
	}

}
