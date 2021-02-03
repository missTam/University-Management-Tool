package de.academy.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

/* SpringAppConfig  contains the configuration for the various beans */

@Configuration
// Add support for @Controller-annotated classes that use @RequestMapping to map incoming requests */
@EnableWebMvc
// Enable transactional behavior based on annotations; @Transactional eliminates coding for manually starting and stopping transactions  */
@EnableTransactionManagement
// Scan for components, recursively for inversion control and dependency injection
@ComponentScan(basePackages = "de.academy")
@PropertySource("classpath:persistence-mysql.properties")
public class WebConfig implements WebMvcConfigurer {

    // set up variable to hold the properties; will use to read configs for JDBC, Hibernate and connection pooling
    @Autowired
    private Environment env;

    // set up a logger for diagnostics
    private Logger logger = Logger.getLogger(getClass().getName());

    // Define Spring MVC view resolver that builds the final view page URL
    @Bean
    public ViewResolver viewResolver() {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    // Define Datasource bean; use env to get data;
    @Bean
    public DataSource getDataSource() {

        // create connection pool (with c3p0)
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        // set the jdbc driver
        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // log url and user
        logger.info("jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info("jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props
        dataSource.setInitialPoolSize(
                getIntegerProperty("connection.pool.initialPoolSize"));

        dataSource.setMinPoolSize(
                getIntegerProperty("connection.pool.minPoolSize"));

        dataSource.setMaxPoolSize(
                getIntegerProperty("connection.pool.maxPoolSize"));

        dataSource.setMaxIdleTime(
                getIntegerProperty("connection.pool.maxIdleTime"));

        return dataSource;
    }

    // read environment property and convert to int
    private int getIntegerProperty(String property) {
        return Integer.parseInt(env.getProperty(property));
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties properties = new Properties();

        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

        return properties;
    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {

        // Setup Hibernate session factory - creates sessions to talk to db
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

        // set the properties - needs data source/ hibernate properties
        sessionFactory.setDataSource(getDataSource());
        sessionFactory.setPackagesToScan(env.getProperty("hiberante.packagesToScan"));
        sessionFactory.setHibernateProperties(getHibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {

        // setup transaction manager based on session factory
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }

    /* Implement WebMvcConfigurer to hook into resource handler
    ResourceHandlerRegistry has resource handlers for serving static resources such as
    images, css files and others through Spring MVC */

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    /* Define Encoder for password hashing */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}