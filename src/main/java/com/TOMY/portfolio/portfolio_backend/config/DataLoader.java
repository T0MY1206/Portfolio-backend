package com.TOMY.portfolio.portfolio_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;

@Component
public class DataLoader implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    private DataSource dataSource;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        loadData();
    }

    public void loadData() {
        try {
            // Verificar si ya hay datos en profiles
            Long count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM profiles", Long.class);
            
            if (count == null || count == 0) {
                System.out.println("Cargando datos iniciales desde data.sql...");
                
                ClassPathResource resource = new ClassPathResource("data.sql");
                EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
                
                try (Connection connection = dataSource.getConnection()) {
                    // Usar ScriptUtils de Spring que maneja mejor los scripts SQL
                    // continueOnError = true para que continúe aunque haya errores
                    ScriptUtils.executeSqlScript(connection, encodedResource, false, true, 
                        ScriptUtils.DEFAULT_COMMENT_PREFIX, 
                        ScriptUtils.DEFAULT_STATEMENT_SEPARATOR, 
                        ScriptUtils.DEFAULT_BLOCK_COMMENT_START_DELIMITER, 
                        ScriptUtils.DEFAULT_BLOCK_COMMENT_END_DELIMITER);
                }
                
                System.out.println("Datos iniciales cargados correctamente");
            } else {
                System.out.println("Los datos ya existen, omitiendo carga inicial");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar datos iniciales: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
