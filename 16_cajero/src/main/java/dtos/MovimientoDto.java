package dtos;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovimientoDto {
	private int idMovimiento;
	private int idCuenta;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fecha;
	private double cantidad;
	private String operacion;
	
	public MovimientoDto(int idCuenta, Date fecha, double cantidad, String operacion) {
		super();
		this.idCuenta = idCuenta;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}
	
	
	
}
