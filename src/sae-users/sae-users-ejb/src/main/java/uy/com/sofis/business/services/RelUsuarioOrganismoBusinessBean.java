/*
 *  SAE-USERS-ADMIN
 *  Desarrollado por Sofis Solutions
 */
package uy.com.sofis.business.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang3.StringUtils;
import uy.com.sofis.entities.UsuarioOrganismoDTO;
import uy.com.sofis.exceptions.GeneralException;
import uy.com.sofis.exceptions.TechnicalException;
import uy.com.sofis.filtros.FiltroUsuario;

/**
 *
 * @author Sofis Solutions
 */
@Stateless
@LocalBean
public class RelUsuarioOrganismoBusinessBean {

	private static final Logger LOGGER = Logger.getLogger(RelUsuarioOrganismoBusinessBean.class.getName());

	@PersistenceContext
	private EntityManager em;

	/**
	 * Devuelve los elementos que satisfacen el criterio
	 * 
	 * @return Lista de <UsuarioOrganismoDTO>
	 * @throws GeneralException
	 */
	public List<UsuarioOrganismoDTO> buscarUsuariosOrganismos(FiltroUsuario filtro) throws GeneralException {
		try {

			String q = "select usu.id,usu.codigo,usu.nombre,usu.correoe,usu.password,org.id as org_id,org.codigo as codOrg,org.nombre as nomOrg, "
					+ "case when rel.usuario_id is not null then 'true' else 'false' end as admin from global.ae_usuarios usu "
					+ "left join global.ae_rel_usuarios_organismos rel on usu.id=rel.usuario_id "
					+ "left join global.ae_organismos org on rel.org_id=org.id " + "where usu.fecha_baja is null ";

			if (!StringUtils.isBlank(filtro.getCodigo())) {
				q = q.concat(" AND usu.codigo='" + filtro.getCodigo() + "' ");
			}

			if (!StringUtils.isBlank(filtro.getNombreYApellido())) {
				q = q.concat(" AND usu.nombre ilike '%" + filtro.getNombreYApellido().trim() + "%' ");
			}

			if (!StringUtils.isBlank(filtro.getCorreo())) {
				q = q.concat(" AND usu.correoe ilike '%" + filtro.getCorreo().trim() + "%' ");
			}

			if (filtro.getOrganismoId() != null) {
				q = q.concat(" AND org.id='" + filtro.getOrganismoId() + "' ");
			}

			if (filtro.getAdministrador() != null) {
				q = q.concat(" AND (case when rel.usuario_id is not null then 'true' else 'false' end)='"
						+ filtro.getAdministrador().toString() + "' ");
			}

			q = q.concat(" order by usu.codigo desc");

			Query query = em.createNativeQuery(q);

			List resultado = query.getResultList();

			List<UsuarioOrganismoDTO> respuesta = new ArrayList<>();

			if (resultado != null) {
				resultado.forEach(z -> {
					Object[] fila = (Object[]) z;
					respuesta.add(transformarFilaEnDTO(fila));
				});
			}

			return respuesta;

		} catch (Exception ex) {
			throw new TechnicalException(ex);
		}
	}

	/**
	 * Transforma una fila en el objeto TransferenciaCedAgrup
	 *
	 * @param fila
	 * @return
	 */
	public UsuarioOrganismoDTO transformarFilaEnDTO(Object[] fila) {

		UsuarioOrganismoDTO ele = new UsuarioOrganismoDTO();
		ele.setId(new Long(fila[0].toString()));
		ele.setCodigo(fila[1].toString());
		ele.setNombre(fila[2].toString());

		ele.setCorreoe(fila[3] != null ? fila[3].toString() : StringUtils.EMPTY);
		ele.setPassword(fila[4] != null ? fila[4].toString() : StringUtils.EMPTY);

		if (fila[5] != null) {
			ele.setOrgId((Integer) fila[5]);
			ele.setCodOrganismo(fila[6].toString());
			ele.setNombreOrganismo(fila[7].toString());
		}
		ele.setAdministrador(new Boolean(fila[8].toString()));
		return ele;
	}

	@SuppressWarnings("unchecked")
	public List<String> obtenerRolesUsuarioTodasLasEmpresas(Long usuarioId) throws GeneralException {
		if (usuarioId == null) {
			return new ArrayList<String>();
		}

		try {
			String sql = "SELECT r.rol_nombre FROM global.ae_rel_usuarios_roles r WHERE r.usuario_id=?";
			Query query = em.createNativeQuery(sql);
			query.setParameter(1, usuarioId);
			return query.getResultList();
		} catch (Exception e) {
			throw new GeneralException(e);
		}
	}

}
