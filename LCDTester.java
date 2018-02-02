import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import lcd.ImpresorLCD;
import lcd.NumCheck;


public class LCDTester {

    static final String CADENA_FINAL = "0,0";
    
    public static void main(String[] args) {

        // Almacena las lineas de codigo que contienen los digitos
        List<String> listaComando = new ArrayList<>();
        String comando = "";
        int espacioDig = -1;
        
        try {   
            comando = JOptionPane.showInputDialog("Espacio entre Digitos (0 a 5):");

            // Valida si comando es un numero
            if (NumCheck.isNumeric(comando)) 
            {
                espacioDig = Integer.parseInt(comando);
                
                // se valida que el espaciado este entre 0 y 5
                if(espacioDig <0 || espacioDig >5)
                {
                    throw new IllegalArgumentException("El espacio entre "
                            + "digitos debe estar entre 0 y 5");
                }   
            } 
            else 
            {
                throw new IllegalArgumentException("Cadena " + comando
                        + " no es un entero");
            }
            
            // Ingreso de los numeros por parte del usuario
            // de la forma size,numeros
            do
            {
            	comando = JOptionPane.showInputDialog("Entrada: ");
                if(!comando.equals(CADENA_FINAL))
                {
                    listaComando.add(comando);
                }
            }while (!comando.equals(CADENA_FINAL)); 
            

            ImpresorLCD impresorLCD = new ImpresorLCD();
            Iterator<String> iterator = listaComando.iterator();
            
            while (iterator.hasNext()) 
            {
                try 
                {
                    impresorLCD.procesar(iterator.next(), espacioDig);
                } catch (Exception ex) 
                {
                    System.out.println("Error: "+ex.getMessage());
                }
            }
        } catch (Exception ex) 
        {
            System.out.println("Error: "+ex.getMessage());
        }

    }

}