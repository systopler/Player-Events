package ru.moa.player.events.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import ru.moa.player.events.util.PropertyUtil;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(
        {
                "ru.moa.player.events.service"
        }
)
@PropertySource("classpath:application.properties")
public class AppConfig  {

    @Autowired
    private Environment env;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        ResourceBundleMessageSource commonMessageSource = new ResourceBundleMessageSource();
        commonMessageSource.setBasename("common_messages");
        commonMessageSource.setDefaultEncoding("UTF-8");
        messageSource.setParentMessageSource(commonMessageSource);
        return messageSource;
    }

    @Bean
    public DataSource dataSource() {
        //Properties properties = PropertyUtil.getPropertiesByPrefix(env,"spring.datasource");
        //HikariConfig config = new HikariConfig(properties);
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(env.getProperty("spring.datasource.url"));
        config.setUsername(env.getProperty("spring.datasource.username"));
        config.setPassword(env.getProperty("spring.datasource.password"));
        config.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        return new HikariDataSource(config);
    }

    @Bean
    public JpaVendorAdapter hibernateJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setPrepareConnection(false);
        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        DataSource ds = this.dataSource();
        em.setDataSource(ds);
        em.setPackagesToScan("ru.moa.player.events.db.entity");
        JpaVendorAdapter jva = this.hibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(jva);
        Properties properties = PropertyUtil.getPropertiesByPrefix(env, "hibernate.properties");
        // settings for EHcache (using where parent context has a type name - RootConfig)

        em.setJpaProperties(properties);
        em.afterPropertiesSet();
        return em.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(this.entityManagerFactory());
        return tm;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db-change-log.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
}
