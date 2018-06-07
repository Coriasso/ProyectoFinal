package proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import proyecto.modelo.ImplementacionDAO;
import proyecto.modelo.ModeloTabla;
import proyecto.modelo.PersonaDTO;
import proyecto.vista.Vista;

public class ControladorTabla implements ActionListener{
	private static final int NUMERO_REGISTROS = 23;
	private static Vista vista;
	private static ImplementacionDAO dao;
	private JTable tabla;
	private static Object[][] datos = new Object[NUMERO_REGISTROS][6];
	private static int paginaActual = 0;
	private ModeloTabla modelo;


	public ControladorTabla (Vista vista, ImplementacionDAO dao) {
		ControladorTabla.vista = vista;
		ControladorTabla.dao = dao;
		this.tabla = vista.getTablaCompleta();
		registrarComponentes();
		mostrarTabla();


	}


	private void mostrarTabla() {
		actualizarDatos();
		modelo = new ModeloTabla(datos, dao, vista);
		tabla.setModel(modelo);
	}

	private void registrarComponentes() {
		vista.getBotonTablaAvanzar().addActionListener(this);
		vista.getBotonTablaRetroceder().addActionListener(this);
		vista.getBotonBorrarTabla().addActionListener(this);

		ListSelectionModel selector = tabla.getSelectionModel();
		selector.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton boton = (JButton) e.getSource();
		switch (boton.getText()) {
		case ">>":
			paginaActual += 1;
			break;
		case "<<":
			paginaActual -= 1;
			break;
		case "Borrar":
			borrarRegistro();
			break;
		default:
			break;
		}
		
		actualizarDatos();
		tabla.repaint();
	}


	public static void actualizarDatos(){
		
		List<PersonaDTO> personas = dao.listarDatos();
				
		//Determinamos el número de páginas completas que podemos crear
		int paginasTotales = (int) Math.ceil(personas.size() / NUMERO_REGISTROS); 
		if(paginaActual == paginasTotales + 2) paginaActual = 0;
		//Si no es exacto aquí se guarda el numero de registro restante
		int registrosPaginaFinal = personas.size() % NUMERO_REGISTROS;
		
		int contador = 0;
		//Para sacar los registros de la pagina actual quitamos los de paginas anteriores
		
		for (int i = 0; i <  NUMERO_REGISTROS - 1; i++) {
			// Los datos a los que se acceden en la lista vienen determinados
			// por la página que se muestre y el número de registros de esta
			int registroSiguiente = i + (paginaActual * NUMERO_REGISTROS);
			for (int j = 0; j < 6; j++) {
				datos[contador][0] = personas.get(registroSiguiente).getDni();
				datos[contador][1] = personas.get(registroSiguiente).getNombre();
				datos[contador][2] = personas.get(registroSiguiente).getApellido();
				datos[contador][3] = personas.get(registroSiguiente).getEmail();
				datos[contador][4] = personas.get(registroSiguiente).getGenero();
				datos[contador][5] = personas.get(registroSiguiente).getPais();
			}
			contador ++;
		}
		//Contador de páginas, dependiendo si hay una página final incompleta
		if (registrosPaginaFinal == 0)
			vista.getTextoRegistroTabla().setText(paginaActual + "/" + paginasTotales);
		else
			vista.getTextoRegistroTabla().setText(paginaActual + "/" + (paginasTotales + 1));

	}
	
	
	private void borrarRegistro() {
		
		int dni = (int) modelo.getValueAt(tabla.getSelectedRow(), 0);
		
		dao.borrarCampo(dni);		
	}

	





}
