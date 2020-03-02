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
	private ArregloDinamico<Comparendo> datos;



	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		datos = new ArregloDinamico<Comparendo>(capacidad);

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

	public ArregloDinamico<Comparendo> comparendosConInfraccion(String pInfraccion)
	{
		ArregloDinamico<Comparendo> retorno= new ArregloDinamico<>(0);
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

	public int consultarNumeroComparendosPorInfraccionTipo(String pInfraccion, String pTipo)
	{
		int retorno=0;
		int i=0;
		while(i<datos.darTamano())
		{
			Comparendo actual= datos.darElemento(i);
			if(actual.darInfraccion()==pInfraccion && actual.darTipoServicio()==pTipo)
				retorno++;
		}
		return retorno;
	}
	public Comparendo darComparendoPorInfraccion(String pInfraccion){
		ArregloDinamico<Comparendo>lista= copiarComparendos();
		Comparator<Comparendo> compare = darComparador("Infraccion");
		Ordenamientos.sortMerge(lista.darElementos(), 0,lista.darTamano()-1, compare);
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
		ArregloDinamico<Comparendo>lista = copiarComparendos();
		ArregloDinamico<Comparendo>res =new ArregloDinamico<>(lista.darTamano());
		Ordenamientos.sortMerge(lista.darElementos(), 0, lista.darTamano()-1, darComparador("Fecha"));
		int inicio = 0;
		int fin = lista.darTamano() - 1;
		
		boolean alto = false; 
		while( inicio <= fin && !alto )
		{
			int medio = ( inicio + fin ) / 2;
			if( lista.darElemento(medio).darFecha().compareTo(fechaHora)==0 )
			{
				res.agregar(lista.darElemento(medio)); 
				alto = true;
			}
			else if( lista.darElemento(medio).darFecha().compareTo(fechaHora)>0 )
				fin = medio - 1;
			else
				inicio = medio + 1;
		}
		
		Ordenamientos.sortMerge(res.darElementos(), 0, res.darTamano()-1, darComparador("InfraccionInver"));
		return res;
	}
	
	public  int firstFecha(ArregloDinamico<Comparendo> arr , int low, int high, Date x, int n) 
    { 
		ArregloDinamico<Comparendo>lista = copiarComparendos();
		Ordenamientos.sortMerge(lista.darElementos(), 0, lista.darTamano()-1, darComparador("Fecha"));
		
        if(high >= low) 
        { 
            int mid = low + (high - low)/2; 
            if( ( mid == 0 || x.after(lista.darElemento(mid-1).darFecha())) && x.equals(lista.darElemento(mid).darFecha())) 
                return mid; 
             else if(x.after(lista.darElemento(mid).darFecha())) 
                return firstFecha(lista, (mid + 1), high, x, n ); 
            else
                return firstFecha(lista, low, (mid -1), x, n); 
        } 
    return -1; 
    } 
   //111124544664324798653478643323434565443
    /* if x is present in arr[] then returns the index of 
    LAST occurrence of x in arr[0..n-1], otherwise 
    returns -1 */
    public  int lastFecha(ArregloDinamico<Comparendo> arr , int low, int high, Date x, int n) 
    { 
    	ArregloDinamico<Comparendo>lista = copiarComparendos();
		Ordenamientos.sortMerge(lista.darElementos(), 0, lista.darTamano()-1, darComparador("Fecha"));
		
        if (high >= low) 
        { 
            int mid = low + (high - low)/2; 
            if (( mid == n-1 || x.before(lista.darElemento(mid+1).darFecha()) ) && x.equals(lista.darElemento(mid).darFecha())) 
                 return mid; 
            else if (x.before(lista.darElemento(mid).darFecha())) 
                return lastFecha(lista, low, (mid -1), x, n); 
            else
                return lastFecha(lista, (mid + 1), high, x, n); 
        } 
    return -1; 
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
		return  datos.eliminar(object);
	}
	public IArregloDinamico<Comparendo> dardatos(){
		return datos;
	}
	public void agregarArregloDinamico(Comparendo comparendo){
		datos.agregar(comparendo);
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
	public ArregloDinamico<Comparendo> copiarComparendos(){
		ArregloDinamico<Comparendo> arreglonuevo = new ArregloDinamico<Comparendo>(datos.darTamano());
		for(int i = 0; i<datos.darTamano(); i++)
		{
			arreglonuevo.darElementos()[i]=(datos.darElemento(i));
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
















