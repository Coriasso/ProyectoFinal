package proyecto.vista;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.awt.Dimension;

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
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JSeparator;
import javax.swing.JInternalFrame;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class Vista extends JFrame {
	private JFrame frame;
	
	
	private JMenuItem MenuSalir;
	private JMenuItem MenuCargarArchivos;
	private JButton botonMas1;
	private JButton botonMenos1;
	private TextField textNombre;
	private JTextField registroActual;
	private TextField textApellido;
	private TextField textDni;
	private TextField textEmail;
	private TextField textGenero;
	private TextField textCiudad;
	private JButton botonMas10;
	private JButton botonMenos10;
	private JRadioButton botonMasculino;
	private JRadioButton botonFemenino;
	private JRadioButton botonAmbosGeneros;
	private JButton botonFiltrar;
	private JButton botonLimpiar;
	private ButtonGroup grupoBotonesMF;
	private List<String> extensionesEmail;
	private FlowLayout fl_panelBotones;
	private JComboBox<String> comboBox;
	private JButton btnBorrar;
	private JTextField textFiltroPais;
	private JButton botonCrear;
	private GroupLayout groupPanel;
	private JPanel panel_2;
	private JTable tablaCompleta;
	private JPanel panelBotonesTabla;
	private JButton botonTablaAvanzar;
	private JButton botonTablaRetroceder;
	private JPanel panelBotones;
	private JTextField textoRegistroTabla;
	private JButton botonBorrarTabla;
	private JButton botonTablaCrear;
	
	public Vista() {
		
		//Objeto tipo toolkit que trae distintas funcionalidades. Lo usaremos para conseguir el ancho y alto de la pantalla que se est� usando
	
		frame = new JFrame();
		frame.setSize(800,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Proyecto de programacion");
		
		//Sacar el tama�o de la pantalla
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//Esta linea coloca la ventana en el centro de la pantalla
		frame.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//Esta l�nea es necesaria para los equipos con m�s de un monitor
		frame.setLocationRelativeTo(null);
		
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
		splitRegistro.setResizeWeight(0.7);
		tabbedPane.addTab("Registro", null, splitRegistro, null);
		
		JPanel panel = new JPanel();
		splitRegistro.setRightComponent(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		Label labelNombre = new Label("Name");
		panel.add(labelNombre);
		
		textNombre = new TextField();
		panel.add(textNombre);
		
		Label labelApellido = new Label("Last Name");
		panel.add(labelApellido);
		
		textApellido = new TextField();
		panel.add(textApellido);
		
		Label labelDni = new Label("DNI");
		panel.add(labelDni);
		
		textDni = new TextField();
		panel.add(textDni);
		
		Label labelEmail = new Label("Email");
		panel.add(labelEmail);
		
		textEmail = new TextField();
		panel.add(textEmail);
				
		Label labelGenero = new Label("G�nero");
		panel.add(labelGenero);
		
		textGenero = new TextField();
		panel.add(textGenero);
		
		Label labelNacimiento = new Label("Pais");
		panel.add(labelNacimiento);
		
		textCiudad = new TextField();
		panel.add(textCiudad);
		
		
		panelBotones = new JPanel();
		panel.add(panelBotones);
		
		fl_panelBotones = new FlowLayout(FlowLayout.CENTER, 30, 5);
		fl_panelBotones.setAlignOnBaseline(true);
		panelBotones.setLayout(fl_panelBotones);
		
		botonMenos10 = new JButton("<<");
		botonMenos10.setEnabled(false);
		panelBotones.add(botonMenos10);
		
		botonMenos1 = new JButton("<");
		botonMenos1.setEnabled(false);
		panelBotones.add(botonMenos1);
		
		registroActual = new JTextField("0/0");
		registroActual.setEditable(false);
		registroActual.setHorizontalAlignment(JTextField.CENTER);
		panelBotones.add(registroActual);
		
		botonMas1 = new JButton(">");		
		botonMas1.setEnabled(false);
		panelBotones.add(botonMas1);
		
		botonMas10 = new JButton(">>");
		botonMas10.setEnabled(false);
		panelBotones.add(botonMas10);
		
		
		
		
		
		//Panel De la izquierda
		
		JPanel panel_1 = new JPanel();
		splitRegistro.setLeftComponent(panel_1);
		
		JLabel lblNewLabel = new JLabel("Filtros:");
		
		botonMasculino = new JRadioButton("Masculino");
		
		botonFemenino = new JRadioButton("Femenino");
		
		botonAmbosGeneros = new JRadioButton("Ambos");
		
		grupoBotonesMF = new ButtonGroup();
		
		grupoBotonesMF.add(botonFemenino);
		grupoBotonesMF.add(botonMasculino);
		grupoBotonesMF.add(botonAmbosGeneros);
		
		JLabel lblNewLabel_1 = new JLabel("Extension de Email");
		
		botonFiltrar = new JButton("Filtrar");
		
		botonLimpiar = new JButton("Limpiar");
		
		comboBox = new JComboBox();
		
		btnBorrar = new JButton("Borrar");

		
		
		JSeparator separator = new JSeparator();
		
		textFiltroPais = new JTextField();
		textFiltroPais.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Pais:");
		
		botonCrear = new JButton("Crear");
		
		
		
		groupPanel = new GroupLayout(panel_1);
		groupPanel.setHorizontalGroup(
			groupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupPanel.createSequentialGroup()
					.addGap(73)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(314, Short.MAX_VALUE))
				.addGroup(groupPanel.createSequentialGroup()
					.addGap(31)
					.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
							.addGroup(groupPanel.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addContainerGap(382, Short.MAX_VALUE))
							.addGroup(groupPanel.createSequentialGroup()
								.addGroup(groupPanel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
									.addComponent(comboBox, Alignment.LEADING, 0, 91, Short.MAX_VALUE))
								.addGap(416))
							.addGroup(groupPanel.createSequentialGroup()
								.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
									.addComponent(botonMasculino, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblCiudad, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
									.addGroup(groupPanel.createSequentialGroup()
										.addComponent(botonFemenino, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(botonAmbosGeneros, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE))
									.addComponent(textFiltroPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(233)))
						.addGroup(groupPanel.createSequentialGroup()
							.addGroup(groupPanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnBorrar)
								.addComponent(botonFiltrar))
							.addGroup(groupPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(groupPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(botonLimpiar))
								.addGroup(groupPanel.createSequentialGroup()
									.addGap(18)
									.addComponent(botonCrear)))
							.addGap(245))))
		);
		groupPanel.setVerticalGroup(
			groupPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(groupPanel.createSequentialGroup()
					.addGap(28)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonMasculino)
						.addComponent(botonFemenino)
						.addComponent(botonAmbosGeneros))
					.addGap(26)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCiudad)
						.addComponent(textFiltroPais, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(lblNewLabel_1)
					.addGap(18)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(44)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonFiltrar)
						.addComponent(botonLimpiar))
					.addGap(20)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(19)
					.addGroup(groupPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBorrar)
						.addComponent(botonCrear))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		panel_1.setLayout(groupPanel);
		
		panel_2 = new JPanel();
		
		panel_2.setEnabled(false);;
		tabbedPane.addTab("Tabla", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		tablaCompleta = new JTable();
		//panel_2.add(tablaCompleta, BorderLayout.CENTER);
		panel_2.add(new JScrollPane(tablaCompleta), BorderLayout.CENTER);
		
		panelBotonesTabla = new JPanel();
		panel_2.add(panelBotonesTabla, BorderLayout.SOUTH);
		
		botonTablaRetroceder = new JButton("<<");
		panelBotonesTabla.add(botonTablaRetroceder);
		
		textoRegistroTabla = new JTextField();
		textoRegistroTabla.setEditable(false);
		panelBotonesTabla.add(textoRegistroTabla);
		textoRegistroTabla.setColumns(10);
		
		botonTablaAvanzar = new JButton(">>");
		panelBotonesTabla.add(botonTablaAvanzar);
		
		botonBorrarTabla = new JButton("Borrar");
		botonBorrarTabla.setHorizontalAlignment(SwingConstants.LEADING);
		panelBotonesTabla.add(botonBorrarTabla);
		
		botonTablaCrear = new JButton("Nuevos registros");
		panelBotonesTabla.add(botonTablaCrear);
		
		
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

	public JTextField getRegistroActual() {
		return registroActual;
	}

	public void setRegistroActual(JTextField registroActual) {
		this.registroActual = registroActual;
	}

	public TextField getTextApellido() {
		return textApellido;
	}

	public void setTextApellido(TextField textApellido) {
		this.textApellido = textApellido;
	}

	public TextField getTextDni() {
		return textDni;
	}

	public void setTextDni(TextField textDni) {
		this.textDni = textDni;
	}

	public TextField getTextEmail() {
		return textEmail;
	}

	public void setTextEmail(TextField textEmail) {
		this.textEmail = textEmail;
	}

	public TextField getTextGenero() {
		return textGenero;
	}

	public void setTextGenero(TextField textGenero) {
		this.textGenero = textGenero;
	}

	public TextField getTextCiudad() {
		return textCiudad;
	}

	public JButton getBotonMas10() {
		return botonMas10;
	}

	public JButton getBotonMenos10() {
		return botonMenos10;
	}
	

	public JRadioButton getBotonMasculino() {
		return botonMasculino;
	}

	public JRadioButton getBotonFemenino() {
		return botonFemenino;
	}
	public JButton getBotonFiltrar() {
		return botonFiltrar;
	}

	public JButton getBotonLimpiar() {
		return botonLimpiar;
	}

	public ButtonGroup getGrupoBotonesMF() {
		return grupoBotonesMF;
	}

	public void setGrupoBotonesMF(ButtonGroup grupoBotonesMF) {
		this.grupoBotonesMF = grupoBotonesMF;
	}
	public void setExtensionesEmail(List<String> extensionesEmail) {
		this.extensionesEmail = extensionesEmail;
	}
	
	public List<String> getExtensionesEmail() {
		return extensionesEmail;
	}

	public JComboBox<String> getComboBox() {
		return comboBox;
	}
	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JTextField getTextFiltroPais() {
		return textFiltroPais;
	}

	public void setTextFiltropais(JTextField textFiltroCiudad) {
		this.textFiltroPais = textFiltroCiudad;
	}
	public JButton getBotonCrear() {
		return botonCrear;
	}
	public JRadioButton getBotonAmbosGeneros() {
		return botonAmbosGeneros;
	}
	public JTable getTablaCompleta() {
		return tablaCompleta;
	}
	public JButton getBotonTablaAvanzar() {
		return botonTablaAvanzar;
	}

	public JButton getBotonTablaRetroceder() {
		return botonTablaRetroceder;
	}
	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public JTextField getTextoRegistroTabla() {
		return textoRegistroTabla;
	}

	public JButton getBotonBorrarTabla() {
		return botonBorrarTabla;
	}
	public JPanel getPanel_2() {
		return panel_2;
	}
	public JButton getBotonTablaCrear() {
		return botonTablaCrear;
	}
	
	
}
