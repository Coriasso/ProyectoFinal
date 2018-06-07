package proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.PersonaDTO;
import proyecto.vista.Vista;

public class ControladorPanelIzquierdo implements ActionListener {

	private static Vista vista;
	private ImplementacionDAO dao;

	public ControladorPanelIzquierdo(Vista vista, ImplementacionDAO dao) {

		ControladorPanelIzquierdo.vista = vista;
		this.dao = dao;
		registrarComponentes();	
		if(dao.comprobarNumenoRegistros()) {
		dominiosEmail();
		vista.getComboBox().setModel(new DefaultComboBoxModel(vista.getExtensionesEmail().toArray()));
		}
	}

	private void registrarComponentes() {
		vista.getBotonFiltrar().addActionListener(this);
		vista.getBtnBorrar().addActionListener(this);
		vista.getBotonCrear().addActionListener(this);
		vista.getBotonLimpiar().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().getClass() == JButton.class) {
			JButton jbutton = (JButton) e.getSource();
			switch (jbutton.getText()) {
			case "Filtrar":
				ControladorPanelDerecho.cargarRegistro(0, filtradoDeDatos());
				break;
			case "Borrar":
				borrarDatos();
				break;
			case "Limpiar":
				limpiarDatos();
				break;
			case "Crear":
				entrarModoCreacion();
				break;
			case "Cancelar":
				salirModoCreacion();
				break;
			default:
				break;
			}

		}
	}

	
	
	
	
	private List<PersonaDTO> filtradoDeDatos(){
		List<String []> filtro = new ArrayList<>();
		List <PersonaDTO> personas;
		//Se recogen todos los posibles filtros aplicables en un array estilo clave:valor
		String[] genero = new String[2];
		genero[0] = "genero";
		
		//Para la extension, el primer String es el nombre de la columna
		String[] extension = new String[2];
		extension[0] = "email";
		
		String[] pais = new String[2];
		pais[0] = "pais";

		if(vista.getBotonFemenino().isSelected()) { 
			genero [1] = "Femenino";
			filtro.add(genero);
			}
		else if (vista.getBotonMasculino().isSelected()) {
			genero [1] = "Masculino";
			filtro.add(genero);
		}
		//Comprobaremmos si el texto introducido est� vac�o y si tiene una funcion SQL.
		if(!vista.getTextFiltroPais().getText().isEmpty()) {
			pais[1] = vista.getTextFiltroPais().getText();
			filtro.add(pais);
		}
		
		
		if(vista.getComboBox().getSelectedItem() != "cualquiera") {
			extension[1] = (String) vista.getComboBox().getSelectedItem();
			filtro.add(extension);
		}
		
			
		if (filtro.size() != 0) 
			//EL m�todo siguiente tiene un c�digo que construye la sentencia segun los arrays que le llegan
		
			personas = dao.listarRegistroConFiltro(filtro);
			
		else 
			personas = dao.listarDatos();


		return personas;
	}



	//Metodo para obtener las extensiones unicas de los email
	private void dominiosEmail(){
		List<PersonaDTO> personas = dao.listarDatos();
		if (personas.size() > 0) {
		List<String> email = new ArrayList<>();
		Set<String> extensionesUnique = new HashSet<>();
		//Sacamos los email de cada persona
		for (PersonaDTO personaDTO : personas) {
			email.add(personaDTO.getEmail());
		}
		//Por cada email, sacamos la posiciones del punto y lo a�adimos a una coleccion
		for (int i = 0; i < email.size(); i++) {
			int primerPunto = email.get(i).indexOf(".");
			String extension = email.get(i).substring(primerPunto );
			extensionesUnique.add(extension);
		}
		//Boramos todos los email e introducimos solo las extensiones �nica
		email.clear();
		//La primera opcion ser� la por defecto
		email.add("cualquiera");
		email.addAll(extensionesUnique);		

		vista.setExtensionesEmail(email);
		
		}

	}
	
	private void borrarDatos() {	
		ControladorPanelDerecho.borradoDeDatos();		
	}
	
	
	
	private void limpiarDatos() {
		ControladorPanelDerecho.cargarRegistro(0, dao.listarDatos());
	}
	
	
	
	private void entrarModoCreacion() {
		
		vista.getBotonMas10().setText("+");
		vista.getBotonMenos10().setText("-");
		vista.getBotonMas1().setText("->");
		vista.getBotonMas1().setEnabled(false);
		vista.getBotonMenos1().setText("<-");
		vista.getBotonMenos1().setEnabled(false);
		vista.getBotonCrear().setText("Aceptar");
		vista.getBtnBorrar().setText("Cancelar");
		vista.getBotonFemenino().setEnabled(false);
		vista.getBotonMasculino().setEnabled(false);
		vista.getBotonAmbosGeneros().setEnabled(false);
		vista.getTextFiltroPais().setEnabled(false);
		vista.getComboBox().setEnabled(false);
		vista.getBotonFiltrar().setEnabled(false);
		vista.getBotonLimpiar().setEnabled(false);
		
		ControladorPanelDerecho.setCreacion(true);
		ControladorPanelDerecho.entrarModoCreacion();
		
	}
	
	protected static void salirModoCreacion() {
		
		vista.getBotonMas10().setText(">>");
		vista.getBotonMenos10().setText("<<");
		vista.getBotonMas1().setText(">");
		vista.getBotonMas1().setEnabled(true);
		vista.getBotonMenos1().setEnabled(true);
		vista.getBotonMenos1().setText("<");
		vista.getBotonCrear().setText("Crear");
		vista.getBtnBorrar().setText("Borrar");
		vista.getBotonFemenino().setEnabled(true);
		vista.getBotonMasculino().setEnabled(true);
		vista.getBotonAmbosGeneros().setEnabled(true);
		vista.getTextFiltroPais().setEnabled(true);
		vista.getComboBox().setEnabled(true);
		vista.getBotonFiltrar().setEnabled(true);
		vista.getBotonLimpiar().setEnabled(true);
		
		ControladorPanelDerecho.setCreacion(false);
		ControladorPanelDerecho.salirModoCreacion();
		
	}
	
}
