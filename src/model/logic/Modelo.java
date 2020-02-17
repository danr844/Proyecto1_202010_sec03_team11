package model.logic;



import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import model.data_structures.ArregloDinamico;
import model.data_structures.Cola;
import model.data_structures.IArregloDinamico;
import model.data_structures.Multa;
import model.data_structures.Pila;
import model.data_structures.Node;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private Node<Multa> primero;
	private int numeroNodos;
	private Cola<Multa> cola;
	private Pila<Multa> pila;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		primero = null;
		cola = new Cola<Multa>();
		pila=new Pila<Multa>();

	}



	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamanoCola()
	{
		return cola.dartamanoCola();
	}
	public int darTamanoPila()
	{
		return pila.darTamanoPila();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param <T>
	 * @param dato
	 */
	public <T> void agregarALaCola(Multa dato)
	{	
		cola.enqueue(dato);	
	}
	
	public <T> void agregarALaPila(Multa dato)
	{
		pila.push(dato);
	}
	
	public Multa eliminarEnCola()
	{
		return cola.dequeue();
	}
	
	public Multa eliminarEnPila()
	{
		return pila.pop();
	}
	
	public List<Double> cargarInfo(){
		List<Double> geo = new ArrayList<Double>();

		try {

			Gson gson = new Gson();
			
			String path = "./data/comparendos_dei_2018_small.geojson";
			JsonReader reader;

			List<String> lista = new ArrayList<String>();

			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonObject().get("features").getAsJsonArray();
			for(JsonElement e: ja) {
				int id = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				String fecha = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				String medio = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String Clasevehi= e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String tipoServicio = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String Infraccion =e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DescInfra=e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();
				String Localidad = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				
				Multa user = new Multa(id,fecha, medio, Clasevehi, tipoServicio, Infraccion, DescInfra, Localidad );
				agregarALaCola(user);
				agregarALaPila(user);
				if(e.getAsJsonObject().has("geometry") && !e.getAsJsonObject().get("geometry").isJsonNull()) {
					for(JsonElement geoElem: e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()) {
						geo.add(geoElem.getAsDouble());

					}
				}
			}

			System.out.println(Arrays.toString(lista.toArray()));


		} catch (IOException e) {
			e.printStackTrace();
		}
		return geo;
	}

	public Cola<Multa> consultaInfraccion()
	{
		 Cola<Multa> retorno= new Cola<Multa>();
		 Cola<Multa> deComparar= new Cola<Multa>();
		 String leyendo="";
		 int i=0;
		 while(i<numeroNodos)
		 {
			 if(deComparar.estavacia())
			 {
				 Multa primero=cola.dequeue();
				 deComparar.enqueue(primero);
				 leyendo=primero.darInfraccion();
			 }
			 else
			 {
				 Multa primero=cola.dequeue();
				 if(leyendo.equals(primero.darInfraccion()))
					 deComparar.enqueue(primero); 
				 
				 else
				 {
					 if(deComparar.dartamanoCola()>retorno.dartamanoCola())
						 retorno=deComparar;
					 
					 deComparar=new Cola<Multa>();
					 deComparar.enqueue(primero);
					 leyendo=primero.darInfraccion();
				 }
			 }
			 i++;
		 }
		 
		 return retorno;
	}
	
	private boolean estaEnLaLista(ArrayList<String> pLista, String pInfraccion)
	{
		boolean retorno=false;

		for(String e: pLista)
		{
			if(e.equals(pInfraccion))
				retorno=true;
		}


		return retorno;
	}	

	public Cola<Multa> procesarElementosPila(String pInfraccion, int numeroComparendos)
	{
		int k =0;
		Cola<Multa> respuesta = new Cola<Multa>();
		Multa actual = pila.darPrimerElemento().darTvalor();

		while(actual!=null&&k<numeroComparendos)
		{
			actual = pila.pop();
			if(actual.darInfraccion().equals(pInfraccion)){
				respuesta.enqueue(actual);
				k++;
			}
			
		}
		return respuesta;
	}
	public Node<Multa> darPrimerElementoCola(){
		return cola.darPrimerElemento();
	}
	public Node<Multa> darPrimerElementoPila(){
		return pila.darPrimerElemento();
	}
	public Cola<Multa> darCola(){
		return cola;
	}
	public Pila<Multa> darPila(){
		return pila;
	}

	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
//	public Node<Multa> buscar(int dato)
//	{
//		Node <Multa> actual=primero;
//		while(actual!=null)
//		{
//		if(actual.darTvalor().darID()==dato)
//			return actual;
//		else actual=actual.darSiguiente();
//		
//		}
//		return null;
//	}

	/**
	 * Requerimiento eliminar dato
	 * @param object Dato a eliminar
	 * @return dato eliminado
	 */

//	public IArregloDinamico<Integer> dardatos(){
//		return datos;
//	}
}
