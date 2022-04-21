package model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//delante de cada atributo cada getter y setter si de alguno no queremos
public class Producto {
	private int idProducto;
	private String nombre;
	private String seccion;
	private double precio;
	private int stock;
}
