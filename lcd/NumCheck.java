package lcd;
/**
* Interfaz que define un unico metodo para identificar si una cadena
* contiene valores numericos.
*/  
public interface NumCheck {
	/**
    * Metodo encargado de validar si una cadena es numerica
    * @param cadena Cadena de entrada
    * @return boolean 
    */  
	static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
	
}
