package service;

import java.util.List;

import model.Producto;

//CRUD de Producto
public interface TiendaService {
	void alta(Producto p);
	void eliminar(String nombre);
	void modificar(String nombre,Double precio); 
	List <Producto> buscar(String seccion);
	Producto buscarProducto(int idProducto);
}
