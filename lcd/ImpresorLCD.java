package lcd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Esta clase define los metodos necesarios para mostrar numeros del 0 al 9
 * en la forma que lo haria una LCD o un display de 7 segmentos
 */

public class ImpresorLCD implements NumCheck{
	private final char CARACTER_VERTICAL = '|';
    private final char CARACTER_HORIZONTAL = '-';
    private final char POSICION_X = 'X';
    private final char POSICION_Y = 'Y';
    private char[][] matrizImpr;
    private int NumSize;

    // Numero total de filas y columnas de la matris
    private int totalFilas;
    private int totalColum;
  

    /**
     * Metodo encargado de añadir una linea a la matriz de Impresión
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(char[][] matriz, int[] punto, char posFija,
            int size, char caracter) {

        if (posFija == POSICION_X) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     * Metodo encargado asignar un segmento a la matriz de Impresión
     * @param segmento Segmento a adicionar
     * @param digit Referencia a objeto Digito
     */  
    private void adicionarSegmento(int segmento, Digito digit) {

        switch (segmento) {
            case 1:
                adicionarLinea(this.matrizImpr, digit.DarPf1(), POSICION_Y,
                        this.NumSize, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(this.matrizImpr, digit.DarPf2(), POSICION_Y,
                        this.NumSize, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(this.matrizImpr, digit.DarPf5(), POSICION_Y,
                        this.NumSize, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(this.matrizImpr, digit.DarPf4(), POSICION_Y,
                        this.NumSize, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(this.matrizImpr, digit.DarPf1(), POSICION_X,
                        this.NumSize, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(this.matrizImpr, digit.DarPf2(), POSICION_X,
                        this.NumSize, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(this.matrizImpr, digit.DarPf3(), POSICION_X,
                        this.NumSize, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     * @param digit Referencia a Objeto Digito
     */
    private void adicionarDigito(Digito digit) {
    	// Almacena los segmentos activados para cada digito a mostrar
        List<Integer> segList = new ArrayList<>();
        
        // Establece los segmentos que se enciende con cada numero
        for(int i=0; i < digit.darsegmentosActivados().length(); i++) {
        	segList.add(Character.getNumericValue(digit.darsegmentosActivados().charAt(i)));
        }

        Iterator<Integer> iterator = segList.iterator();
    	
        while (iterator.hasNext()) {
        	//adiciona un segmento a la matriz de impreción
            adicionarSegmento(iterator.next(), digit);
        }
    }

    /**
     * Metodo encargado de imprimir numeros alamcenados 
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumeros(int size, String numeroImp, int espacio) 
    {
    	//Corrimiento para la ubicacion de cada digito 
        int pivotX = 0;
        char[] digitos;

        this.NumSize = size;

        // Calcula el numero de filas y columnas paracada digito
        Digito.setFilasColumDig(size);;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        this.totalFilas = Digito.filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        this.totalColum = (Digito.columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        this.matrizImpr = new char[this.totalFilas][this.totalColum];

        // contiene los caracteres de numeroImp
        digitos = numeroImp.toCharArray();

        //recorre el srreglo digitos
        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }
            
          
            Digito digit = new Digito(Character.getNumericValue(digito));
            
            //Asignación de puntos de ubicación a objeto digit
            digit.setValorDePuntos(pivotX);
                
            pivotX = pivotX + Digito.columDig + espacio;

            adicionarDigito(digit);
        }

        // Imprime matriz
        for (int i = 0; i < this.totalFilas; i++) {
            for (int j = 0; j < this.totalColum; j++) {
                System.out.print(this.matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

     /**
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesar(String comando, int espacioDig) {
    	//
        String[] parametros;
        int tam;
        
        // se verifica que el formato de el numero sea zize,num
        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //validar que la cadena tenga valores numericos
        if(NumCheck.isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion de los numeros 
        imprimirNumeros(tam, parametros[1],espacioDig);

    }
}