package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Alumno;
import model.Curso;
import service.FormacionService;


@CrossOrigin("*")
@Controller
public class FormacionController {
	@Autowired
	FormacionService fs;
	
	@PostMapping(value="Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password, HttpSession session, HttpServletRequest request) {
		Alumno alumno = fs.validarUsuario(usuario, password);
		if (alumno!=null) {
			session.setAttribute("alumno", alumno);
			return "menu";
		} else {
			request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "login";
		}
		
	}
	@GetMapping(value="AlumnosCursos", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<Alumno> alumnosCursos(@RequestParam("nombreCurso") String nombreCurso) {
		//HttpServletRequest request para guardar atributos en la respuesta
		return fs.alumnosCurso(nombreCurso); 
	}
	@GetMapping(value="CursosAlumnos", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<Curso> cursosAlumnos(@RequestParam("nombre") String nombre) {
		return fs.obtenerCursos(nombre);
	}
	@GetMapping(value="Alumnos", produces = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody List<Alumno> alumnos() {
		return fs.listaAlumnos(); 
	}
	@GetMapping(value="Cursos", produces = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody List<Curso> cursos() {
		return fs.listaCursos(); 
	}
	/*Could not write JSON: failed to lazily initialize a collection of role: model.Alumno.cursos, 
	 * could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: 
	 * failed to lazily initialize a collection of role: model.Alumno.cursos, could not initialize proxy 
	 * - no Session (through reference chain: java.util.ArrayList[0]->model.Alumno["cursos"])*/
	
	
	/*Cerró la sesion no ha podido trae los cursos (lazy). La solución no es eager, 
	 * es cambiar un aspecto de la configuracion para que no cierre la sesion (solucion parcial) luego tendremos bucle infinito
	 * ServiceConfig props.put("hibernate.enable_lazy_load_no_trans", true); (SOLUCION PARCIAL) */
	
	/* La solucion: hacer el JSON evitando que se manden los objetos de tipo coleccion 
	 * que a su vez estan relacionados para asi evitar los bucles infinitos.
	 * Los datos de coleccion evitarlos transformar a JSON. 
	 * Con una notacion de la libreria jackson que es la utiliza Spring @JsonIgnore delante del atributo
	 * */
	
}
