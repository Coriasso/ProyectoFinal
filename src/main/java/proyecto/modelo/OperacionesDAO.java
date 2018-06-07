package proyecto.modelo;

import java.util.List;

public interface OperacionesDAO {
	
	

	public abstract List<PersonaDTO> listarDatos();
	
	public abstract List<PersonaDTO> listarDatos(String sql);
	
	public abstract boolean introducirDatos( List<PersonaDTO> personas);
	
	public abstract boolean introducirDatos( PersonaDTO persona);

	public abstract boolean actualizarCampo(PersonaDTO persona);
	
	public abstract boolean actualizarCampo(String[] datos);
	
	public abstract boolean actualizarCampo(List<PersonaDTO> personas);
	
	public abstract boolean borrarCampo(int dni);
	
	public abstract boolean borrarCampo(int[] dni);
	
	public abstract boolean crearTabla();
	
	public abstract boolean comprobarNumenoRegistros();
	
	public abstract List<PersonaDTO> listarRegistroConFiltro(List<String[]> filtro);
	
 }