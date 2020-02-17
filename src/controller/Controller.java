package controller;

import java.util.Scanner;

import com.sun.glass.events.ViewEvent;

import model.data_structures.Cola;
import model.data_structures.Multa;
import model.data_structures.Node;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;

		Integer respuesta = 0;

		while( !fin ){
			view.printMenu();
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				view.printMessage("------------------------------------------------------------------------\n Se esta cargando la informacion \n------------------------------------------------------------------------");
					modelo.cargarInfo();
				if(modelo.darPrimerElementoCola()!=null&&modelo.darPrimerElementoPila()!=null)
				{
					Node<Multa> encontradoPila = modelo.darPrimerElementoPila();
					Node<Multa> encontradoCola = modelo.darPrimerElementoCola();

					view.printMessage("Primer comparendo en la Cola:"+ encontradoCola.darTvalor().darID()+","+encontradoCola.darTvalor().darFecha()+","+ encontradoCola.darTvalor().darClaseVehiculo()+","+encontradoCola.darTvalor().darTipoServicio()+","+encontradoCola.darTvalor().darLocalidad()+"\n---------------------------");
					view.printMessage("Primer comparendo en la Pila: "+ encontradoPila.darTvalor().darID()+","+encontradoPila.darTvalor().darFecha()+","+ encontradoPila.darTvalor().darClaseVehiculo()+","+encontradoPila.darTvalor().darTipoServicio()+","+encontradoPila.darTvalor().darLocalidad()+"\n---------------------------");
					view.printMessage("Total de comparendos leidos: "+modelo.darTamanoPila());
					view.printMessage("------------------------------------------------------------------------");

				}
				break;
			case 2: 
				Cola<Multa>cola1 = modelo.consultaInfraccion();
				Node<Multa> actual1 = cola1.darPrimerElemento();
				while(actual1!=null)
				{
					view.printMessage(actual1.darTvalor().darInfraccion()+ " "+actual1.darTvalor().darID()+ " "+ actual1.darTvalor().darFecha()+ " "+ actual1.darTvalor().darClaseVehiculo()+ " "+ actual1.darTvalor().darTipoServicio() + " "+ actual1.darTvalor().darLocalidad() +"\n---------------------------");
					actual1 = actual1.darSiguiente();
				}
				
				
				
				
				
				break;
			case 3:
				view.printMessage("------------------------------------------------------------------------\n Ingrese la infraccion buscada: \n------------------------------------------------------------------------");
				String idBuscada = lector.next();
				view.printMessage("------------------------------------------------------------------------\n Ingrese el numero de comparendos con la infraccion dada que desea conocer: \n------------------------------------------------------------------------");
				int numComparendos = lector.nextInt();
				Cola<Multa>cola = modelo.procesarElementosPila(idBuscada, numComparendos);
				Node<Multa> actual = cola.darPrimerElemento();
				while(actual!=null)
				{
					view.printMessage(actual.darTvalor().darInfraccion()+ " "+actual.darTvalor().darID()+ " "+ actual.darTvalor().darFecha()+ " "+ actual.darTvalor().darClaseVehiculo()+ " "+ actual.darTvalor().darTipoServicio() + " "+ actual.darTvalor().darLocalidad() +"\n---------------------------");
					actual = actual.darSiguiente();
				}

				//Node<Multa> encontrado=modelo.buscar(idBuscada);
				//view.printMessage(""+ encontrado.darTvalor().darID()+","+encontrado.darTvalor().darFecha()+","+ encontrado.darTvalor().darClaseVehiculo()+","+encontrado.darTvalor().darTipoServicio()+","+encontrado.darTvalor().darLocalidad()+"\n---------------------------");

				break;


			default: 
				view.printMessage("--------------------------------------------------------------- \n Opcion Invalida !! \n---------------------------------------------------------------");
				break;
			}
		}




	}	
}
