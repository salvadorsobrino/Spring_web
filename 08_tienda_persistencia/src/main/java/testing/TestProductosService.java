package testing;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.ServiceConfig;
import service.TiendaService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ServiceConfig.class})

public class TestProductosService {
	@Autowired
	TiendaService service;
	
	@Test
	void testProducto() {
		assertEquals(67.0, service.buscarProducto(4).getPrecio());
		assertNull(service.buscarProducto(500));
	}
	
	@Test
	void testMedia() {
		assertEquals(90.0, service.precioMedioSeccion("hogar"));
	}
}
