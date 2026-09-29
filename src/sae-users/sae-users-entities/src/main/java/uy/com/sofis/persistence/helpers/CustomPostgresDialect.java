/*
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.persistence.helpers;

import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StandardBasicTypes;

/**
 * Funciones específicas para Postgres.
 *
 * @author Sofis Solutions
 */
public class CustomPostgresDialect extends org.hibernate.dialect.PostgreSQL94Dialect {

    /**
     * Incorporaciòn de la función ilike.
     */
    public CustomPostgresDialect() {
        super();
        registerFunction("ilike", new SQLFunctionTemplate(StandardBasicTypes.BOOLEAN, "?1 ilike ?2", false));
    }
}
