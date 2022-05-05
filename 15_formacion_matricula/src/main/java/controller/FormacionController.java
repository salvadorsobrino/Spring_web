package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dtos.AlumnoDto;
import dtos.CursoDto;
import dtos.MatriculaDto;
import model.Alumno;
import model.Curso;
import service.FormacionService;

@CrossOrigin("*")
@Controller
public class FormacionController {
	@Autowired
	FormacionService fs;

	@PostMapping(value = "Login")
	public String login(@RequestParam("usuario") String usuario, @RequestParam("password") String password,
			HttpSession session, HttpServletRequest request) {
		AlumnoDto alumno = fs.validarUsuario(usuario, password);
		if (alumno != null) {
			session.setAttribute("alumno", alumno);
			return "menu";
		} else {
			request.setAttribute("mensaje", "Usuario o contraseña incorrectos");
			return "login";
		}

	}

	@GetMapping(value = "AlumnosCursos", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al
																						// Controller
	public @ResponseBody List<AlumnoDto> alumnosCursos(@RequestParam("nombreCurso") String nombreCurso) {
		// HttpServletRequest request para guardar atributos en la respuesta
		return fs.alumnosCurso(nombreCurso);
	}

	@GetMapping(value = "CursosAlumnos", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al
																						// Controller
	public @ResponseBody List<CursoDto> cursosAlumnos(@RequestParam("nombre") String nombre) {
		return fs.obtenerCursos(nombre);
	}

	@GetMapping(value = "Alumnos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlumnoDto> alumnos() {
		return fs.listaAlumnos();
	}

	@GetMapping(value = "Cursos", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursos() {
		return fs.listaCursos();
	}
	/*
	 * Could not write JSON: failed to lazily initialize a collection of role:
	 * model.Alumno.cursos, could not initialize proxy - no Session; nested
	 * exception is com.fasterxml.jackson.databind.JsonMappingException: failed to
	 * lazily initialize a collection of role: model.Alumno.cursos, could not
	 * initialize proxy - no Session (through reference chain:
	 * java.util.ArrayList[0]->model.Alumno["cursos"])
	 */

	/*
	 * Cerró la sesion no ha podido trae los cursos (lazy). La solución no es eager,
	 * es cambiar un aspecto de la configuracion para que no cierre la sesion
	 * (solucion parcial) luego tendremos bucle infinito ServiceConfig
	 * props.put("hibernate.enable_lazy_load_no_trans", true); (SOLUCION PARCIAL)
	 */

	/*
	 * La solucion: hacer el JSON evitando que se manden los objetos de tipo
	 * coleccion que a su vez estan relacionados para asi evitar los bucles
	 * infinitos. Los datos de coleccion evitarlos transformar a JSON. Con una
	 * notacion de la libreria jackson que es la utiliza Spring @JsonIgnore delante
	 * del atributo
	 */
	@PostMapping(value = "AltaAlumno")
	public String insertarAlumno(@ModelAttribute AlumnoDto a) {
		return fs.altaAlumno(a)?"menu":"altaAlumno";
	}

	/*@PostMapping(value = "AltaCurso")
	public String insertarCurso(@ModelAttribute CursoDto c) {
		return fs.altaCurso(c)?"menu":"altaCurso";
	}*/

	@PostMapping(value = "AltaCurso")
	public String insertarCurso(@RequestParam("nombre") String nombre, 
			@RequestParam("duracion") int duracion,
			@RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
			@RequestParam("precio") double precio) {
		
		return fs.altaCurso(new CursoDto(nombre,duracion,d1,precio))?"menu":"altaCurso";
	}
	
	@GetMapping(value = "CursosNoMatriculados", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CursoDto> cursosNoMatriculados(@RequestParam("usuario") String usuario) {
		return fs.listaCursosNoMatriculados(usuario);
	}

	/*@GetMapping(value = "CursosEntreFechas", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Curso> cursosEntreFechas(@RequestParam("fecha1") String d1,
			@RequestParam("fecha2") String d2) {

		//SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); //ISO 8601 
		Date dateFormateada1;
		Date dateFormateada2;
		try {
			
			dateFormateada1 = formato.parse(d1);
			dateFormateada2 = formato.parse(d2);

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return fs.consultarMatriculas(dateFormateada1, dateFormateada2);

	}*/
	
	@GetMapping(value = "CursosEntreFechas", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MatriculaDto> cursosEntreFechas(@RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd")Date d2) {
		//@DateTimeFormat Se recogen como String los parametros pero al poner esta notacion se recoge como Date
		return fs.consultarMatriculas(d1, d2);
		
	}
	
	@PostMapping(value= "Matricular") 
	public @ResponseBody void matricular(@RequestParam("idCurso") int idCurso, @RequestParam("usuario") String usuario) {
		fs.matricularAlumno(usuario, idCurso);
		//return "menu"
	}

}
