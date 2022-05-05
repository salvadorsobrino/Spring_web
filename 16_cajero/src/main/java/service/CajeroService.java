package service;

import java.util.Date;
import java.util.List;

import dtos.CuentaDto;
import dtos.MovimientoDto;

public interface CajeroService {
	CuentaDto validarCuenta(int numeroCuenta);
	//Validar Cuenta
	List<MovimientoDto> consultarMovimientos(int numeroCuenta,Date f1,Date f2);
	//Devuelve una lista con los movimientos realizados en un rango de fechas
	
	void ingresar(int numeroCuenta, double cantidad);//El numero de cuenta lo tenemos guardado en un session
	void extraer(int numeroCuenta, double cantidad);
	void transferir(int numeroCuenta, double cantidad, int numeroCuentaDestino);
}
