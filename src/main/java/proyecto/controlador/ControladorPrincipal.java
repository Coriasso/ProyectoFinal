package proyecto.controlador;

import proyecto.modelo.ImplementacionDAO;
import proyecto.vista.Vista;

public class ControladorPrincipal {
	
	public ControladorPrincipal(Vista vista, ImplementacionDAO dao){
		
		if(!dao.comprobarNumenoRegistros()) {
			System.out.println("No hay datos");
			dao.crearTabla();
		}
		
		new ControladorPanelDerecho(vista, dao);
		new ControladorPanelIzquierdo(vista, dao);
		new ControladorMenu(vista, dao);
		new ControladorTabla(vista,dao);
	}
}
