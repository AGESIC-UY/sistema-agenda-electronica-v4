/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.gub.imm.sae.business.em;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;

/**
 * Simplisitc implementation for illustration purposes showing a single
 * connection pool used to serve multiple schemas using "connection altering".
 * Here we use the T-SQL specific USE command; Oracle users might use the ALTER
 * SESSION SET SCHEMA command; etc.
 */
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    private static final long serialVersionUID = -3709094721460244520L;

    private static final Logger LOGGER = Logger.getLogger(MultiTenantConnectionProviderImpl.class.getName());

    @Override
    public Connection getAnyConnection() throws SQLException {
        Context initContext;
        try {
            initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:/postgres-sae-ds");
            Connection conn = ds.getConnection();
            return conn;
        } catch (NamingException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo obtener una conexión", ex);
            return null;
        }

    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        try {
            connection.close();
        }catch(Exception ex) {
            LOGGER.log(Level.WARNING, "No se pudo cerrar una conexión", ex);
        }
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        Connection connection = null;
        Statement st = null;
        try {
            connection = getAnyConnection();
            st = connection.createStatement();
            st.execute("SET SEARCH_PATH TO '" + tenantIdentifier + "'");   
            return connection;
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "No se pudo obtener una conexión o cambiar de esquema", ex);
            if(connection != null) {
                try {
                    connection.close();
                }catch(Exception ex1) {
                    //Nada para hacer
                }
            }
            throw new HibernateException("No se pudo cambiar al esquema [" + tenantIdentifier + "]", ex);
        } finally {
            if(st != null) {
                try {
                    st.close();
                }catch(Exception ex) {
                    //Nada para hacer
                }
            }
        }        
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        releaseAnyConnection(connection);
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return ConnectionProvider.class.equals(unwrapType) || MultiTenantConnectionProvider.class.equals(unwrapType)
                || MultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (isUnwrappableAs(unwrapType)) {
            return (T) this;
        } else {
            throw new UnknownUnwrapTypeException(unwrapType);
        }
    }

}
