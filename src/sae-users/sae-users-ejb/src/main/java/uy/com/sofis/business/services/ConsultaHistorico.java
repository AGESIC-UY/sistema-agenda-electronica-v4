/*
 * 
 * 
 */
package uy.com.sofis.business.services;

import java.lang.reflect.Field;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.exceptions.TechnicalException;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import uy.com.sofis.utils.ReflectionUtils;



@Stateless
@LocalBean
public class ConsultaHistorico<T> {

    @PersistenceContext
    private EntityManager em;

    /**
     * Deuvelve todos los objetos de un determinado tipo
     *
     * @param <T>
     * @param clase
     * @param id
     * @param campoVersion
     * @return
     * @throws GeneralException
     */
    public <T> List<T> obtenerHistorialPorId(Class clase, Long id) throws GeneralException {
        AuditReader reader = AuditReaderFactory.get(em);
        Field versionField = ReflectionUtils.obtenerCampoAnotado(clase, Version.class);
        List<T> respuesta = reader.createQuery().forRevisionsOfEntity(clase, true, true)
                .add(AuditEntity.id().eq(id))
                .addOrder(AuditEntity.property(versionField.getName()).desc())
                .getResultList();

        return respuesta;
    }
    

    /**
     * Devuelve un objeto en una determinada versión
     *
     * @param <T>
     * @param clase
     * @param version
     * @param id
     * @param campoVersion
     * @return
     */
    public <T> T obtenerEnVersion(Class clase, Long id, Integer version ) {
        try {
            AuditReader reader = AuditReaderFactory.get(em);
             Field versionField = ReflectionUtils.obtenerCampoAnotado(clase, Version.class);
            List<T> respuesta = reader.createQuery().forRevisionsOfEntity(clase, true, true)
                    .add(AuditEntity.id().eq(id))
                    .add(AuditEntity.property(versionField.getName()).eq(version))
                    .getResultList();
            if (respuesta != null && respuesta.size() > 0) {
                return respuesta.get(0);
            }
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
        return null;
    }


    public <T> T obtenerHistorico(T entidad, Integer estId, Date d, Number revision) {
        T respuesta = null;
        if (entidad != null && estId != null && revision != null) {
            AuditReader reader = AuditReaderFactory.get(em);
            Number prior_revision = (Number) reader.createQuery()
                    .forRevisionsOfEntity(entidad.getClass(), false, true)
                    .addProjection(AuditEntity.revisionNumber().max())
                    .add(AuditEntity.id().eq(estId))
                    .add(AuditEntity.revisionNumber().lt(revision))
                    .getSingleResult();
            if (prior_revision != null) {
                respuesta = (T) reader.find(entidad.getClass(), estId, prior_revision);
            }
        }
        return respuesta;
    }

   
    public Number obtenerRevision(Date fechaMod, AuditReader reader) {
        Number rev = 1;
        try {
            rev = reader.getRevisionNumberForDate(fechaMod);
        } catch (Exception ex) {
            //deja la primera
        }
        return rev;
    }
}
