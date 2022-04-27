package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import model.Producto;

@Service
public class TiendaServiceImpl implements TiendaService {

	/*
	 * Antes mediante: @Autowired JdbcTemplate template;
	 */

	// Ahora utilizando JPA javax.persistence
	// PersistenceContext es como Autowired es para que Spring te lo integre, ya
	// esta preparado para la bbdd

	@PersistenceContext
	EntityManager entityManager;

	@Transactional(propagation = Propagation.REQUIRED) //Por defecto es required. Admite una transacción actual, crea una nueva si no existe ninguna. 
	// Utilizamos la de Spring NO la de javax.persistence
	@Override
	public void alta(Producto p) {
		entityManager.persist(p); // Entidad(no columnas) y Objeto. Abre y cierra una transaccion.
									// JpaTransactionManager

	}

	@Transactional
	@Override
	public void eliminar(String nombre) { // Elimina objeto, no por nombre. EntityManager.remove
		/*
		 * Las consultas son gestionadas a través de un objeto de la interfaz Query o
		 * TypedQuery, obtenido a partir de EntityManager select alias from Entidad
		 * alias where condicion delete from Entidad alias where condicion update
		 * Entidad alias set alias.atributo=valor
		 */

		String jpql = "delete from Producto p where p.nombre=:nombre";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter("nombre", nombre);
		qr.executeUpdate();
		// int executeUpdate(). Ejecuta la consulta cuando se trata de una instrucción
		// de acción (Delete o Update). Devuelve el numero de entidades afectadas.

	}
	
	@Transactional
	@Override
	public void modificar(String nombre, Double precio) {
		// TODO Auto-generated method stub
		//update Empleado e set e.salario=e.salario*1.05 
		String jpql = "update from Producto p set p.precio=:precio where p.nombre=:nombre";
		Query qr = entityManager.createQuery(jpql);
		qr.setParameter("precio", precio);
		qr.setParameter("nombre", nombre);
		qr.executeUpdate();
		
		/*
		 * Producto producto = buscarProducto(nombre);
		 * if (producto != null){
		 * 		producto.setPrecio(nuevoPrecio);
		 * 		entityManager.merge(producto); //Actualizacion
		 * }
		 * 
		 * */


	}

	@Override
	public List<Producto> buscar(String seccion) {
		// String jpql="select p from Producto p where p.seccion=:seccion";
		// Query qr=entityManager.createQuery(jpql);
		// qr.setParameter("seccion", seccion);
		// casting al tipo de colección específica
		// List<Producto> productos = (List<Producto>) qr.getResultList();
		
		//Implementar @NamedQuery(name="Producto.findBySeccion", query="select p from Producto p where p.seccion=:seccion")
		//TypedQuery<Producto> qr = entityManager.createNamedQuery("Producto.findBySeccion", Producto.class);
		//qr.setParameter("seccion", seccion);
		
		String jpql = "select p from Producto p where p.seccion=:seccion";
		TypedQuery<Producto> qr = entityManager.createQuery(jpql, Producto.class);
		qr.setParameter("seccion", seccion);
		// No hay que hacer casting
		List<Producto> productos = qr.getResultList();

		return productos;
	}

	@Override
	public Producto buscarProducto(int idProducto) {
		return entityManager.find(Producto.class, idProducto);
	}

	@Override
	public double precioMedioSeccion(String seccion) {
		String jpql="select avg(p.precio) from Producto p where p.seccion=:sec";
		TypedQuery<Double> qr = entityManager.createQuery(jpql, Double.class);
		qr.setParameter("sec", seccion);
		return qr.getSingleResult();
	}

}
