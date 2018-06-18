package proyecto.modelo;


import javax.swing.table.AbstractTableModel;

import proyecto.controlador.ControladorTabla;
import proyecto.vista.Vista;


@SuppressWarnings("serial")
public class ModeloTablaCreacion extends AbstractTableModel {
	
	private ImplementacionDAO dao;
	private String [] cabeceras =  {"dni", "nombre","apellido","email","genero","pais"};
	private Object[][] datos;
	private Vista vista;
	
	public ModeloTablaCreacion(Object[][] datos, ImplementacionDAO dao, Vista vista) {
		
		this.datos = datos;
		this.dao = dao; 
		this.vista = vista; 
		
		
	}
	
	@Override
	public int getColumnCount() {
		return cabeceras.length;
	}

	@Override
	public int getRowCount() {
		
		return 22;
	}

	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return cabeceras[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		return datos[rowIndex][columnIndex];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		
		return true;
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	
		//Metemos el dao escrito en la coleccion y repintamos la tabla
		//De esta forma el cambio se queda plasmado en ella
		datos[rowIndex][columnIndex] = aValue;
		vista.repaint();
		
	}




	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return super.getColumnClass(arg0);
	}

	public String[] getCabeceras() {
		return cabeceras;
	}
	
	

}
