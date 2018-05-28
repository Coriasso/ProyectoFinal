package proyecto.modelo;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplementacionDAO implements OperacionesDAO {

	@Override
	public List<PersonaDTO> listarDatos() {
		Connection c = Conexion.abrirConexion();
		List<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try {
			PreparedStatement pst = c.prepareStatement("Select *  from personas");
			ResultSet r = pst.executeQuery();


			while (r.next()) {

				if( r.getString(4).equals("Masculino"))
					personas.add(new PersonaDTO(r.getString(1), r.getString(2), r.getString(3), Genero.Masculino,r.getString(5),r.getInt(6)));
				else
					personas.add(new PersonaDTO(r.getString(1), r.getString(2), r.getString(3), Genero.Femenino,r.getString(5),r.getInt(6)));

			}

		} catch (SQLException | PersonasExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personas;
	}

	@Override
	public boolean introducirDatos(List<PersonaDTO> personas) {
		//insert into personas values ("Jorge", "Sanchez", "coriasso@outlook.es", "masculino", "11/07/1994", 77340070);
		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contadorLineas = 0; 
		try {
			PreparedStatement pst = c.prepareStatement("INSERT INTO personas VALUES (?,?,?,?,?,?);");
			for (int i = 0; i < personas.size(); i++) {
				pst.setString(1, personas.get(i).getNombre());
				pst.setString(2, personas.get(i).getApellido());
				pst.setString(3, personas.get(i).getEmail());
				pst.setObject(4, personas.get(i).getGenero());
				pst.setObject(5, personas.get(i).getFechaNacimiento());
				pst.setInt(6, personas.get(i).getDni());

				contadorLineas += pst.executeUpdate();

			}
			if (contadorLineas>0) confirmacion = true;
			System.out.printf("Se añadieron %d líneas%n", contadorLineas);
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean introducirDatos(PersonaDTO persona) {

		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false; 
		try {
			PreparedStatement pst = c.prepareStatement("INSERT INTO personas VALUES (?,?,?,?,?,?);");
			pst.setString(1, persona.getNombre());
			pst.setString(2, persona.getApellido());
			pst.setString(3, persona.getEmail());
			pst.setObject(4, persona.getGenero());
			pst.setObject(5, persona.getFechaNacimiento());
			pst.setInt(6, persona.getDni());
			if(pst.executeUpdate() < 0) confirmacion = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Conexion.cerrarConexion();
		return confirmacion;
	}

	@Override
	public boolean actualizarCampo(PersonaDTO persona) {
		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false; 
		try {
			PreparedStatement pst = c.prepareStatement("UPDATE personas SET nombre=?, apellido=?, email=?,genero=?,fechaNacimiento=? WHERE dni=?;");
			pst.setString(1, persona.getNombre());
			pst.setString(2, persona.getApellido());
			pst.setString(3, persona.getEmail());
			pst.setObject(4, persona.getGenero());
			pst.setObject(5, persona.getFechaNacimiento());
			pst.setInt(6, persona.getDni());
			if(pst.executeUpdate() < 0) confirmacion = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Conexion.cerrarConexion();
		return confirmacion;		

	}

	@Override
	public boolean actualizarCampo(List<PersonaDTO> personas) {
		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contadorLineas = 0; 
		try {
			PreparedStatement pst = c.prepareStatement("UPDATE personas SET nombre=?, apellido=?, email=?,genero=?,fechaNacimiento=? WHERE dni=?;");
			for (int i = 0; i < personas.size(); i++) {
				pst.setString(1, personas.get(i).getNombre());
				pst.setString(2, personas.get(i).getApellido());
				pst.setString(3, personas.get(i).getEmail());
				pst.setObject(4, personas.get(i).getGenero());
				pst.setObject(5, personas.get(i).getFechaNacimiento());
				pst.setInt(6, personas.get(i).getDni());

				contadorLineas += pst.executeUpdate();

			}
			if (contadorLineas>0) confirmacion = true;
			System.out.printf("Se modificaron %d líneas%n", contadorLineas);
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean borrarCampo(int dni)  {
		Connection c = Conexion.abrirConexion();
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
		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		int contador = 0;
		try {
			PreparedStatement pst = c.prepareStatement("Delete from personas where dni=?");
			for (int i = 0; i<dni.length;i++) {
				pst.setInt(1,dni[i]);
				contador += pst.executeUpdate();
			}
			if(contador>0)confirmacion = true;
			System.out.printf("Se borraron %d registros %n", contador);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return confirmacion;
	}

	@Override
	public boolean crearTabla() {
		Connection c = Conexion.abrirConexion();
		boolean confirmacion = false;
		String sql = ("CREATE TABLE `libreria`. ( `dni` INT NOT NULL , `nombre` VARCHAR(20) NOT NULL ,"
				+ " `apellido` VARCHAR(30) NOT NULL ,"
				+ " `email` VARCHAR(25) NULL ,"
				+ " `genero` VARCHAR(10) NULL ,"
				+ " `fechaNacimiento` VARCHAR(20) NULL ,"
				+ " PRIMARY KEY (`dni`)) ");
		try {
			Statement st = c.createStatement();
			st.executeUpdate(sql);
			confirmacion = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return confirmacion;
	}

}
