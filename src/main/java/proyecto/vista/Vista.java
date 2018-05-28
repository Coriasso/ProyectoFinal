package proyecto.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class Vista extends JFrame {
	private JFrame frame;
	private JTable tablaCompleta;
	private JTextField textFechaFiltro;
	
	
	private JMenuItem MenuSalir;
	private JMenuItem MenuCargarArchivos;
	private TextField textNombre;
	private JButton botonMas1;
	private JButton botonMenos1;
	private TextField registroActual;
	public Vista() {
		
		//Objeto tipo toolkit que trae distintas funcionalidades. Lo usaremos para conseguir el ancho y alto de la pantalla que se esté usando
		Toolkit t = Toolkit.getDefaultToolkit();

		int height = t.getScreenSize().height;
        int width = t.getScreenSize().width;
		frame = new JFrame();
		//Aquí pondremos que la ventana tenga la cuarta parte del tamaño del monitor que se esté usando. La división entre 2 hace que aparezca centrado.
		frame.setBounds(width/4, height/4,width/2,height/2 );
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		
		frame.setJMenuBar(menuBar);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuBar.add(menuArchivo);
		
		MenuCargarArchivos = new JMenuItem("Cargar Archivos");
		menuArchivo.add(MenuCargarArchivos);
		
		MenuSalir = new JMenuItem("Salir");
		menuArchivo.add(MenuSalir);
		
		JMenu MenuAcercaDe = new JMenu("Acerca de");
		menuBar.add(MenuAcercaDe);
		
		JMenuItem MenuMostrarAcerca = new JMenuItem("Acerca de...");
		MenuAcercaDe.add(MenuMostrarAcerca);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		
		//JSplit con los registros individuales
		
		JSplitPane splitRegistro = new JSplitPane();
		splitRegistro.setResizeWeight(0.6);
		tabbedPane.addTab("Registro", null, splitRegistro, null);
		
		JPanel panel = new JPanel();
		splitRegistro.setRightComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Label labelNombre = new Label("Nombre");
		panel.add(labelNombre);
		
		textNombre = new TextField();
		panel.add(textNombre);
		
		Label labelApellido = new Label("Apellido");
		panel.add(labelApellido);
		
		TextField textApellido = new TextField();
		panel.add(textApellido);
		
		Label labelTarjeta = new Label("Número de tarjeta");
		panel.add(labelTarjeta);
		
		TextField textTarjeta = new TextField();
		panel.add(textTarjeta);
		
		Label labelCiudad = new Label("Ciudad de residencia");
		panel.add(labelCiudad);
		
		TextField textCiudad = new TextField();
		panel.add(textCiudad);
		
		Label labelGenero = new Label("Género");
		panel.add(labelGenero);
		
		TextField textGenero = new TextField();
		panel.add(textGenero);
		
		Label labelNacimiento = new Label("Fecha de nacimiento");
		panel.add(labelNacimiento);
		
		TextField textNacimiento = new TextField();
		panel.add(textNacimiento);
		
		
		JPanel panelBotones = new JPanel();
		panel.add(panelBotones);
		FlowLayout fl_panelBotones = new FlowLayout(FlowLayout.CENTER, 5, 5);
		fl_panelBotones.setAlignOnBaseline(true);
		panelBotones.setLayout(fl_panelBotones);
		
		JButton botonMenos10 = new JButton("<<");
		panelBotones.add(botonMenos10);
		
		botonMenos1 = new JButton("<");
		panelBotones.add(botonMenos1);
		
		registroActual = new TextField();
		registroActual.setEditable(false);
		panelBotones.add(registroActual);
		
		botonMas1 = new JButton(">");
		panelBotones.add(botonMas1);
		
		JButton botonMas10 = new JButton(">>");
		botonMas10.setEnabled(true);
		panelBotones.add(botonMas10);
		
		JPanel panel_1 = new JPanel();
		splitRegistro.setLeftComponent(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chkEditar = new JCheckBox("Habilitar editado");
		chkEditar.setBounds(6, 7, 407, 23);
		panel_1.add(chkEditar);
		
		JButton btnGuardarCambios = new JButton("Guardar cambios");
		btnGuardarCambios.setBounds(10, 380, 119, 23);
		panel_1.add(btnGuardarCambios);
		
		JButton botonDeshacer = new JButton("Deshacer cambios");
		botonDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botonDeshacer.setBounds(139, 380, 119, 23);
		panel_1.add(botonDeshacer);
		
		JButton btnLimpiarDatos = new JButton("Limpiar datos");
		btnLimpiarDatos.setBounds(269, 380, 119, 23);
		panel_1.add(btnLimpiarDatos);
		
		JFormattedTextField textoFiltros = new JFormattedTextField();
		textoFiltros.setEditable(false);
		textoFiltros.setText("Filtros");
		textoFiltros.setBounds(10, 50, 47, 20);
		panel_1.add(textoFiltros);
		
		
		String[] generos = {"Masculino", "Femenino"};
		
		JComboBox comboBoxGenero = new JComboBox(generos);
		comboBoxGenero.setToolTipText("");
		comboBoxGenero.setBounds(10, 90, 107, 20);
		panel_1.add(comboBoxGenero);
		
		JLabel labelCiudadFiltro = new JLabel("Ciudad de residencia:");
		labelCiudadFiltro.setBounds(10, 134, 107, 14);
		panel_1.add(labelCiudadFiltro);
		
		textFechaFiltro = new JTextField();
		textFechaFiltro.setBounds(127, 131, 119, 20);
		panel_1.add(textFechaFiltro);
		textFechaFiltro.setColumns(10);
		
		JRadioButton rdbtnMayorDeEdad = new JRadioButton("Mayor de edad");
		rdbtnMayorDeEdad.setBounds(10, 179, 109, 23);
		panel_1.add(rdbtnMayorDeEdad);
		
		JButton btnCargarDatos = new JButton("Cargar Datos");
		btnCargarDatos.setBounds(269, 225, 119, 23);
		panel_1.add(btnCargarDatos);
		
		//Fin del JSplit
		
		
		
		
		
		tablaCompleta = new JTable();
		tabbedPane.addTab("Tabla", null, tablaCompleta, null);
		
		
		
	}

	public JFrame getFrame() {
		return frame;
	}

	public JMenuItem getMenuSalir() {
		return MenuSalir;
	}

	public JMenuItem getMenuCargarArchivos() {
		return MenuCargarArchivos;
	}

	public TextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(TextField textNombre) {
		this.textNombre = textNombre;
	}
	
	public JButton getBotonMas1() {
		return botonMas1;
	}

	public void setBotonMas1(JButton botonMas1) {
		this.botonMas1 = botonMas1;
	}

	public JButton getBotonMenos1() {
		return botonMenos1;
	}

	public void setBotonMenos1(JButton botonMenos1) {
		this.botonMenos1 = botonMenos1;
	}

	public TextField getRegistroActual() {
		return registroActual;
	}

	public void setRegistroActual(TextField registroActual) {
		this.registroActual = registroActual;
	}
	
}
