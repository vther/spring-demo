package com.vther.spring.data.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.vther.spring.data.jpa.dao")
@ComponentScan({"com.vther.spring.data.jpa"})
public class SpringConfiguration {

    @Bean
    public DataSource dataSource() {
        // 相比DERBY和HSQL，H2对语法的支持以及数据类型之类的与MYSQL的兼容都是最高，http://www.cnblogs.com/langtianya/p/3807573.html
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).setName("testdb")
                .addScript("classpath:sql/student.sql")
                .build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {
        final EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        final LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.vther.spring.data.jpa.entity");
        factory.getJpaPropertyMap().put("eclipselink.weaving", "false");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();
        //factory.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        //factory.setPersistenceUnitName("TEST_Persistence");
        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new JpaTransactionManager(entityManagerFactory());
    }

}
