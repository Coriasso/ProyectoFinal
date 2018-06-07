package proyecto.main;


import proyecto.controlador.ControladorPrincipal;
import proyecto.modelo.ImplementacionDAO;
import proyecto.vista.Vista;

public class Main {

	public static void main(String[] args) {
		ImplementacionDAO dao = new ImplementacionDAO();
		Vista vista = new Vista();
		new ControladorPrincipal(vista, dao);

		
		
		
		
	}

}

