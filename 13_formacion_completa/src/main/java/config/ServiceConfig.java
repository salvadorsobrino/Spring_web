package config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement //Habilitar la transaccionalidad mediante Anotaciones
@PropertySource(value = "classpath:config/application.properties")
@Configuration
@ComponentScan(basePackages = { "service" })
//Ya no scanea dao con JpaRepository ahora:
@EnableJpaRepositories(basePackages = { "dao" },entityManagerFactoryRef = "factory", transactionManagerRef = "txManager")
//Le especificamos el entityManagerFactoryRef y transactionManagerRef 
public class ServiceConfig {
	@Value("${driver}")
	String driver;
	@Value("${url}")
	String url;
	@Value("${usuario}")
	String usuario;
	@Value("${pwd}")
	String pwd;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource data = new DriverManagerDataSource();
		data.setDriverClassName(driver);
		data.setUrl(url);
		data.setUsername(usuario);
		data.setPassword(pwd);
		return data;
	}
	// Ahora utilizamos persistencia. DataSource si es necesario.
	/*
	 * @Bean public JdbcTemplate template(DataSource data) { return new
	 * JdbcTemplate(data); }
	 */

	// Adaptador de Hibernate, indicar que estamos trabajando con SQL
	@Bean
	public HibernateJpaVendorAdapter adapter() {
		HibernateJpaVendorAdapter adp = new HibernateJpaVendorAdapter();
		adp.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adp;
	}

	// Factoria EntityManager: Objeto para acceder a capa de persistencia con JPA
	// Configuracion de JPA
	@Bean
	public LocalContainerEntityManagerFactoryBean factory(DataSource dataSource, HibernateJpaVendorAdapter adapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setPersistenceUnitName("formacionPU"); //nombre que le damos a la unidad de persistencia (NO ES LA BBDD)
		factory.setDataSource(dataSource);
		factory.setPackagesToScan("model");
		/*Properties props = new Properties();
		props.put("hibernate.enable_lazy_load_no_trans", true); //SOLUCION PARCIAL
		factory.setJpaProperties(props);*/
		factory.setJpaVendorAdapter(adapter); //adaptador de Hibernate
		return factory;
	}

	// Gestor de transacción
	// Objeto que abre y cierra transacciones
	@Bean
	public JpaTransactionManager txManager(LocalContainerEntityManagerFactoryBean factory) {
		JpaTransactionManager manager = new JpaTransactionManager();
		manager.setEntityManagerFactory(factory.getObject());
		return manager;
	}

}
