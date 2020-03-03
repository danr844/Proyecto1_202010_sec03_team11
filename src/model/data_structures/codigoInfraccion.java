package model.data_structures;

public class codigoInfraccion implements Comparable<codigoInfraccion> 
{
	private String codigo;
	private int cantidad;
	
	public codigoInfraccion(String pCodigo)
	{
		codigo=pCodigo;
		cantidad=0;
	}
	public int darCantidad()
	{
		return cantidad;
	}
	public String darCodigo()
	{
		return codigo;
	}
	
	public void aumentarCantidad()
	{
		cantidad++;
	}
	public int compareTo(codigoInfraccion pCodigo) 
	{
		// TODO Auto-generated method stu
		int respuesta =0;
		if(this.darCantidad()<pCodigo.darCantidad())
			respuesta=-1;
		else if(this.darCantidad()>pCodigo.darCantidad())
			respuesta=1;
		return respuesta;
	}

	
}
