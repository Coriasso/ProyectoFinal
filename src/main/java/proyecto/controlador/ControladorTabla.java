package proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.TableModel;

import proyecto.modelo.Genero;
import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.ModeloTabla;
import proyecto.modelo.PersonaDTO;
import proyecto.modelo.PersonasExcepcion;
import proyecto.modelo.ModeloTablaCreacion;
import proyecto.vista.Vista;

public class ControladorTabla implements ActionListener{
	private static final int NUMERO_REGISTROS = 23;
	private static Vista vista;
	private static ImplementacionDAO dao;

	private static JTable tabla;
	private static Object[][] datos = new Object[NUMERO_REGISTROS][6];
	private static ModeloTabla modelo;

	private static int paginaActual = 0;
	private static List<PersonaDTO> personas;
	private static int paginasTotales;


	public ControladorTabla (Vista vista, ImplementacionDAO dao) {
		ControladorTabla.vista = vista;
		ControladorTabla.dao = dao;
		ControladorTabla.tabla = vista.getTablaCompleta();
		registrarComponentes();
		
		if(!ControladorTabla.dao.comprobarNumenoRegistros())
			System.out.println("hola");
		mostrarTabla();



	}


	public static void mostrarTabla() {
		
		paginaActual = 0;
		normalizarPaginadoTabla();
		actualizarDatos();
		modelo = new ModeloTabla(datos, dao, vista);
		tabla.setModel(modelo);
	}

	private void registrarComponentes() {
		vista.getBotonTablaAvanzar().addActionListener(this);
		vista.getBotonTablaRetroceder().addActionListener(this);
		vista.getBotonBorrarTabla().addActionListener(this);
		vista.getBotonTablaCrear().addActionListener(this);



	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		switch (boton.getText()) {
		case ">>":
			paginaActual += 1;
			normalizarPaginadoTabla();
			break;
		case "<<":
			paginaActual -= 1;
			normalizarPaginadoTabla();
			break;
		case "Borrar":
			borrarRegistro();
			normalizarPaginadoTabla();
			break;
		case "Nuevos registros":
			creacionDeRegistros();
			break;
		case "Terminado":
			salirCreacion();
			break;
		default:
			break;
		}
		actualizarDatos();
		tabla.repaint();
	}


	public static void actualizarDatos(){


	

		//Si no es exacto aquï¿½ se guarda el numero de registro restante

		int contador = 0;
		//Para sacar los registros de la pagina actual quitamos los de paginas anteriores

		for (int i = 0; i <  NUMERO_REGISTROS - 1; i++) {
			// Los datos a los que se acceden en la lista vienen determinados
			// por la pï¿½gina que se muestre y el nï¿½mero de registros de esta
			int registroSiguiente = i + (paginaActual * NUMERO_REGISTROS);

			for (int j = 0; j < 6; j++) {
				if(registroSiguiente < (personas.size())) {
					datos[contador][0] = personas.get(registroSiguiente).getDni();
					datos[contador][1] = personas.get(registroSiguiente).getNombre();
					datos[contador][2] = personas.get(registroSiguiente).getApellido();
					datos[contador][3] = personas.get(registroSiguiente).getEmail();
					datos[contador][4] = personas.get(registroSiguiente).getGenero();
					datos[contador][5] = personas.get(registroSiguiente).getPais();
				}
				else {
					datos[contador][0] = "";
					datos[contador][1] = "";
					datos[contador][2] = "";
					datos[contador][3] = "";
					datos[contador][4] = "";
					datos[contador][5] = "";
				}
			}
			contador ++;
		}
		//Contador de pï¿½ginas, dependiendo si hay una pï¿½gina final incompleta


	}
	//Añadimos una pagina extra, la cargamos y obtenemos todas las filas vacias
	//para rellenarlas con los datos que vamos a introducir
	private void creacionDeRegistros() {
		ModeloTablaCreacion modeloCreacion = new ModeloTablaCreacion(datos, dao, vista);
		tabla.setModel(modeloCreacion);
		actualizarDatos();

		

		paginasTotales += 1;
		paginaActual = paginasTotales;
		vista.getBotonTablaAvanzar().setEnabled(false);
		vista.getBotonTablaRetroceder().setEnabled(false);
		vista.getBotonBorrarTabla().setEnabled(false);
		vista.getBotonTablaCrear().setText("Terminado");
		vista.getTextoRegistroTabla().setText("Creando registros");
		//System.out.println(modeloCreacion.getColumnClass(5));
	}

	private void salirCreacion() {

		List<PersonaDTO> nuevasPersonas = new ArrayList<>();

		for (int i = 0; i < NUMERO_REGISTROS; i++) {
			
			//filtro que no permite dni nulo o genero inválido
			if(!String.valueOf( modelo.getValueAt(i, 0)).matches("\\d{8}")) 
				continue;
			try {
				String nombre = "";
				if (!String.valueOf( modelo.getValueAt(i, 1)).matches("null"))
					nombre = String.valueOf( modelo.getValueAt(i, 1));
				System.out.println("Nombre: " + nombre);
				
				String apellido = "";
				if (!String.valueOf( modelo.getValueAt(i, 2)).matches("null"))
					apellido = String.valueOf( modelo.getValueAt(i, 2));
				System.out.println("Apellido: " + apellido);
				

				String email = "";
				if (!String.valueOf( modelo.getValueAt(i, 3)).matches("null"))
					email = String.valueOf( modelo.getValueAt(i, 3));
				System.out.println("Email: " + email);
				
				Genero genero = Genero.Masculino;
				if(String.valueOf( modelo.getValueAt(i, 4)).toLowerCase().matches("femenino"))
					genero = Genero.Femenino;
						
				String pais = "";
				if (!String.valueOf( modelo.getValueAt(i, 5)).matches("null"))
					pais = String.valueOf( modelo.getValueAt(i, 5));
				System.out.println("pais: " + pais);
				
				int dni =Integer.parseInt(String.valueOf( modelo.getValueAt(i, 0)));
				
				
				nuevasPersonas.add(new PersonaDTO(nombre, apellido,email,genero,pais,dni));
			
				

			} catch (PersonasExcepcion e) {
				e.printStackTrace();
				System.out.println("Problema al crear un registro");
			}
		}

		System.out.println(nuevasPersonas);
		dao.introducirDatos(nuevasPersonas);


		
		
		vista.getTablaCompleta().setModel(modelo);
	
		vista.getBotonTablaAvanzar().setEnabled(true);
		vista.getBotonTablaRetroceder().setEnabled(true);
		vista.getBotonBorrarTabla().setEnabled(true);
		vista.getBotonTablaCrear().setText("Nuevos registros");
		
		
		normalizarPaginadoTabla();
		paginaActual =  (int) Math.floor(personas.size() / NUMERO_REGISTROS);
		mostrarTabla();


	}


	private static void normalizarPaginadoTabla() {

		if(paginaActual > paginasTotales ) paginaActual = 0;
		if(paginaActual < 0) paginaActual = paginasTotales;
		personas = dao.listarDatos();
		paginasTotales = (int) Math.floor(personas.size() / NUMERO_REGISTROS); 
		vista.getTextoRegistroTabla().setText((paginaActual + 1) + "/" + (paginasTotales + 1));


	}


	private void borrarRegistro() {

		int dni = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);

		dao.borrarCampo(dni);		
	}


}
