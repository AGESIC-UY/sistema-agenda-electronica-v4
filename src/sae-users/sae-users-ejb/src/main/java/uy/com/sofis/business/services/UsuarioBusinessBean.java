/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.services;



import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.HashSet;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import uy.com.sofis.entities.Usuario;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import uy.com.sofis.business.validaciones.UsuarioBusinessValidation;
import uy.com.sofis.persistencia.daos.CodigueraDAO;
import uy.com.sofis.exceptions.BusinessException;
import uy.com.sofis.exceptions.TechnicalException;
import javax.inject.Inject;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.constantes.ConstantesErrores;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.constantes.Constantes;
import uy.com.sofis.entities.Organismo;
import uy.com.sofis.filtros.FiltroUsuario;
import uy.com.sofis.persistence.helpers.PersistenceHelper;
import uy.com.sofis.persistencia.daos.UsuarioDAO;
import uy.com.sofis.tipos.SgMail;
import uy.com.sofis.utils.EncriptarPasswordUtils;



/**
 *
 * @author Sofis Solutions
 */
@Stateless
@LocalBean
public class UsuarioBusinessBean  {

    private static final Logger LOGGER = Logger.getLogger(UsuarioBusinessBean.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Inject
    private ConsultaHistorico ch;
    
    @Inject
    private MailSender mailSender;


	 /**
     * Devuelve el objeto del tipo que tiene el id indicado
     * @param id
     * @return
     * @throws GeneralException
     */
    public Usuario obtenerPorId(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Usuario> codDAO = new CodigueraDAO<>(em, Usuario.class);
                return codDAO.obtenerPorId(id);
            } catch (Exception ex) {
                throw new TechnicalException(ex);
            }
        }
        return null;
    }
    
     /**
     * Devuelve el objeto del tipo que tiene el código indicado
     * @param codigo
     * @return Usuario
     * @throws GeneralException
     */
    public Usuario obtenerUsuarioPorCodigo(String codigo) throws GeneralException {
        if (codigo == null) {
            return null;
        }
        try {
            Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u WHERE u.codigo = :codigo")
                    .setParameter("codigo", codigo)
                    .getSingleResult();
            return usuario;
        } catch (NoResultException nrEx) {
            return null;
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }



	 /**
     * Guarda el objeto indicado
     *
     * @param usu Usuario
     * @return Usuario
     * @throws GeneralException
     */
    public Usuario guardar(Usuario usu) throws GeneralException {
        Usuario usuario = new Usuario();
        Usuario usuarioG = new Usuario();
        Organismo organismoOld = new Organismo();
        try {
            CodigueraDAO<Usuario> codDAO = new CodigueraDAO<>(em, Usuario.class);
            String pwd = StringUtils.EMPTY;
            BusinessException ge = new BusinessException();
            Boolean enviarMail = Boolean.FALSE;
            if (usu != null) {
                
                if(usu.getId()==null){
                    pwd = RandomStringUtils.randomAscii(8);
                    usu.setPassword(EncriptarPasswordUtils.encriptarPassword(pwd));
                    enviarMail = Boolean.TRUE;
                }
                else if(!codDAO.objetoExistePorId(usu.getId())){
                    pwd = RandomStringUtils.randomAscii(8);
                    usu.setPassword(EncriptarPasswordUtils.encriptarPassword(pwd));
                    enviarMail = Boolean.TRUE;
                }
                
                
                if(usu.getId()!=null){
                    usuarioG=em.getReference(Usuario.class, usu.getId());
                    usuarioG.setNombre(usu.getNombre());
                    usuarioG.setCorreoe(usu.getCorreoe());
                    usuarioG.setOrganismo(usu.getOrganismo());
                    if(usu.getOrganismoOld()!=null){
                        usuarioG.setOrganismoOld(usu.getOrganismoOld());
                    }
                    usuarioG.setIsAdministrador(usu.getIsAdministrador());
                }
                else{
                    usuarioG = usu;
                }
                        
                if (UsuarioBusinessValidation.validar(usuarioG)) {
                    if(usuarioG.getOrganismo()!=null){
                        Set<Organismo> listOrganismo = usuarioG.getOrganismos();
                        if(usuarioG.getIsAdministrador().equals(Boolean.TRUE)){
                            if(listOrganismo!=null && !listOrganismo.isEmpty()){
                                if(listOrganismo.stream().filter(o-> o.getCodigo().equals(usu.getOrganismo().getCodigo())).findFirst().orElse(null)==null){
//                                    ge.addError("El organismo " +usu.getOrganismo().getNombre()+ " ya esta asignado al usuario seleccionado");
//                                    throw ge;
                                
                                    if(usuarioG.getOrganismoOld()!=null && !usuarioG.getOrganismoOld().equals(usuarioG.getOrganismo())){
                                        usuarioG.getOrganismos().remove(usuarioG.getOrganismoOld());
                                    }
                                    usuarioG.getOrganismos().add(usuarioG.getOrganismo());
                                
                                }
                                
                            }
                            else{
                                usuarioG.setOrganismos(new HashSet<>(asList(usuarioG.getOrganismo())));
                            }
                        }
                        else if(usuarioG.getIsAdministrador().equals(Boolean.FALSE) && usuarioG.getOrganismo()!=null){
                            if(listOrganismo!=null && !listOrganismo.isEmpty()){
                                listOrganismo.remove(usuarioG.getOrganismo());
                                usuarioG.setOrganismos(listOrganismo);
                            }
                        }
                        
                        usuario = codDAO.guardar(usuarioG);
                        //Enviar mail de la contraseña asignada al usuario si es usuario nuevo
                        if(enviarMail){
                          enviarPassword(usuario,pwd);
                        }
                        return usuario;
                    }
                    else{
                        ge.addError("No se ha podido completar la operación, favor intentelo de nuevo.");
                        throw ge;
                    }
                }
            }
        } catch (BusinessException be) {
            throw be;
        } catch (Exception ex) {
            TechnicalException te = new TechnicalException(ex);
            if(PersistenceHelper.isConstraintViolation(ex)){
                te.addError(ConstantesErrores.ERROR_CODIGO_REPETIDO);
            }
            throw te;
        }

        return usuario;
    }


    public void guardarRolUsuarioEmpresa(Usuario usuario) throws GeneralException {
        try {
            String schema = (String) em.getEntityManagerFactory().getProperties().get("hibernate.default_schema");
            if (schema == null) {
                schema = "public";
            }

            String sql = "DELETE FROM " + schema + ".ae_rel_usuarios_roles r WHERE r.usuario_id=?";
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, usuario.getId());
            query.executeUpdate();

            sql = "INSERT INTO " + schema + ".ae_rel_usuarios_roles(usuario_id, empresa_id, rol_nombre) values (?,?,?)";
            query = em.createNativeQuery(sql);
            query.setParameter(1, usuario.getId());
            query.setParameter(3, Constantes.ROL_ADMIN_SAE);
//            for (Empresa emp : usuario.getEmpresas()) {
//                query.setParameter(2, emp.getId());
//                query.executeUpdate();
//            }
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }
    
    /**
     * Verifica si el usuario es administrador del organismo
     * @param usuario
     * @param org
     * @return Booelan
     * @throws GeneralException 
     */
    public Boolean usuarioAdministrador(Usuario usuario, Organismo org) throws GeneralException {
        Boolean esAdminOrganismo = Boolean.FALSE;
        try {
            
            String schema = (String) em.getEntityManagerFactory().getProperties().get("hibernate.default_schema");
            if (schema == null) {
                schema = "public";
            }
            
            Query query = em.createQuery("SELECT e.id from Empresa e WHERE e.organismoId = :orgId AND e.fechaBaja IS NULL ORDER BY e.nombre");
            query.setParameter("orgId", org.getId());
            List<String> empresas = (List<String>) query.getResultList();
             Integer sizeEmpresas = empresas.size();
            
            String sql = "SELECT distinct * FROM " + schema + ".ae_rel_usuarios_roles r WHERE r.usuario_id=:usuarioId and r.empresa_id IN :empresas AND r.rol_nombre=:rol";
            Query query2 = em.createNativeQuery(sql);
            query2.setParameter("usuarioId", usuario.getId());
            query2.setParameter("empresas", empresas);
            query2.setParameter("rol", Constantes.ROL_ADMIN_SAE);
            
            Integer sizeRolesUsuarioEmpresas = query2.getResultList().size();
            
            if(sizeRolesUsuarioEmpresas!=null && sizeRolesUsuarioEmpresas >0){
                if(sizeEmpresas.equals(sizeRolesUsuarioEmpresas)){
                    esAdminOrganismo = Boolean.TRUE;
                }
            }
            
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
        
        return esAdminOrganismo;
    }

    public void enviarPassword(Usuario usuarioEditar, String pwd) throws GeneralException {
        if (usuarioEditar == null && pwd==null) {
            throw new GeneralException("no_se_puede_enviar_el_correo_porque_el_usuario_no_tiene_direccion_de_correo_electronico");
        }
        //Enviar por mail la nueva contraseña
        if (usuarioEditar.getCorreoe() == null) {
            throw new GeneralException("no_se_puede_enviar_el_correo_porque_el_usuario_no_tiene_direccion_de_correo_electronico");
        }
        
        SgMail mail = new SgMail();
        
        mail.setRecipients(new String[]{usuarioEditar.getCorreoe()});
        mail.setSubject("Nueva contraseña");
        
        String content = "El usuario " + usuarioEditar.getCodigo() + " tendrá la nueva contraseña [" + pwd + "] (sin los corchetes).";
        content = content + " Si la aplicación está configurada para utilizar IDURUGUAY deberá utilizar su contraseña de IDURUGUAY en lugar de esta contraseña";
        mail.setMessage(content);
        
        try {
            LOGGER.log(Level.INFO, "Enviado correo a usuario");
            mailSender.sendMail(mail);

        } 
        catch(MessagingException me){
            LOGGER.log(Level.SEVERE, me.getMessage(),me);
            TechnicalException te = new TechnicalException(me);
            te.addError(ConstantesErrores.ERROR_ENVIO_MAIL);
            throw te;
        }
        catch (Exception mEx) {
            throw new GeneralException(mEx);
        }
    }

	 /**
     * Devuelve la cantidad total de elementos que satisfacen el criterio
     *
     * @param filtro FiltroCodiguera
     * @return Long
     * @throws GeneralException
     */
    public Long obtenerTotalPorFiltro(FiltroUsuario filtro) throws GeneralException {
        try {
            UsuarioDAO codDAO = new UsuarioDAO(em);
            return codDAO.obtenerTotalPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }

    /**
     * Devuelve los elementos que satisfacen el criterio
     *
     * @param filtro FiltroCodiguera
     * @return Lista de <Usuario>
     * @throws GeneralException
     */
    public List<Usuario> obtenerPorFiltro(FiltroUsuario filtro) throws GeneralException {
        try {
            UsuarioDAO codDAO = new UsuarioDAO(em);
            return codDAO.obtenerPorFiltro(filtro);
        } catch (Exception ex) {
            throw new TechnicalException(ex);
        }
    }



	/**
     * Elimina el objeto con la id indicada
     *
     * @param id Long
     * @throws GeneralException
     */
    public void eliminar(Long id) throws GeneralException {
        if (id != null) {
            try {
                CodigueraDAO<Usuario> codDAO = new CodigueraDAO<>(em, Usuario.class);
                codDAO.eliminarPorId(id);
            } catch (Exception ex) {
				if (PersistenceHelper.isConstraintViolation(ex)) {
                    BusinessException b = new BusinessException();
                    b.addError(ConstantesErrores.ERROR_CONSTRAINT_VIOLATION);
                    throw b;
                }
                throw new TechnicalException(ex);
            }

        }
    }

    public List<Organismo> consultarOrganismosPorUsuario(Usuario usu) throws GeneralException {
        List<Organismo> organismos = new ArrayList();
        try {
            
            
            if(usu.getId()!=null){
                Query query = em.createQuery("SELECT r.organismo from RelUsuarioOrganismo r WHERE r.usuario.id = :usuId ");
                query.setParameter("usuId", usu.getId());
                organismos = (List<Organismo>) query.getResultList();
            }
            
            return organismos;
        } catch (Exception e) {
            throw new TechnicalException(e);
        }
    }

    
    

}
