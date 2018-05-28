package proyecto.modelo;

public class PersonaDTO {


	private String nombre;
	private String apellido;
	private String email;
	private Genero genero;
	private String fechaNacimiento;
	private int dni;
	
	public PersonaDTO(String nombre, String apellido, String email, Genero genero,
			String fechaNacimiento, int id) throws PersonasExcepcion {
		//TODO excepción
		if (nombre != null) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.dni= id;

		}
		else throw new PersonasExcepcion("Se debe suministrar el nombre siempre");
		
	}

	

	

	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApellido() {
		return apellido;
	}





	public void setApellido(String apellido) {
		this.apellido = apellido;
	}





	public String getEmail() {
		return email;
	}





	public void setEmail(String email) {
		this.email = email;
	}





	public Genero getGenero() {
		return genero;
	}





	public void setGenero(Genero genero) {
		this.genero = genero;
	}





	public String getFechaNacimiento() {
		return fechaNacimiento;
	}





	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}





	public int getDni() {
		return dni;
	}





	public void setDni(int dni) {
		this.dni = dni;
	}





	@Override
	public String toString() {
		return "Personas [nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + ", genero="
	+ genero + ", fechaNacimiento=" + fechaNacimiento + " , DNI=" + dni +"]";
	}
	
	
}
