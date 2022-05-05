package model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name="movimientos") //Indica nombre de la tabla, a traves del atributo name


public class Movimiento {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Llave primaria autogenerada , informa al framework que es una llave primaria autogenerada 
	private int idMovimiento;
	private int idCuenta;
	//@Temporal(TemporalType.DATE) //Obtener solo la fecha
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	private double cantidad;
	private String operacion;
	@JsonIgnore
    @ManyToOne()
    @JoinColumn(name="idCuenta", referencedColumnName = "numeroCuenta", insertable = false, updatable = false) //Nombre de la PK y la FK
	// insertable = false, updatable = false para la duplicidad ya que estan duplicados los atributos, las FK se ignoran
    private Cuenta cuenta;

	public Movimiento(int idMovimiento, int idCuenta, Date fecha, double cantidad, String operacion) {
		super();
		this.idMovimiento = idMovimiento;
		this.idCuenta = idCuenta;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.operacion = operacion;
	}

    

}
