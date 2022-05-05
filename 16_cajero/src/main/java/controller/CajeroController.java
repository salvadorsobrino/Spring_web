package controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dtos.CuentaDto;
import dtos.MovimientoDto;
import service.CajeroService;

@CrossOrigin("*")
@Controller
public class CajeroController {
	@Autowired
	CajeroService cs;
	
	@PostMapping(value = "Login")
	public String login(@RequestParam("numeroCuenta") int numeroCuenta,
			HttpSession session, HttpServletRequest request) {
		
		CuentaDto cuenta = cs.validarCuenta(numeroCuenta);
		if (cuenta != null) {
			session.setAttribute("numeroCuenta", numeroCuenta);
			return "menu";
		} else {
			request.setAttribute("mensaje", "Numero de Cuenta incorrecto");
			return "login";
		}

	}
	@PostMapping(value = "Ingreso") // @ResponseBody ? no hay body
	public @ResponseBody void ingreso(@RequestParam("cantidad") double cantidad,
			HttpSession session) {
		int numeroCuenta = (int) session.getAttribute("numeroCuenta");
		cs.ingresar(numeroCuenta, cantidad);
		//return "menu";

	}
	@PostMapping(value = "Extraccion")
	public @ResponseBody void extraccion(@RequestParam("cantidad") double cantidad,
			HttpSession session) {
		int numeroCuenta = (int) session.getAttribute("numeroCuenta");
		cs.extraer(numeroCuenta, cantidad);
		//return "menu";

	}
	@PostMapping(value = "Transferencia")
	public @ResponseBody void transferencia(@RequestParam("cantidad") double cantidad,
			@RequestParam("numeroCuentaDestino") int numeroCuentaDestino,
			HttpSession session) {
		int numeroCuenta = (int) session.getAttribute("numeroCuenta");
		cs.transferir(numeroCuenta, cantidad, numeroCuentaDestino);
		//return "menu";

	}
	
	@GetMapping(value = "MovimientosEntreFechas", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MovimientoDto> movimientosEntreFechas(@RequestParam("fechaIni") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d1,
			@RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") Date d2,
			HttpSession session) {
		//@DateTimeFormat Se recogen como String los parametros pero al poner esta notacion se recoge como Date
		int numeroCuenta = (int) session.getAttribute("numeroCuenta");
		return cs.consultarMovimientos(numeroCuenta,d1, d2);
		
	}
	
	
	@GetMapping(value = "InformacionCuenta", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody CuentaDto informacionCuenta(HttpSession session) {
		//@DateTimeFormat Se recogen como String los parametros pero al poner esta notacion se recoge como Date
		int numeroCuenta = (int) session.getAttribute("numeroCuenta");
		return cs.validarCuenta(numeroCuenta);
		
	}


}
