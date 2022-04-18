package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Pagina;
import service.BuscadorService;

@Controller
public class BuscadorController {
	
	@Autowired
	BuscadorService buscadorService;
	@GetMapping(value="Buscador") //Spring sabe que cuando llegue una llamada con el final llamado Buscador
	public String buscar(@RequestParam("tema") String tematica, HttpServletRequest request) {
		List<Pagina> paginas = buscadorService.buscar(tematica);
		request.setAttribute("paginas", paginas);
		return "listado"; //Spring interpreta que es un JSP 
		//En el form datos se llama tema no tematica
	}
	
	/*@PostMapping(value="Insertar")
	public String insertar(@RequestParam("direccion") String direccion,
			@RequestParam("tematica") String tematica,
			@RequestParam("descripcion") String descripcion) {
		
		Pagina p = new Pagina (direccion, tematica, descripcion);
		buscadorService.insertar(p);
		return "datos";
	}*/
	@PostMapping(value="Insertar")
	//Te vuelca los datos en el objeto. Los nombres de los parametros deben coincidir.TEMATICA - TEMA NO
	//Hace falta constructor vacio
	public String insertar(@ModelAttribute Pagina p) {
		buscadorService.insertar(p);
		return "datos";
	}
}
