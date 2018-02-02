package lcd;
/**
 * Esta clase crea un objeto de tipo Digito que contiene las caracteristicas como
 * valor, segemtos encendidos y puntos.
 * La dispocisión de los segmentos es: 
 *     5	
 *    --
 *  1|  |3
 *   6--
 *  2|  |4
 *    --
 *     7
 */
    
public class Digito {
	private int valor;
	private String segmentosActivados;
	private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    public static int filasDig;
    public static int columDig;
    // Segmentos que se encienden para 0,1,2,3.....
 	private final String[] SEGMENTOS_ENCENDIDOS = {"123457", "34", "53627",
 												"53647","1634", "51647", 
 												"516274", "534", "1234567", 
 												"134567",};
    
    /**
     * Constructor
     * @param valor Valor digito
     */    
    public Digito(int valor) {
    	this.valor = valor;
    	this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
        
        //Asignación de los segmentos que se encienden con cada numero
        segmentosActivados = SEGMENTOS_ENCENDIDOS[this.valor]; 
    }
    
    
    /**
    * Metodo encargado de inicializar filasDig y columDig (Setter)
    * @param NumSize tamaño de los digitos
    */  
    public static void setFilasColumDig(int NumSize) {
    	filasDig = (2 * NumSize) + 3;
    	columDig = (NumSize + 2);
    }
    
    /**
    * Metodo encargado de inicializar puntos(Setter) 
    * @param pivotX corrimiento de los digitos
    */ 

    public void setValorDePuntos(int pivotX) {
    	
    	this.pf1[0] = 0;
        this.pf1[1] = 0 + pivotX;

        this.pf2[0] = (filasDig / 2);
        this.pf2[1] = 0 + pivotX;

        this.pf3[0] = (filasDig - 1);
        this.pf3[1] = 0 + pivotX;

        this.pf4[0] = (columDig - 1);
        this.pf4[1] = (filasDig / 2) + pivotX;

        this.pf5[0] = 0;
        this.pf5[1] = (columDig - 1) + pivotX;
    }
    
    /**
     * Metodo Getter
     */ 
    public int darValor() {
    	return this.valor;
    }
    
    /**
     * Metodo Getter 
     */ 
    public String darsegmentosActivados() {
    	return segmentosActivados;
    }
    
    /**
     * Metodo Getter
     */ 
    public int[] DarPf1() {
    	return this.pf1;
    }
    
    /**
     * Metodo Getter
     */ 
	public int[] DarPf2() {
		return this.pf2;
	}
	
	/**
    * Metodo Getter 
    */ 
	public int[] DarPf3() {
		return this.pf3;
    }
	
	/**
    * Metodo Getter 
    */ 
	public int[] DarPf4() {
		return this.pf4;
	}
	
	/**
    * Metodo Getter
    */ 
	public int[] DarPf5() {
		return this.pf5;
	}

}
