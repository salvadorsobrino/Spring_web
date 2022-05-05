package converters;

import org.springframework.stereotype.Component;

import dtos.CuentaDto;
import dtos.MovimientoDto;
import model.Cuenta;
import model.Movimiento;
@Component //Notacion importante!
public class ConversorEntityDtoImpl implements ConversorEntityDto{
	
	@Override
	public CuentaDto cuentaToDto(Cuenta cuenta) {
		return new CuentaDto(cuenta.getNumeroCuenta(),cuenta.getSaldo(),cuenta.getTipoCuenta());
	}

	@Override
	public Cuenta dtoToCuenta(CuentaDto dto) {
		return new Cuenta(dto.getNumeroCuenta(),dto.getSaldo(),dto.getTipoCuenta());
	}

	@Override
	public MovimientoDto movimientoToDto(Movimiento movimiento) {
		return new MovimientoDto(movimiento.getIdMovimiento(),
				movimiento.getIdCuenta(),
				movimiento.getFecha(),
				movimiento.getCantidad(),
				movimiento.getOperacion());
	}

	@Override
	public Movimiento dtoToMovimiento(MovimientoDto dto) {
		return new Movimiento(dto.getIdMovimiento(),
				dto.getIdCuenta(),
				dto.getFecha(),
				dto.getCantidad(),
				dto.getOperacion());
	}
	
	
}
