package model.data_structures;

public class Comparendo implements Comparable<Comparendo> {
	private int OBJECT_ID;
	private String FECHA_HORA;
	private String Infraccion;
	private String CLASE_VEHICULO;
	private String TIPO_SERVICIO;
	private String LOCALIDAD;
	private String DESC_INFRACCION;
	private String MEDIO;
	private Comparendo siguiente;



	public Comparendo (int pOBJECT_ID, String pFECHA_HORA, String pMedioDeteccion, String pClasevehiculo,String pTIPO_SERVICIO, String pInfraccion, String pDescInfraccion,  String pLOCALIDAD)
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
	@Override
	public int compareTo(Comparendo pComparendo) {
		// TODO Auto-generated method stu
		String[] ArrefechaThis = FECHA_HORA.trim().split("/");
		String fechaThis = ArrefechaThis[0]+ArrefechaThis[1]+ArrefechaThis[2];
		String[] ArrefechaThat = pComparendo.darFecha().trim().split("/");
		String fechaThat = ArrefechaThat[0]+ArrefechaThat[1]+ArrefechaThat[2];

		
		if(Integer.parseInt(fechaThis)< Integer.parseInt(fechaThat))return -1;
		else if (Integer.parseInt(fechaThis)> Integer.parseInt(fechaThat))return 1;
		else{
			if(this.OBJECT_ID< pComparendo.darID())return -1;
			else if (this.OBJECT_ID> pComparendo.darID())return 1;
		}
		return 0;
	}

}



