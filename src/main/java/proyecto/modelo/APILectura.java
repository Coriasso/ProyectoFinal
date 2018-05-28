package proyecto.modelo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class APILectura {


	//Método para leer de un JSON simple
	public static ArrayList <PersonaDTO> leerJson(String url){

		File file = new File(url);
		ArrayList <PersonaDTO> personas = new ArrayList<>();
		ArrayList<String> jsonMoc = new ArrayList<>();

		try (Scanner sc = new Scanner(file);){

			//Se crea la instancia gson
			Gson gson = new GsonBuilder().create();

			//Leemos el fichero y vamos añadiendo cada nueva linea a una lista dinámica
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				jsonMoc.add(line);
			}

			//Creamos un objeto de la clase Persona con cada uno de esas lineas
			
				
			for (int i = 0; i < jsonMoc.size(); i++) {

				//Esta primera línea hace que si los items de la lisa JSON están separados por comas siga funcionando
				String objetoJson = jsonMoc.get(i).replaceAll("},", "}");

				//Creamos y añadimos los objetos de la clase Persona
				personas.add(gson.fromJson(objetoJson, PersonaDTO.class));

			}		



		} catch(FileNotFoundException e) {

			System.out.println("Este fichero no existe");

		}

		return personas;

	}


	public static boolean escribirJson(String url,ArrayList <PersonaDTO> personas ) {
		File file = new File(url);
		boolean confirmacion = false;
		//Se instancia un escritor con destino al fichero que queremos crear
		try (Writer writter = new FileWriter(file)){ 
			Gson gson = new GsonBuilder().create();


			//Se van escribiendo los objetos linea a linea segun vaya iterando el for. Se introduce un intro al final
			for (int i = 0; i < personas.size(); i++) {

				gson.toJson(personas.get(i), writter);
				writter.write("\n");
			}

			confirmacion = true;



		} catch (Exception e) {
			e.printStackTrace();

		}
		return confirmacion;
	}

}