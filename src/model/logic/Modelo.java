package model.logic;



import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.Comparendo;
import model.data_structures.Node;
import model.data_structures.Ordenamientos; 

/**
 * Definicion del modelo del mundo
 *
 */
public class Modelo
{
	/**
	 * Atributos del modelo del mundo
	 */
	private ArregloDinamico<Comparendo> datos;
	private int numeroNodos;
	private Ordenamientos ordenar;



	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico<Comparendo>(capacidad);

	}
	public static  boolean   less(Comparendo a, Comparendo a2)  
	{  
		return Ordenamientos.less(a, a2); 
	}   


	public List<Double> cargarInfo() throws ParseException{
		List<Double> geo = new ArrayList<Double>();

		try {

			Gson gson = new Gson();

			String path = "./data/comparendos_dei_2018_small.geojson";
			JsonReader reader;

			List<String> lista = new ArrayList<String>();

			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonObject().get("features").getAsJsonArray();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			for(JsonElement e: ja) {
				int id = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();
				String fechaString = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();
				Date fecha = parser.parse(fechaString);
				String medio = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String Clasevehi= e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String tipoServicio = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String Infraccion =e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DescInfra=e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();
				String Localidad = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();


				Comparendo user = new Comparendo(id,fecha, medio, Clasevehi, tipoServicio, Infraccion, DescInfra, Localidad );
				datos.agregar(user);

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

	public Comparendo buscarPrimeroPorInfraccion(String pInfraccion)
	{
		int i=0;
		while(i<datos.darTamano())
		{
			Comparendo actual= datos.darElemento(i);
			if(actual.darInfraccion().equals(pInfraccion))
				return actual;
			
			i++;
		}
		return null;
	}
	public ArregloDinamico<Comparendo> comparendosConInfraccion(String pInfraccion)
	{
		ArregloDinamico<Comparendo> retorno= new ArregloDinamico<>(0);
		ordenarPorMergeSort(datos, 0, datos.darTamano()-1);
		int i=0;
		while(i<datos.darTamano())
		{
			Comparendo actual= datos.darElemento(i);
			if(actual.darInfraccion().equals(pInfraccion))
				retorno.agregar(actual);
			i++;
		}
		return retorno;
	}
	/**
	 * Requerimiento eliminar dato
	 * @param object Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminar(Comparendo object)
	{
		return  datos.eliminar(object);
	}
	public IArregloDinamico<Comparendo> dardatos(){
		return datos;
	}
	public void agregarArregloDinamico(Comparendo comparendo){
		datos.agregar(comparendo);
	}
	public void ordenarShellSort(ArregloDinamico<Comparendo> datos)
	{
		// Sort a[] into increasing order.   
		Ordenamientos.ShellSort(datos.darElementos());
	}
	public  void ordenarPorMergeSort(ArregloDinamico<Comparendo> a, int lo, int hi) 
	{  // Merge a[lo..mid] with a[mid+1..hi].
		Ordenamientos.sortMerge(a.darElementos(), lo, hi);
	}
	public void ordenarPorQuick(ArregloDinamico<Comparendo> datos)
	{
		Ordenamientos.Quicksort(datos.darElementos());
	}
	public ArregloDinamico<Comparendo> copiarComparendos(){
		ArregloDinamico<Comparendo> arreglonuevo = new ArregloDinamico<>(datos.darTamano());
		for(int i = 0; i<datos.darTamano(); i++)
		{
			arreglonuevo.agregar(datos.darElemento(i));
		}
		return arreglonuevo;
	}



	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datos.darTamano();
	}
	public ArregloDinamico<Comparendo> darArreglo(){
		return datos;
	}
	public Comparendo buscar(int datoID)
	{
		int i = 0;

		while(datos.darElemento(i)!=null)
		{
			Comparendo actual=datos.darElemento(i);
			if(actual.darID() == datoID )
				return actual;
			++i;

		}
		return null;
	}

	public Comparator<Comparendo> darComparador(String caracteristicaComparable){

		if(caracteristicaComparable.equals("ID"))
		{
			Comparator<Comparendo> ID = new Comparator<Comparendo>() {
				@Override
				public int compare(Comparendo o1, Comparendo o2) 
				{
					if(o1.darID()<o2.darID())return -1;
					else if (o1.darID()>o2.darID())
						return 1;
					return 0;	
				}
			};
			return ID;
		}
		else if(caracteristicaComparable.equals("Infraccion"))
		{

			Comparator<Comparendo> Infraccion = new Comparator<Comparendo>() 
			{
				@Override
				public int compare(Comparendo o1, Comparendo o2) 
				{
					return o1.darInfraccion().compareTo(o2.darInfraccion());
				}
			};
		
			return Infraccion;
		}
	
		else if(caracteristicaComparable.equals("Fecha")){

			Comparator<Comparendo> Fecha = new Comparator<Comparendo>() {
				@Override
				public int compare(Comparendo o1, Comparendo o2) {
					return o1.darFecha().compareTo(o2.darFecha());	
				}
			};
			return Fecha;
		}
		else 
			return null;


	}

	//	public int darNumeroNodos(){
	//		return numeroNodos;
	//	}

	//	public Node<Comparendo> darUltimoNodo(){
	//		return ultimo;
	//	}
	//	
	/**
	 * Requerimiento de agregar dato
	 * @param <T>
	 * @param dato
	 */
	//	public <T> void agregar(Comparendo dato)
	//	{	
	//		if(primero== null){
	//			primero  = new Node <Comparendo>();
	//			primero.cambiarDato(dato);
	//			numeroNodos++;
	//			ultimo = primero;
	//		}
	//		else{
	//			Node<Comparendo> nodo= new Node<Comparendo>();
	//			nodo.cambiarDato(dato);
	//			ultimo.cambiarSiguiente(nodo);
	//			ultimo = nodo;
	//			numeroNodos++;
	//		
	//		}
	//			
	//	}
	//	public Node<Comparendo> darPrimero(){
	//	return primero;
	//}
	/**
	 * Requerimiento buscar dato
	 * @param dato Dato a buscar
	 * @return dato encontrado
	 */

}
















