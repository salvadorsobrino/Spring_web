package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import service.AcademiaService;

@CrossOrigin("*")
@Controller
public class AcademiaController {
	@Autowired
	AcademiaService as;
	
	@PostMapping(value="Alta")
	public String insertar(@ModelAttribute Alumno a) {
		as.alta(a);
		return "alta";
	}
	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<Alumno> buscar(@RequestParam("curso") String curso, HttpServletRequest request) {
		return as.buscar(curso); 
	}
	@GetMapping(value="Cursos", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<String> cursos() {
		return as.buscarCursos(); 
	}
}
