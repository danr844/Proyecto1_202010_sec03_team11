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

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.Multa;
import model.data_structures.Node;


/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<Integer> datos;
	private Node<Multa> primero;
	private int numeroNodos;
	private Node<Multa> ultimo;
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		primero = null;
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico<Integer>(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * @param <T>
	 * @param dato
	 */
	public <T> void agregar(Multa dato)
	{	
		if(primero== null){
			primero  = new Node <Multa>();
			primero.cambiarDato(dato);
			numeroNodos++;
			ultimo = primero;
		}
		else{
			Node<Multa> nodo= new Node<Multa>();
			nodo.cambiarDato(dato);
			ultimo.cambiarSiguiente(nodo);
			ultimo = nodo;
			numeroNodos++;
		
		}
			
	}
	public int darNumeroNodos(){
		return numeroNodos;
	}
	
	public Node<Multa> darUltimoNodo(){
		return ultimo;
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
				agregar(user);
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
	public Node<Multa> darPrimero(){
		return primero;
	}
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */
	public Node<Multa> buscar(int dato)
	{
		Node <Multa> actual=primero;
		while(actual!=null)
		{
		if(actual.darTvalor().darID()==dato)
			return actual;
		else actual=actual.darSiguiente();
		
		}
		return null;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param object Dato a eliminar
	 * @return dato eliminado
	 */
	public Integer eliminar(Integer object)
	{
		return  datos.eliminar(object);
	}
	public IArregloDinamico<Integer> dardatos(){
		return datos;
	}

}
