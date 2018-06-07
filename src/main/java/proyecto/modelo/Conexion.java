package proyecto.modelo;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	private static Connection conexion;

	private Conexion() {


		try {
			
			
			//conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro","root","");
			conexion = DriverManager.getConnection("jdbc:sqlite:BBDD/personas.db");
		} catch (SQLException e) {e.printStackTrace();}
	}


	public static Connection abrirConexion() {
		if(conexion == null)
			new Conexion();
		else
			System.out.println("Ya existe una conexion abierta");

		return conexion;
	}
	
	public static Object cerrarConexion() {
		//TODO POR IMPLEMENTAR 
		return null;
	}

	static class ShutdownHook extends Thread{
		@Override
		public void run() {
			if (conexion != null)
				try {
					System.out.println("Cerrando conexi�n");
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
}
