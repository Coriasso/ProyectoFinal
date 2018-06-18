package proyecto.modelo;

import java.io.FileWriter;
import java.time.LocalDate;

public class Logs {

	public static void escribirLog(String sql, boolean exito) {
		LocalDate hora = LocalDate.now(); 
		try (FileWriter out = new FileWriter("logs/logs.txt", true);) {
			out.write("Orden: " + sql + " Fecha: " + hora + " Exito: " + exito + System.lineSeparator() );
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
