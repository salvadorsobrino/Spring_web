package service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converters.ConversorEntityDto;
import dao.CuentasDao;
import dao.MovimientosDao;
import dtos.CuentaDto;
import dtos.MovimientoDto;

@Service
public class CajeroServiceImpl implements CajeroService{
	
	@Autowired
	ConversorEntityDto conversor;
	
	CuentasDao cuentasDao;
	
	MovimientosDao movimientosDao;
	
	public CajeroServiceImpl(@Autowired CuentasDao cuentaDao, @Autowired MovimientosDao movimientosDao) {
		this.cuentasDao = cuentaDao;
		this.movimientosDao = movimientosDao;
	}
	@Override
	public CuentaDto validarCuenta(int numeroCuenta) {
		return conversor.cuentaToDto(cuentasDao.findById(numeroCuenta).get());
	}
	
	@Override
	public List<MovimientoDto> consultarMovimientos(int numeroCuenta,Date f1, Date f2) {
		// TODO Auto-generated method stub
		return movimientosDao.findByIdCuentaAndFechaBetween(numeroCuenta,f1, f2)
				.stream()
				.map(m->conversor.movimientoToDto(m))
				.collect(Collectors.toList());
	}
	@Override
	public void ingresar(int numeroCuenta, double cantidad) {
		CuentaDto cuenta = conversor.cuentaToDto(cuentasDao.findById(numeroCuenta).get());
		cuenta.setSaldo(cuenta.getSaldo()+cantidad);
		MovimientoDto movimiento = new MovimientoDto(numeroCuenta,new Date(),cantidad,"ingreso");
		movimientosDao.save(conversor.dtoToMovimiento(movimiento));
		cuentasDao.save(conversor.dtoToCuenta(cuenta));
	}
	@Override
	public void extraer(int numeroCuenta, double cantidad) {
		CuentaDto cuenta = conversor.cuentaToDto(cuentasDao.findById(numeroCuenta).get());
		cuenta.setSaldo(cuenta.getSaldo()-cantidad);
		MovimientoDto movimiento = new MovimientoDto(numeroCuenta,new Date(),cantidad,"extracción");
		movimientosDao.save(conversor.dtoToMovimiento(movimiento));
		cuentasDao.save(conversor.dtoToCuenta(cuenta));
		
	}
	@Override
	public void transferir(int numeroCuenta, double cantidad, int numeroCuentaDestino) {
		extraer(numeroCuenta,cantidad);
		ingresar(numeroCuentaDestino,cantidad);
		
	}
	

}
