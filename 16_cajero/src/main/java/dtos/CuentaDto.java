package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CuentaDto {
	private int numeroCuenta;
	private double saldo;
	private String tipoCuenta; 
	
	//private List<Movimiento> movimientos;

}
