
package proyecto.controlador;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import proyecto.modelo.APILectura;
import proyecto.modelo.Conexion;
import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.PersonaDTO;
import proyecto.vista.Vista;

public class Controlador implements ActionListener{

	private Vista vista;
	private ImplementacionDAO dao = new ImplementacionDAO();
	private String Path;
	private List<PersonaDTO> personas; 
	private int contador = 0;

	public Controlador (Vista vista) {
		this.vista = vista;

		registrarComponentes();
		//Si existen registros en la base de datos se ejecutará este if
		if(comprobarRegistros()) {
	    personas = dao.listarDatos();
	    cargarRegistro(contador);
		
		}
		
		

	}


	private void registrarComponentes() {
		vista.getMenuSalir().addActionListener(this);
		vista.getMenuCargarArchivos().addActionListener(this);
		vista.getBotonMas1().addActionListener(this);
		vista.getBotonMenos1().addActionListener(this);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//Comprobar el tipo de objeto que activó el evento
		//Agrupacion del JMenu
		if(e.getSource().getClass() == JMenuItem.class) {
			//Obtenemos el objeto para poder leer lo que hay dentro
			JMenuItem jmenu = (JMenuItem) e.getSource();

			if(jmenu.getText().equals( "Salir")) System.exit(0); //Salida sin fallos
		
			else if(jmenu.getText().equals("Cargar Archivos")) selectorDeArchivos();
		}
		
		if(e.getSource().getClass() == JButton.class) {
			JButton jbutton = (JButton) e.getSource();
			switch (jbutton.getText()) {
			case ">":
				contador +=1;
				cargarRegistro(contador);
				break;
			case "<":
				contador -=1;
				cargarRegistro(contador);
				break;

			default:
				break;
			}
			
		}
		

	}
	
	private void selectorDeArchivos() {
		JFileChooser jFileChooser = new JFileChooser(".");
		int resultado = jFileChooser.showOpenDialog(vista.getFrame());
		
		if (resultado == JFileChooser.APPROVE_OPTION) {
			Path = jFileChooser.getSelectedFile().getPath();
			List<PersonaDTO> personas = APILectura.leerJson(Path);
			dao.introducirDatos(personas);
		}
	}
	//Devuelve true si hay al menos un registro en la tabla
	private boolean comprobarRegistros() {
		boolean comprobacion = false;
		Connection c = Conexion.abrirConexion();
		
		try {
			PreparedStatement spt = c.prepareStatement("SELECT count(*) FROM personas");
			ResultSet r = spt. executeQuery();
			if (r.getInt(1) > 0) comprobacion = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return comprobacion;
		
	}
	
	private void cargarRegistro(int contador) {
		vista.getTextNombre().setText(personas.get(contador).getNombre());
		vista.getRegistroActual().setText((contador + 1) + "/" + (personas.size() + 1));
		
		
	}
}
