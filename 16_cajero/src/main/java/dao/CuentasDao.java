package dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Cuenta;

//Añadimos JpaRepository<Entidad, PK>
public interface CuentasDao extends JpaRepository<Cuenta,Integer>{
	//Optional<T> findById(ID primaryKey);
	//Optional<Cuenta> findById(int numeroCuenta); No hace falta lo genera automaticamente.
	
	
	
}
