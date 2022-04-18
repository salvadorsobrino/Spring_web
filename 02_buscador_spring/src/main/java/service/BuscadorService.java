package service;
import model.Pagina;

import java.util.List;

public interface BuscadorService {
	List <Pagina> buscar(String tematica);
	void insertar(Pagina p);
}
