package model;



import javax.persistence.Entity; //Todos son oficial de JPA
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table; 

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter //delante de cada atributo cada getter y setter si de alguno no queremos

@Entity //Entidad de datos que se va a mapear a una base de datos
@Table(name="productos") //Indica nombre de la tabla, a traves del atributo name
@NamedQuery(name="Producto.findBySeccion", query="select p from Producto p where p.seccion=:seccion")
public class Producto {
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Llave primaria autogenerada , informa al framework que es una llave primaria autogenerada 
	private int idProducto;
	private String nombre;
	@Column(name="seccion") //Sirve para indicarle otro nombre a la columna, por defecto es el nombre dado
	private String seccion;
	private double precio;
	private int stock;
}
