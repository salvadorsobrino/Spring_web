package converters;


import dtos.CuentaDto;
import dtos.MovimientoDto;
import model.Cuenta;
import model.Movimiento;

public interface ConversorEntityDto {
	public CuentaDto cuentaToDto(Cuenta cuenta);
	public Cuenta dtoToCuenta(CuentaDto dto);
	public MovimientoDto movimientoToDto(Movimiento movimiento);
	public Movimiento dtoToMovimiento(MovimientoDto dto);
}
