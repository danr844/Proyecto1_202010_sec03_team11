package model.data_structures;

import java.util.Date;

public class Comparendo implements Comparable<Comparendo> {
	private int OBJECT_ID;
	private Date FECHA_HORA;
	private String Infraccion;
	private String CLASE_VEHICULO;
	private String TIPO_SERVICIO;
	private String LOCALIDAD;
	private String DESC_INFRACCION;
	private String MEDIO;
	private Comparendo siguiente;

//m

	public Comparendo (int pOBJECT_ID, Date pFECHA_HORA, String pMedioDeteccion, String pClasevehiculo,String pTIPO_SERVICIO, String pInfraccion, String pDescInfraccion,  String pLOCALIDAD)
	{
		OBJECT_ID = pOBJECT_ID;
		FECHA_HORA= pFECHA_HORA;
		MEDIO = pMedioDeteccion;
		Infraccion= pInfraccion;
		CLASE_VEHICULO = pClasevehiculo;
		TIPO_SERVICIO=pTIPO_SERVICIO;
		LOCALIDAD= pLOCALIDAD;
		DESC_INFRACCION = pDescInfraccion;

	}
	public Comparendo darSiguiente()
	{
		return siguiente;
	}
	public void cambiarSiguiente(Comparendo pSiguiente)
	{
		siguiente = pSiguiente;
	}

	public int darID(){
		return OBJECT_ID;
	}
	public Date darFecha(){
		return FECHA_HORA;
	}
	public String darMedio(){
		return MEDIO; 
	}
	public String darInfraccion(){
		return Infraccion;
	}
	public String darClaseVehiculo(){
		return CLASE_VEHICULO;
	}
	public String darTipoServicio(){
		return TIPO_SERVICIO;
	}
	public String darLocalidad(){
		return LOCALIDAD;
	}
	public String darDescInfo(){
		return DESC_INFRACCION;
	}
	@Override
	public int compareTo(Comparendo pComparendo) {
		// TODO Auto-generated method stu
		int respuesta = this.darFecha().compareTo(pComparendo.darFecha());
		if(respuesta==0){
			if(this.OBJECT_ID< pComparendo.darID())return -1;
			else if (this.OBJECT_ID> pComparendo.darID())return 1;
		}

		return respuesta;
	}

}



