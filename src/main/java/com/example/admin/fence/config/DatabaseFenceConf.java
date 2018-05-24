package com.example.admin.fence.config;

import com.example.admin.fence.parser.TheatreParser;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.example.admin.fence.repository")
@EnableTransactionManagement
@ComponentScan("com.example.admin.fence")
@PropertySource("classpath:db.properties")
public class DatabaseFenceConf {


    @Resource
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(env.getRequiredProperty("db.entity.package"));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(getHibernateProperities());
        return em;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    private Properties getHibernateProperities() {
        try {
            Properties prop = new Properties();
            InputStream is = getClass().getClassLoader().getResourceAsStream("hibernate.properties");
            prop.load(is);

            return prop;
        }
        catch (Exception e){
            throw new IllegalArgumentException("can't find hibernate properties",e);
        }
    }



    @Bean
    public DataSource dataSource(){

        BasicDataSource bds = new BasicDataSource();
        bds.setUrl(env.getRequiredProperty("db.fence.url"));
        bds.setDriverClassName(env.getRequiredProperty("db.driver"));
        bds.setUsername(env.getRequiredProperty("db.username"));
        bds.setPassword(env.getRequiredProperty("db.password"));
        bds.setInitialSize(Integer.parseInt(env.getRequiredProperty("db.initialSize")));
        bds.setMaxIdle(Integer.parseInt(env.getRequiredProperty("db.maxIdle")));
        bds.setMinIdle(Integer.parseInt(env.getRequiredProperty("db.minIdle")));
        bds.setTimeBetweenEvictionRunsMillis(Long.valueOf(env.getRequiredProperty("db.timeBetweenEvictionRunsMillis")));
        bds.setMinEvictableIdleTimeMillis(Long.valueOf(env.getRequiredProperty("db.minEvictableIdleTimeMillis")));
        bds.setTestOnBorrow(Boolean.valueOf(env.getRequiredProperty("db.testOnBorrow")));
        bds.setValidationQuery(env.getRequiredProperty("db.validationQuerty"));


        return bds;
    }


}
