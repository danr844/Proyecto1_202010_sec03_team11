package model.logic;



import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
	private ArregloDinamico<Comparendo> datosOriginal;
	private ArregloDinamico<Comparendo> datosOrdenadoInfraccion;
	private ArregloDinamico<Comparendo> datosOrdenadoFecha;
	private ArregloDinamico<Comparendo> datosOrdenadoLocalidad;





	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datosOriginal = new ArregloDinamico<Comparendo>(capacidad);
		datosOrdenadoFecha = new ArregloDinamico<>(capacidad);
		datosOrdenadoLocalidad = new ArregloDinamico<>(capacidad);

	}
	public static  boolean   less(Comparendo a, Comparendo a2, Comparator comparador)  
	{  
		return Ordenamientos.less(a, a2,comparador); 
	}   


	public List<Double> cargarInfo() throws ParseException{
		List<Double> geo = new ArrayList<Double>();

		try {
////// tesing
			Gson gson = new Gson();

			String path = "./data/comparendos_dei_2018_small.geojson";
			JsonReader reader;

			List<String> lista = new ArrayList<String>();

			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray ja = elem.getAsJsonObject().get("features").getAsJsonArray();
			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			for(JsonElement e: ja) 
			{
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
				datosOriginal.agregar(user);
				datosOrdenadoFecha.agregar(user);
				datosOrdenadoInfraccion.agregar(user);
				datosOrdenadoLocalidad.agregar(user);

				if(e.getAsJsonObject().has("geometry") && !e.getAsJsonObject().get("geometry").isJsonNull()) {
					for(JsonElement geoElem: e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()) {
						geo.add(geoElem.getAsDouble());

					}
				}
			}
			System.out.println(Arrays.toString(lista.toArray()));
			Ordenamientos.sortMerge(datosOrdenadoFecha.darElementos(), 0, datosOrdenadoFecha.darTamano(), darComparador("Fecha"));
			Ordenamientos.sortMerge(datosOrdenadoInfraccion.darElementos(), 0, datosOrdenadoInfraccion.darTamano(), darComparador("Infraccion"));
			Ordenamientos.sortMerge(datosOrdenadoLocalidad.darElementos(), 0, datosOrdenadoLocalidad.darTamano(), darComparador("Localidad"));


		} catch (IOException e) {
			e.printStackTrace();
		}
		return geo;
	}

