package proyecto.modelo;

import java.util.Arrays;

import javax.swing.table.AbstractTableModel;

import proyecto.controlador.ControladorTabla;
import proyecto.vista.Vista;


@SuppressWarnings("serial")
public class ModeloTabla extends AbstractTableModel {
	
	private ImplementacionDAO dao;
	private String [] cabeceras =  {"dni", "nombre","apellido","email","genero","pais"};
	private Object[][] datos;
	private Vista vista;
	
	public ModeloTabla(Object[][] datos, ImplementacionDAO dao, Vista vista) {
		
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
		if (columnIndex == 0) return false;
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//Recogemos el valor actualizado, la columna y el dni
		//Los mandamos a actualizar si hay un cambio y repintamos la tabla
		String [] datosUpdate = new String [3];
		datosUpdate[0] = (datos[rowIndex][0]) + "";
		datosUpdate[1] = (String) cabeceras[columnIndex];
		datosUpdate[2] = (String) aValue;
		
		dao.actualizarCampo(datosUpdate);
		ControladorTabla.actualizarDatos();
		
		
	}

	public String[] getCabeceras() {
		return cabeceras;
	}
	
	
	public void borrarRegistro() {
		
	}

}
