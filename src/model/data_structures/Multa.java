package model.data_structures;

public class Multa  {
	private int OBJECT_ID;
	private String FECHA_HORA;
	private String Infraccion;
	private String CLASE_VEHICULO;
	private String TIPO_SERVICIO;
	private String LOCALIDAD;
	private String DESC_INFRACCION;
	private String MEDIO;
	


	public Multa (int pOBJECT_ID, String pFECHA_HORA, String pMedioDeteccion, String pClasevehiculo,String pTIPO_SERVICIO, String pInfraccion, String pDescInfraccion,  String pLOCALIDAD)
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

	public int darID(){
		return OBJECT_ID;
	}
	public String darFecha(){
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

}



