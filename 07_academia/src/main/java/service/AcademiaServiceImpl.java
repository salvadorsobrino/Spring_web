package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import model.Alumno;

@Service 
public class AcademiaServiceImpl implements AcademiaService{
	
	@Autowired
	JdbcTemplate template;

	@Override
	public void alta(Alumno a) {
		if (buscarNombre(a)==null) {
			String sql = "insert into alumnos(nombre,curso,nota) values(?,?,?) ";
			template.update(sql, a.getNombre(),a.getCurso(),a.getNota());
		}

	}

	@Override
	public List<Alumno> buscar(String curso) {
		
		String sql = "select * from alumnos where curso=?";
		return template.query(sql,
				(rs,f)->new Alumno(rs.getInt("idAlumno"),
						rs.getString("nombre"),
						rs.getString("curso"),
						rs.getDouble("nota")),
				curso);
	}

	@Override
	public Alumno buscarNombre(Alumno a) {
		String sql = "select * from alumnos where nombre=?";
		List<Alumno> alumnos = template.query(sql,
				(rs,f)->new Alumno(rs.getInt("idAlumno"),
						rs.getString("nombre"),
						rs.getString("curso"),
						rs.getDouble("nota")),
				a.getNombre());
		return alumnos.size()>0?alumnos.get(0):null;
	}

	@Override
	public List<String> buscarCursos() {
		String sql = "select distinct(curso) from alumnos";
		return template.query(sql,
				(rs,f)->rs.getString("curso"));
	}

}
