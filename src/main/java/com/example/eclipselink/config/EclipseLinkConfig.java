package com.example.eclipselink.config;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.orm.jpa.JpaBaseConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class EclipseLinkConfig extends JpaBaseConfiguration {

    protected EclipseLinkConfig(DataSource dataSource, JpaProperties properties, ObjectProvider<JtaTransactionManager> jtaTransactionManager) {
        super(dataSource, properties, jtaTransactionManager);
    }

    @Override
    protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
        EclipseLinkJpaVendorAdapter adapter = new EclipseLinkJpaVendorAdapter();
        adapter.setShowSql(true);
        adapter.setDatabasePlatform("org.eclipse.persistence.platform.database.PostgreSQLPlatform");
        return adapter;
    }

    @Override
    protected Map<String, Object> getVendorProperties() {
        return Map.of(
                PersistenceUnitProperties.WEAVING, "static",
                PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables",
                PersistenceUnitProperties.LOGGING_LEVEL, "FINE",
                PersistenceUnitProperties.LOGGING_PARAMETERS, "true");
    }
}
