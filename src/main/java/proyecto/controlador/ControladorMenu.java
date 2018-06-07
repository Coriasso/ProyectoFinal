package proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

import proyecto.modelo.APILectura;
import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.PersonaDTO;
import proyecto.vista.Vista;

public class ControladorMenu implements ActionListener {

	private String Path;
	private ImplementacionDAO dao;
	private Vista vista;


	public ControladorMenu (Vista vista, ImplementacionDAO dao) {

		this.vista = vista;
		this.dao = dao;
		registrarComponentes();
	}


	private void registrarComponentes() {
		vista.getMenuSalir().addActionListener(this);
		vista.getMenuCargarArchivos().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JMenuItem jmenu = (JMenuItem) e.getSource();

		if(jmenu.getText().equals( "Salir")) System.exit(0); //Salida sin fallos

		else if(jmenu.getText().equals("Cargar Archivos")) selectorDeArchivos();
	}


	private void selectorDeArchivos() {
		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista.getFrame());

		if (resultado == JFileChooser.APPROVE_OPTION) {
			Path = jFileChooser.getSelectedFile().getPath();
			List<PersonaDTO> personas = APILectura.leerJson(Path);
			dao.introducirDatos(personas);
			ControladorPanelDerecho.cargarRegistro(0, personas);
			vista.getBotonMas1().setEnabled(true);
			vista.getBotonMenos1().setEnabled(true);
			vista.getBotonMenos10().setEnabled(true);
			vista.getBotonMas10().setEnabled(true);
		}
		ControladorTabla.mostrarTabla();
	}


}
