package proyecto.controlador;

import java.io.File;

import javax.swing.JOptionPane;

import proyecto.modelo.ImplementacionDAO;
import proyecto.vista.Vista;

public class ControladorPrincipal {
	
	public ControladorPrincipal(Vista vista, ImplementacionDAO dao){
		
		if(!dao.comprobarNumenoRegistros())
			dao.crearTabla();
		
	
		
		new ControladorPanelDerecho(vista, dao);
		new ControladorPanelIzquierdo(vista, dao);
		new ControladorMenu(vista, dao);
		new ControladorTabla(vista,dao);
	}
	
	
}
