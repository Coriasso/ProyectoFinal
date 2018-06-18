
package proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;

import proyecto.modelo.Genero;
import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.PersonaDTO;
import proyecto.modelo.PersonasExcepcion;
import proyecto.vista.Vista;

public class ControladorPanelDerecho implements ActionListener{

	private static Vista vista;
	private static ImplementacionDAO dao;
	private static List<PersonaDTO> personas; 
	private static int contador = 0;
	
	//Controlaremos si estamos creando nuevos registros o no
	private static boolean creacion = false;

	public ControladorPanelDerecho (Vista vista, ImplementacionDAO dao) {
		ControladorPanelDerecho.vista = vista;
		ControladorPanelDerecho.dao = dao;

		registrarComponentes();
		
		if(!dao.comprobarNumenoRegistros()) {
			
			System.out.println("Aquí no hay registros aun");
		}
		
		
		//Si existen registros en la base de datos se ejecutar este if
		if(dao.comprobarNumenoRegistros()) {
			personas = dao.listarDatos();
			cargarRegistro(contador,personas);
			vista.getBotonMas1().setEnabled(true);
			vista.getBotonMenos1().setEnabled(true);
			vista.getBotonMenos10().setEnabled(true);
			vista.getBotonMas10().setEnabled(true);

		}



	}


	private void registrarComponentes() {

		//Botones del menu derecho
		vista.getBotonMas1().addActionListener(this);
		vista.getBotonMenos1().addActionListener(this);
		vista.getBotonMenos10().addActionListener(this);
		vista.getBotonMas10().addActionListener(this);
		vista.getBotonCrear().addActionListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jbutton = (JButton) e.getSource();

		if(!creacion) {

			switch (jbutton.getText()) {
			case ">":
				contador +=1;
				break;
			case "<":
				contador -=1;
				break;
			case ">>":
				contador +=10;
				break;
			case "<<":
				contador -=10;
				break;
			default:
				break;
			}
			if (personas.size() != 0) 
				contador %= personas.size();  

			if (contador < 0)
				contador += personas.size();
			cargarRegistro(contador,personas);

		}

		if (creacion) {
			switch (jbutton.getText()) {
			case "+":
				System.out.println("Nuevo Registro");
				break;
			case "-":
				System.out.println("Quitar registro");
				break;
			case "->":
				System.out.println("->");
				break;
			case "<-":
				System.out.println("<-");
				break;
			case "Aceptar":
				crearRegistro();
				ControladorPanelIzquierdo.salirModoCreacion();
				break;
			default:
				break;
			}
		}




	}


	//m�todo est�tico que permite mostrar los datos obtenidos en el controlador izquierdo
	
	
	protected static void cargarRegistro(int contador, List<PersonaDTO> personas) {
		//Colocar la informaci�n de cada persona en los campos de texto

		//Seteamos la lista que se usa como argumento con la lista del controlador, para evitar que
		//si viene de otro sitio al avanzar de registro salte a la otra lista
		ControladorPanelDerecho.personas = personas;
		
		//Con el contador hacemos lo mismo
		ControladorPanelDerecho.contador = contador;

		vista.getTextNombre().setText(personas.get(contador).getNombre());
		vista.getTextApellido().setText(personas.get(contador).getApellido());
		vista.getTextGenero().setText(personas.get(contador).getGenero());		
		vista.getTextEmail().setText(personas.get(contador).getEmail());
		vista.getTextCiudad().setText(personas.get(contador).getPais());
		vista.getTextDni().setText(personas.get(contador).getDni()+"");



		//N�nero de registro actual y total
		vista.getRegistroActual().setText((contador + 1) + "/" + (personas.size()));
		//Sacamos el n�mero de d�gitos que hay, para asignarle un tama�o din�mico al cuadro de texto.
		//Se convierte en string y se saca el lenght para el n�mero de digitos en cada n�mero
		int anchuraContador = (personas.size()+"").length()+((contador+1)+"").length() +1;
		vista.getRegistroActual().setSize((anchuraContador*8), 23);



	}



	protected static void borradoDeDatos() {
		dao.borrarCampo(personas.get(contador).getDni());
		//Despues de borrar el campo se vuelve a consultar la base de datos
		personas = dao.listarDatos();
		cargarRegistro(1, personas);
	}






	//Parte relacionada con la creacion de registros
	protected static void entrarModoCreacion() {
		vista.getTextNombre().setText("");;
		vista.getTextApellido().setText("");
		vista.getTextGenero().setText("");		
		vista.getTextEmail().setText("");
		vista.getTextCiudad().setText("");
		vista.getTextDni().setText("");
		vista.getRegistroActual().setText("1/1");
		contador = 0;
		personas.clear();


	}

	protected static void salirModoCreacion() {
		personas = dao.listarDatos();
		contador = 0;
		cargarRegistro(contador, personas);

	}

	private void crearRegistro() {
		String nombre = vista.getTextNombre().getText();
		String apellido = vista.getTextApellido().getText();
		String email = vista.getTextEmail().getText();
		String genero = vista.getTextGenero().getText();
		String ciudad =vista.getTextCiudad().getText();
		int dni =Integer.parseInt( vista.getTextDni().getText());

		PersonaDTO persona = null;

		
			try {
				if (genero.matches("Masculino"))
				persona = new PersonaDTO(nombre, apellido, email, Genero.Masculino, ciudad, dni);
				else
					persona = new PersonaDTO(nombre, apellido, email, Genero.Femenino, ciudad, dni);
			} catch (PersonasExcepcion e) {
				System.out.println("No se pudo crear esta persona");;
			}
		
		if(persona != null)
			dao.introducirDatos(persona);
		else
			System.out.println("No se pudo crear el registro");

	}




	protected static void setCreacion(boolean creacion) {
		ControladorPanelDerecho.creacion = creacion;
	}


}


