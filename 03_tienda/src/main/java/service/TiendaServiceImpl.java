package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import model.Producto;

@Service 
public class TiendaServiceImpl implements TiendaService{
	static ArrayList<Producto> productos=new ArrayList<>(List.of(new Producto("vino","alimentación",8.4,25),
			new Producto("leche","alimentación",1.2,70),
			new Producto("cable usb","informática",5.6,30),
			new Producto("arroz","alimentación",3.1,16),
			new Producto("silla","hogar",30.6,10),
			new Producto("monitor","informática",125.0,5),
			new Producto("escritorio","hogar",230.0,4)
			));

	@Override
	public void alta(Producto p) {
		productos.add(p);
		
	}

	@Override
	public void eliminar(String nombre) {
		Producto aux = buscarProducto(nombre);
		productos.remove(aux);
	}

	@Override
	public void modificar(String nombre, Double precio) {
		Producto aux = buscarProducto(nombre);
		aux.setPrecio(precio);
	}

	@Override
	public List <Producto> buscar(String seccion) {
		return productos
				.stream()
				.filter(p->p.getSeccion().equals(seccion))
				.collect(Collectors.toList());
		
	}
	
	public Producto buscarProducto(String nombre) {
		Producto aux = null;
		for (Producto p : productos) {
			if (p.getNombre().equals(nombre)){
				aux = p;
			}
		}
		return aux;
	}
	

}
