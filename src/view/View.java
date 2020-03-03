package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    public void printMenu()
	    		{
	    			System.out.println("1. Opcion 1. cargar Informacion");
	    			System.out.println("2. Opcion 2: Dar el primer comparendo por una localidad dada ");
	    			System.out.println("3. Opcion 3: Dar comparendo(s) en una fecha especifcada");
	    			System.out.println("4. Opcion 4: Dar primer comparendo por una infraccion dada");
	    			System.out.println("5. Opcion 5: Consultar comparendos por dos fechas dadas");
	    			System.out.println("6. Opcion 6: Consulta lista de comparendos por codigo de Infraccion dado");
	    			System.out.println("7. Opcion 7: Comparación de comparendos por Infracción en servicio Particular y servicio Público");
	    			System.out.println("8. Exit");
	    			System.out.println("------------------------------------------------------------------------");
	    			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	    			System.out.println("------------------------------------------------------------------------");

	    		}
		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			// TODO implementar
			for( int i = 0; i<modelo.darTamano(); i++){
				System.out.println(modelo.dardatos().darElemento(i));
				
			}
		}
}
