package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "matriculas") 
/*La clave primaria de la entidad se define como un atributo de la clase Primary Key.
El atributo se registra con @EmbeddedId*/

public class Matricula {
	@EmbeddedId
    private MatriculaPk id;
    private double nota;
    
    @ManyToOne
    @JoinColumn(name = "idCurso",referencedColumnName = "idCurso", insertable = false, updatable = false) //Nombre de la PK y la FK coinciden en este caso
   //JoinColumn se refiere a las columnas de la BBDD
    Curso curso;
    @ManyToOne
    @JoinColumn(name = "usuario",referencedColumnName = "usuario" , insertable = false, updatable = false) 
    Alumno alumno;
	// insertable = false, updatable = false para la duplicidad ya que estan duplicados los atributos, las FK se ignoran
	public Matricula(double nota, Curso curso, Alumno alumno) {
		super();
		this.nota = nota;
		this.curso = curso;
		this.alumno = alumno;
	}
    
    
}
