package model;
//JavaBean de Producto

public class Producto {
	
	private String nombre;
	private String seccion;
	private Double precio;
	private int stock;
	
	public Producto(String nombre, String seccion, Double precio, int stock) {
		super();
		this.nombre = nombre;
		this.seccion = seccion;
		this.precio = precio;
		this.stock = stock;

	}
	public Producto() { //Para versiones antiguas, ahora añade por parametros
		
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
