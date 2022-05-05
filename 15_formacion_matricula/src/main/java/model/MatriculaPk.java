package model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@EqualsAndHashCode

/*
 * JavaBean que encapsula los datos de la clave primaria Debe sobrescribir
 * equals() y hashCode(). 
 * Anotada con @Embeddable. 
 * Java te obliga a que sea Serializable, convertir a 1 y 0 y se pueda reconstruir
 * guarda las PK en fisico. Los java Beans no. Hay que indicar que es Serializable, no tiene metodos.
 * Antiguamente se hacian las entidades Serializables (ya no es necesario). En el caso de esta clave pues si.
 */
@Embeddable
public class MatriculaPk implements Serializable{
	private int idCurso;
	private String usuario;
	
}
