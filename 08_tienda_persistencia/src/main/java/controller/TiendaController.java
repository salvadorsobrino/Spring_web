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

import model.Producto;
import service.TiendaService;

@CrossOrigin("*")
@Controller
public class TiendaController {
	@Autowired
	TiendaService ts;

	@PostMapping(value="Alta")
	public String insertar(@ModelAttribute Producto p) {
		ts.alta(p);
		return "alta";
	}

	@GetMapping(value="Buscador", produces = MediaType.APPLICATION_JSON_VALUE) // Peticion que llega al Controller 
	public @ResponseBody List<Producto> buscar(@RequestParam("seccion") String seccion, HttpServletRequest request) {
		//List<Producto> productos = ts.buscar(seccion);
		//request.setAttribute("productos", productos);
		return ts.buscar(seccion); 
	}
	
	@GetMapping(value="Eliminar")
	public String eliminar(@RequestParam("nombre") String nombre){
		ts.eliminar(nombre);
		return "eliminar";
	}
	
	@GetMapping(value="Modificar")
	public String eliminar(@RequestParam("nombre") String nombre, 
			@RequestParam("precio") Double precio){
		ts.modificar(nombre,precio);
		return "modificar";
	}

}
