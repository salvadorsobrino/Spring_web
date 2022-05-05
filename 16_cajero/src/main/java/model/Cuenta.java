package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//delante de cada atributo cada getter y setter si de alguno no queremos
@Entity //Entidad de datos que se va a mapear a una base de datos
@Table(name="cuentas") //Indica nombre de la tabla, a traves del atributo name


public class Cuenta {
	@Id //Primary key
	private int numeroCuenta;
	private double saldo;
	@Column(name ="tipocuenta") //Sirve para indicarle otro nombre a la columna, por defecto es el nombre dado
	private String tipoCuenta; 
	@JsonIgnore
	@OneToMany(mappedBy = "cuenta")
	private List<Movimiento> movimientos;

	public Cuenta(int numeroCuenta, double saldo, String tipoCuenta) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.saldo = saldo;
		this.tipoCuenta = tipoCuenta;
	}
	
	
}
