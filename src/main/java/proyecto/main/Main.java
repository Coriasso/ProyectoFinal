package proyecto.main;


import proyecto.controlador.Controlador;
import proyecto.modelo.APILectura;
import proyecto.modelo.Conexion;
import proyecto.vista.Vista;

public class Main {

	public static void main(String[] args) {

		Vista vista = new Vista();
		new Controlador(vista);

		
		
		
	}

}

