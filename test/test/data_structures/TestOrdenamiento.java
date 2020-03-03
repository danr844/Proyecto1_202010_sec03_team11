package test.data_structures;
import model.data_structures.ArregloDinamico;
import model.data_structures.Ordenamientos;
import model.logic.Modelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestOrdenamiento 
{

	

		private ArregloDinamico<Integer> arreglo;
		private static int TAMANO=100;
		private Ordenamientos ordenar;
		private Modelo modelo;

		@Before
		public void setUp1() 
		{
			arreglo= new ArregloDinamico<Integer>(10);
			arreglo.agregar(1);
			arreglo.agregar(6);
			arreglo.agregar(7);
			arreglo.agregar(2);
			arreglo.agregar(8);
			arreglo.agregar(4);
			arreglo.agregar(0);
			arreglo.agregar(3);
			arreglo.agregar(9);
			arreglo.agregar(5);

			



		}
		@Before
		public void setUp2() {
			arreglo= new ArregloDinamico<Integer>(10);
			arreglo.agregar(0);
			arreglo.agregar(1);
			arreglo.agregar(2);
			arreglo.agregar(3);
			arreglo.agregar(4);
			arreglo.agregar(5);
			arreglo.agregar(6);
			arreglo.agregar(7);
			arreglo.agregar(8);
			arreglo.agregar(9);

		}
		@Before
		public void setU3() {
			arreglo= new ArregloDinamico<Integer>(10);
			arreglo.agregar(9);
			arreglo.agregar(8);
			arreglo.agregar(7);
			arreglo.agregar(6);
			arreglo.agregar(5);
			arreglo.agregar(4);
			arreglo.agregar(3);
			arreglo.agregar(2);
			arreglo.agregar(1);
			arreglo.agregar(0);

		}
		@Test
		public void testArregloDinamico() {

			setUp2();
			assertEquals("El arreglo no tiene el tamaño espera", 10  ,arreglo.darTamano());
			arreglo.eliminar(0);
			assertEquals("El arreglo no tiene el tamaño espera",9, arreglo.darTamano());
			// TODO
		}

		@Test
		public void testDarElemento() 
		{
			setUp1();
			// TODO
			assertEquals("No es el elemento esperado",6 , (int)arreglo.darElemento(1));
			assertEquals("No es el elemento esperado",4 , (int)arreglo.darElemento(5));
			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(9));
			setUp2();
			// TODO
			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
			setU3();
			// TODO
			assertEquals("No es el elemento esperado",8 , (int)arreglo.darElemento(1));
			assertEquals("No es el elemento esperado",4 , (int)arreglo.darElemento(5));
			assertEquals("No es el elemento esperado",0 , (int)arreglo.darElemento(9));
		}
//		@Test
//		public void testShellSort() 
//		{
//			setUp1();
//			// TODO
//			Ordenamientos.ShellSort(arreglo.darElementos(),modelo.darComparador("ID"));
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setUp2();
//			// TODO
//			Ordenamientos.ShellSort(arreglo.darElementos(),modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setU3();
//			// TODO
//			Ordenamientos.ShellSort(arreglo.darElementos(),modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			
//		}
//		@Test
//		public void testMergeSort() 
//		{
//			setUp1();
//			// TODO
//			Ordenamientos.sortMerge(arreglo, modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setUp2();
//			// TODO
//			Ordenamientos.sortMerge(arreglo.darElementos(), 0,arreglo.darTamano()-1);
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setU3();
//			// TODO
//			Ordenamientos.sortMerge(arreglo.darElementos(), 0,arreglo.darTamano()-1);
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
////		}
//		@Test
//		public void testDarQuickSort() 
//		{
//			setUp1();
//			// TODO
//			Ordenamientos.Quicksort(arreglo.darElementos(), modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setUp2();
//			// TODO
//			Ordenamientos.Quicksort(arreglo.darElementos(),modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//			setU3();
//			// TODO
//			Ordenamientos.Quicksort(arreglo.darElementos(),modelo.darComparador("ID"));
//
//			assertEquals("No es el elemento esperado",1 , (int)arreglo.darElemento(1));
//			assertEquals("No es el elemento esperado",5 , (int)arreglo.darElemento(5));
//			assertEquals("No es el elemento esperado",9 , (int)arreglo.darElemento(9));
//		}

	}



