package dao;
//Añadimos JpaRepository<Entidad, PK>

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Movimiento;

public interface MovimientosDao extends JpaRepository<Movimiento,Integer> {
	//@Query("select m from Movimiento m where m.idCuenta=?1 and m.fecha between ?2 and ?3")
	//List<Movimiento>movimientosCuenta(int numeroCuenta, Date fechaIni, Date fechaFin);
	//select m from Movimiento m where m.fecha between ?1 and ?2
	//No hace falta query gracias a la palabra reservada between, en este caso si para especificar la cuenta. 
	//DE LAS SIGUIENTE FORMA NO HACE FALTA:
	List<Movimiento>findByIdCuentaAndFechaBetween(int numeroCuenta, Date fechaIni, Date fechaFin);

}
