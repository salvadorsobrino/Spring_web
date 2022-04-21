package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Producto;

@Service 
public class TiendaServiceImpl implements TiendaService{

	@Autowired
	JdbcTemplate template;
	
	@Override
	public void alta(Producto p) {
		String sql = "insert into productos(nombre,seccion,precio,stock) values(?,?,?,?) ";
		template.update(sql, p.getNombre(),p.getSeccion(),p.getPrecio(),p.getStock());
	}

	@Override
	public void eliminar(String nombre) {
		String sql="delete from productos where nombre=?";
		template.update(sql, nombre);
		
	}

	@Override
	public void modificar(String nombre, Double precio) {
		String sql="update from productos set precio=? where nombre=?";
		template.update(sql, precio,nombre);
		
	}

	@Override
	public List<Producto> buscar(String seccion) {
		String sql = "select * from productos where seccion=?";
		//query(String sql, RowMapper<T> rowMapper, Object... args)
		//T	mapRow(ResultSet rs, int rowNum)
		//f es el numero de fila que utiliza Spring para cada fila
		return template.query(sql,
				(rs,f)->new Producto(rs.getInt("IdProducto"),
						rs.getString("nombre"),
						rs.getString("seccion"),
						rs.getDouble("precio"),
						rs.getInt("stock")),
				seccion);
	}

	@Override
	public Producto buscarProducto(int idProducto) {
		String sql = "select * from productos where idProducto=?";
		List<Producto> productos = template.query(sql,
				(rs,f)->new Producto(rs.getInt("IdProducto"),
						rs.getString("nombre"),
						rs.getString("seccion"),
						rs.getDouble("precio"),
						rs.getInt("stock")),
				idProducto);
		return productos.size()>0?productos.get(0):null;
	}

}
