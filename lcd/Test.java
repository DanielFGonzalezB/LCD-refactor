package lcd;
import static org.junit.jupiter.api.Assertions.*;

class Test {
	@org.junit.jupiter.api.Test
	void testProcesar() {
		try {
			ImpresorLCD testImp = new ImpresorLCD();
			testImp.procesar("2,1a3", 3);
			fail("Se esperaba excepcion IllegalArgumentException");
		}catch(IllegalArgumentException IE){}
		
	}
	
	@org.junit.jupiter.api.Test
	void testIsNumeric() {
		assertTrue(NumCheck.isNumeric("234"));
	}
	
	
}
