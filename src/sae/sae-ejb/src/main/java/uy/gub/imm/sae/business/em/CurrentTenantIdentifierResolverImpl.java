/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.gub.imm.sae.business.em;

import java.security.Principal;

import javax.ejb.SessionContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.jboss.logging.Logger;

import uy.gub.imm.sae.login.SAEPrincipal;

/**
 *
 * @author Santiago
 */
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    private static final Logger LOGGER = Logger.getLogger(CurrentTenantIdentifierResolverImpl.class.getName());
    
    @Override
    public String resolveCurrentTenantIdentifier() {
        try {
            Context initContext = new InitialContext();
            SessionContext session = (SessionContext) initContext.lookup("java:comp/EJBContext");
            Principal principal = session.getCallerPrincipal();
            if (principal instanceof SAEPrincipal) {
                SAEPrincipal saePrincipal = (SAEPrincipal) principal;
                return saePrincipal.getTenant();
            }
        } catch (NamingException nEx) {
            LOGGER.log(Logger.Level.FATAL, "Error al identificar el tenant", nEx);
        }
        catch (Exception ex) {
            LOGGER.log(Logger.Level.INFO, "Error al identificar el tenant", ex);
        }
        return "public";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
