package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Producto;
import service.TiendaService;

@Controller
public class TiendaController {
	@Autowired
	TiendaService ts;

	@PostMapping(value="Alta")
	public String insertar(@ModelAttribute Producto p) {
		ts.alta(p);
		return "alta";
	}

	@GetMapping(value="Buscador") // Spring sabe que cuando llegue una llamada con el final llamado Buscador
	public String buscar(@RequestParam("seccion") String seccion, HttpServletRequest request) {
		List<Producto> productos = ts.buscar(seccion);
		request.setAttribute("productos", productos);
		return "listado"; // Spring interpreta que es un JSP
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
