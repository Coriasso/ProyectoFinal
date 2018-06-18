package proyecto.modelo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplementacionDAO implements OperacionesDAO {

	private   Connection c = Conexion.abrirConexion();
	@Override
	public List<PersonaDTO> listarDatos() {
		boolean exito = false;
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			PreparedStatement pst = c.prepareStatement("Select *  from personas");
			ResultSet r = pst.executeQuery();

			//TODO normalizar las bases de datos
			while (r.next()) {

				if( r.getString(5).equals("Masculino"))
					personas.add(new PersonaDTO(r.getString(2), r.getString(3), r.getString(4), Genero.Masculino,r.getString(6),r.getInt(1)));
				else
					personas.add(new PersonaDTO(r.getString(2), r.getString(3), r.getString(4), Genero.Femenino,r.getString(6),r.getInt(1)));
			}
			exito = true;

		} catch (SQLException | PersonasExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logs.escribirLog("Select *  from personas", exito);

		return personas;
	}

	public List<PersonaDTO> listarDatos(String sql) {
		boolean exito = false;
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet r = pst.executeQuery();


			while (r.next()) {

				if( r.getString(5).equals("Masculino"))
					personas.add(new PersonaDTO(r.getString(2), r.getString(3), r.getString(4), Genero.Masculino,r.getString(6),r.getInt(1)));
				else
					personas.add(new PersonaDTO(r.getString(2), r.getString(3), r.getString(4), Genero.Femenino,r.getString(6),r.getInt(1)));

			}
			exito = true;
		} catch (SQLException | PersonasExcepcion e) {
			// TODO Auto-generated catch block
			System.out.println("Esta consulta no obtuvo resultados");
			
		}
		Logs.escribirLog("sql", exito);
		return personas;
	}


	@Override
	public boolean introducirDatos(List<PersonaDTO> personas) {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contadorLineas = 0; 
		try {
			PreparedStatement pst = c.prepareStatement("INSERT INTO personas VALUES (?,?,?,?,?,?);");
			for (int i = 0; i < personas.size(); i++) {
				pst.setInt(1, personas.get(i).getDni());
				pst.setString(2, personas.get(i).getNombre());
				pst.setString(3, personas.get(i).getApellido());
				pst.setString(4, personas.get(i).getEmail());
				pst.setObject(5, personas.get(i).getGenero());
				pst.setString(6, personas.get(i).getPais());


				contadorLineas += pst.executeUpdate();

			}
			if (contadorLineas>0) confirmacion = true;
			System.out.printf("%d nuevas lineas %n", contadorLineas);
			Logs.escribirLog(pst.toString(), confirmacion);
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confirmacion;
	}

	@Override
	public boolean introducirDatos(PersonaDTO persona) {

		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		try {
			PreparedStatement pst = c.prepareStatement("INSERT INTO personas VALUES (?,?,?,?,?,?);");
			pst.setInt(1, persona.getDni());
			pst.setString(2, persona.getNombre());
			pst.setString(3, persona.getApellido());
			pst.setString(4, persona.getEmail());
			pst.setObject(5, persona.getGenero());
			pst.setString(6, persona.getPais());
			if(pst.executeUpdate() < 0) confirmacion = true;
			Logs.escribirLog(pst.toString(), confirmacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return confirmacion;
	}

	@Override
	public boolean actualizarCampo(PersonaDTO persona) {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false; 
		try {
			PreparedStatement pst = c.prepareStatement("UPDATE personas SET nombre=?, apellido=?, email=?,genero=?,pais=? WHERE dni=?;");
			pst.setString(1, persona.getNombre());
			pst.setString(2, persona.getApellido());
			pst.setString(3, persona.getEmail());
			pst.setObject(4, persona.getGenero());
			pst.setString(5, persona.getPais());
			pst.setInt(6, persona.getDni());
			if(pst.executeUpdate() < 0) confirmacion = true;
			Logs.escribirLog(pst.toString(), confirmacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Conexion.cerrarConexion();
		return confirmacion;		

	}

	
	@Override
	public boolean actualizarCampo(String[] datos) {
		//Los datos llegan en el orden : numero de dni, nombre del campo editado, y valor nuevo
		
		try {
			
			//String sql = "UPDATE personas set ? = ? where dni = ?";
			String sql = "UPDATE personas set "+ datos[1] +"= '"+ datos[2] +"' where dni =" + datos[0];
			
			PreparedStatement pst = c.prepareStatement(sql);
		
			/*pst.setString(1, datos[1]);
			pst.setString(2, datos[2]);
			pst.setString(3,datos[0]);*/
			pst.executeUpdate();
			Logs.escribirLog(sql, true);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false; 
	}
	
	@Override
	public boolean actualizarCampo(List<PersonaDTO> personas) {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contadorLineas = 0; 
		try (PreparedStatement pst = c.prepareStatement("UPDATE personas SET nombre=?, apellido=?, email=?,genero=?,fechaNacimiento=? WHERE dni=?;");){
			for (int i = 0; i < personas.size(); i++) {
				pst.setString(1, personas.get(i).getNombre());
				pst.setString(2, personas.get(i).getApellido());
				pst.setString(3, personas.get(i).getEmail());
				pst.setObject(4, personas.get(i).getGenero());
				pst.setString(5, personas.get(i).getPais());
				pst.setInt(6, personas.get(i).getDni());

				contadorLineas += pst.executeUpdate();
				Logs.escribirLog(pst.toString(), true);
			}
			if (contadorLineas>0) confirmacion = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean borrarCampo(int dni)  {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		try {
			PreparedStatement pst = c.prepareStatement("Delete from personas where dni=?");
			pst.setInt(1,dni);
			if(pst.executeUpdate()>0)confirmacion = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean borrarCampo(int[] dni) {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contador = 0;
		try {
			PreparedStatement pst = c.prepareStatement("Delete from personas where dni=?");
			for (int i = 0; i<dni.length;i++) {
				pst.setInt(1,dni[i]);
				contador += pst.executeUpdate();
			}
			if(contador>0)confirmacion = true;
			Logs.escribirLog(pst.toString(), confirmacion);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean crearTabla() {
		//Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		String sql = ("CREATE TABLE personas ( `dni` INT(8) NOT NULL ,"
				+ " `nombre` VARCHAR(20) NOT NULL ,"
				+ " `apellido` VARCHAR(30) NOT NULL"
				+ " , `email` VARCHAR(25) NULL"
				+ " , `genero` VARCHAR(10) NULL"
				+ " , `pais` VARCHAR(15) NULL"
				+ " , PRIMARY KEY (`dni`));");
		try {
			Statement st = c.createStatement();
			st.executeUpdate(sql);
			confirmacion = true;
			Logs.escribirLog(sql, confirmacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	//Devolver� true si hay al menos un registro en la base de datos
	@Override
	public boolean comprobarNumenoRegistros() {
		boolean comprobacion = false;
		try {
			PreparedStatement spt = c.prepareStatement("SELECT count(*) FROM personas");
			ResultSet r = spt. executeQuery();
			//No me queda claro por que, pero hay que avanzar el resulset uno para que detecte la columna uno
			//en sqlite esto no era necesari
			r.next();
			if (r.getInt(1) > 0) comprobacion = true;

		} catch (SQLException e) {
			System.out.println("Database empty");

		}
		return comprobacion;
	}

	@Override
	public List<PersonaDTO> listarRegistroConFiltro(List<String[]> filtro) {
		List <PersonaDTO> personas = new ArrayList<>();
		String sql = "Select * from personas where " + filtro.get(0)[0] +" = '" + filtro.get(0)[1] + "' ";	
		//En caso de que el email es el �nico filtro
		if(filtro.get(0)[0].matches("email"))
			sql = "Select * from personas where " + filtro.get(0)[0] +" like '%" + filtro.get(0)[1] + "' ";

		if(filtro.size() == 1) 	
			personas = listarDatos(sql);		
		else {
			for (int i = 1; i < filtro.size(); i ++ ) {
				if(filtro.get(i)[0].matches("email"))
					sql += "AND " + filtro.get(i)[0] + " like '%" + filtro.get(i)[1] + "'";
				else
					sql += "AND " + filtro.get(i)[0] + " = '" + filtro.get(i)[1] + "'";

				Logs.escribirLog(sql, true);
			}
			personas = listarDatos(sql);
		}
		return personas;
	}

}