// test merge
	public ArregloDinamico<Comparendo> comparendosConInfraccion(String pInfraccion)
	{
		ArregloDinamico<Comparendo> retorno= new ArregloDinamico<>(0);
		int i=0;
		while(i<datosOriginal.darTamano())
		{
			Comparendo actual= datosOriginal.darElemento(i);
			if(actual.darInfraccion().equals(pInfraccion))
				retorno.agregar(actual);
			i++;
		}
		return retorno;
	}

	public int consultarNumeroComparendosPorInfraccionTipo(String pInfraccion, String pTipo)
	{
		int retorno=0;
		int i=0;
		while(i<datosOriginal.darTamano())
		{
			Comparendo actual= datosOriginal.darElemento(i);
			if(actual.darInfraccion()==pInfraccion && actual.darTipoServicio()==pTipo)
				retorno++;
		}
		return retorno;
	}


	public Comparendo darPrimerComparendoPorLocalidad(String pLocalidad){
		ArregloDinamico<Comparendo>lista= datosOrdenadoLocalidad;
		int inicio = 0;
		int fin = lista.darTamano() - 1;
		boolean encontre = false;
		Comparendo respuesta=null;
		while( inicio <= fin && !encontre )
		{
			int medio = ( inicio + fin ) / 2;
			if( lista.darElemento(medio).darLocalidad().compareTo(pLocalidad)==0 ){
				respuesta = lista.darElemento(medio);
				encontre = true;
			}
			else if( lista.darElemento(medio).darLocalidad().compareTo(pLocalidad)>0 )
				fin = medio - 1;
			else
				inicio = medio + 1;
		}
		return respuesta;
	}

	public Comparendo darPrimerComparendoPorInfraccion(String pInfraccion){
		ArregloDinamico<Comparendo>lista= datosOrdenadoInfraccion;
		int inicio = 0;
		int fin = lista.darTamano() - 1;
		boolean encontre = false;
		Comparendo respuesta=null;
		while( inicio <= fin && !encontre )
		{
			int medio = ( inicio + fin ) / 2;
			if( lista.darElemento(medio).darInfraccion().compareTo(pInfraccion)==0 ){
				respuesta = lista.darElemento(medio);
				encontre = true;
			}
			else if( lista.darElemento(medio).darInfraccion().compareTo(pInfraccion)>0 )
				fin = medio - 1;
			else
				inicio = medio + 1;
		}
		return respuesta;
	}





	public ArregloDinamico<Comparendo> darComparendosFechaHora(Date fechaHora){
		ArregloDinamico<Comparendo>lista = datosOrdenadoFecha;
		ArregloDinamico<Comparendo>res =new ArregloDinamico<>(lista.darTamano());

		boolean alto = false; 

		{
			int medio = ( inicio + fin ) / 2;
			if( lista.darElemento(medio).darFecha().compareTo(fechaHora)==0 )
			{
				res.agregar(lista.darElemento(medio)); 
				alto = true;
			}

		}
		
		Ordenamientos.sortMerge(res.darElementos(), 0, res.darTamano()-1, darComparador("InfraccionInver"));
		return res;
	}

	public ArrayList<ArregloDinamico<Comparendo>> darComparendosDosfechas(Date Fecha1, Date fecha2){
		ArregloDinamico<Comparendo> lista1 = darComparendosFechaHora(Fecha1);
		ArregloDinamico<Comparendo> lista2 = darComparendosFechaHora(fecha2);
		ArrayList<ArregloDinamico<Comparendo>> res = new ArrayList<>();
		res.add(lista1);
		res.add(lista2);
		return res;
	}

	/**
	 * Requerimiento eliminar dato
	 * @param object Dato a eliminar
	 * @return dato eliminado
	 */
	public Comparendo eliminar(Comparendo object)
	{
		return  datosOriginal.eliminar(object);
	}
	public IArregloDinamico<Comparendo> dardatos(){
		return datosOriginal;
	}
	public void agregarArregloDinamico(Comparendo comparendo){
		datosOriginal.agregar(comparendo);
	}
	public void ordenarPorShellSort(ArregloDinamico<Comparendo> datos, Comparator comparador)
	{
		// Sort a[] into increasing order.   
		Ordenamientos.ShellSort(datos.darElementos(), comparador);
	}
	public  void ordenarPorMergeSort(ArregloDinamico<Comparendo> a, int lo, int hi, Comparator comparador) 
	{  // Merge a[lo..mid] with a[mid+1..hi].
		Ordenamientos.sortMerge(a.darElementos(), lo, hi, comparador);
	}
	public void ordenarPorQuick(ArregloDinamico<Comparendo> datos, Comparator comparador)
	{
		Ordenamientos.Quicksort(datos.darElementos(), comparador);
	}
	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano()
	{
		return datosOriginal.darTamano();
	}
	public ArregloDinamico<Comparendo> darArreglo(){
		return datosOriginal;
	}
	public Comparendo buscar(int datoID)
	{
		int i = 0;

		while(datosOriginal.darElemento(i)!=null)
		{
			Comparendo actual=datosOriginal.darElemento(i);
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
		else if(caracteristicaComparable.equals("InfraccionInver"))
		{

			Comparator<Comparendo> Infraccion = new Comparator<Comparendo>() 
			{
				@Override
				public int compare(Comparendo o1, Comparendo o2) 
				{
					return -(o1.darInfraccion().compareTo(o2.darInfraccion()));
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
		else if(caracteristicaComparable.equals("Localidad")){

			Comparator<Comparendo> Localidad = new Comparator<Comparendo>() {
				@Override
				public int compare(Comparendo o1, Comparendo o2) {
					return o1.darLocalidad().compareTo(o2.darLocalidad());	
				}
			};
			return Localidad;
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
















